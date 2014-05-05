package com.minnymin.zephyrus.core.projectile;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityDestroy;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.state.State;

/**
 * Zephyrus - StateBallProjectile.java
 * 
 * @author minnymin3
 * 
 */

public class StateBallProjectile implements Projectile {

	private Snowball snowball;
	private Particle particle;
	private State state;
	private int time;
	private int amount;

	public StateBallProjectile(Particle particle, State state, int time, int amount) {
		this.particle = particle;
		this.state = state;
		this.time = time;
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
		if (entity instanceof Player) {
			Zephyrus.getUser((Player)entity).addState(state, time);
		}
	}

	@Override
	public void onProjectileTick(Location loc) {
		ParticleEffects.sendParticle(particle, loc, amount);
	}

}
