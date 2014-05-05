package com.minnymin.zephyrus.core.projectile;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityDestroy;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;

/**
 * Zephyrus - BeamProjectile.java
 * 
 * @author minnymin3
 * 
 */

public class DamagingBeamProjectile implements Projectile {

	private Snowball snowball;
	private Particle particle;
	private int damage;
	private List<Location> locs;
	
	public DamagingBeamProjectile(Particle particle, int damage) {
		this.particle = particle;
		this.damage = damage;
		locs = new ArrayList<Location>();
	}
	
	@Override
	public Entity getEntity() {
		return snowball;
	}
	
	@Override
	public void launchProjectile(Player player) {
		snowball = player.launchProjectile(Snowball.class);
		PacketEntityDestroy packet = new PacketEntityDestroy(snowball.getEntityId());
		packet.send(player);
		for (Player p : player.getWorld().getPlayers()) {
			packet.send(p);
		}
		ProjectileHandler.getInstance().launchProjectile(this);
	}
	
	@Override
	public void onHitBlock(Location loc) {
	}
	
	@Override
	public void onHitEntity(LivingEntity entity) {
		entity.damage(damage);
	}
	
	@Override
	public void onProjectileTick(Location loc) {
		locs.add(loc);
		for (Location pos : locs) {
			ParticleEffects.sendParticle(particle, pos, 4);
		}
	}

	
}