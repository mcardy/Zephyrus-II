package net.minezrc.zephyrus.core.projectile;

import net.minezrc.zephyrus.core.packet.server.PacketEntityDestroy;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

/**
 * Zephyrus - BallProjectile.java
 * 
 * @author minnymin3
 * 
 */

public class BallProjectile implements Projectile {

	private Snowball snowball;
	private Particle particle;
	private int damage;
	private int amount;
	
	public BallProjectile(Particle particle, int damage, int amount) {
		this.particle = particle;
		this.damage = damage;
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
		ParticleEffects.sendParticle(particle, loc, amount);
	}

	
}
