package com.minnymin.zephyrus.core.projectile;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityDestroy;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;

/**
 * Zephyrus - DamagingBallProjectile.java
 * 
 * @author minnymin3
 * 
 */

public class DamagingBallProjectile implements Projectile {

	protected Snowball snowball;
	protected Particle particle;
	protected int damage;
	protected int amount;

	public DamagingBallProjectile(Particle particle, int damage, int amount) {
		this.particle = particle;
		this.damage = damage;
		this.amount = amount;
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
		ParticleEffects.sendParticle(particle, loc, amount);
	}

}
