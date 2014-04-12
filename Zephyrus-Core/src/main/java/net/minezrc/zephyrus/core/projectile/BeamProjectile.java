package net.minezrc.zephyrus.core.projectile;

import java.util.ArrayList;
import java.util.List;

import net.minezrc.zephyrus.core.packet.server.PacketEntityDestroy;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

/**
 * Zephyrus - BeamProjectile.java
 * 
 * @author minnymin3
 * 
 */

public class BeamProjectile implements Projectile {

	private Snowball snowball;
	private Particle particle;
	private int damage;
	private List<Location> locs;
	
	public BeamProjectile(Particle particle, int damage) {
		this.particle = particle;
		this.damage = damage;
		locs = new ArrayList<Location>();
	}
	
	@Override
	public int getDamage() {
		return damage;
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
	public void onHit(Location loc) {
	}
	
	@Override
	public void onParticleDisplay(Location loc) {
		locs.add(loc);
		for (Location pos : locs) {
			ParticleEffects.sendParticle(particle, pos, 4);
		}
	}

	
}