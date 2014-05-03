package com.minnymin.zephyrus.core.projectile;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Zephyrus - TargetHomingProjectile.java
 * 
 * Projectile tracking code from SethBling's HomingArrows mod
 * http://sethbling.com/media/5920/blinghomingarrows.jar
 * 
 * @author minnymin3
 * 
 */

public class HomingEntityProjectile implements Projectile {

	private Entity entity;
	private Entity target;

	public HomingEntityProjectile(Entity entity, Entity target) {
		this.entity = entity;
		this.target = target;
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public void launchProjectile(Player player) {
		ProjectileHandler.getInstance().launchProjectile(this);
	}

	@Override
	public void onProjectileTick(Location loc) {
		if (target.isDead()) {
			return;
		}
		double speed = this.entity.getVelocity().length() * 0.9D + 0.14D;
		Vector velocity = null;
		Vector direction = this.entity.getVelocity().clone().normalize();
		Vector targetDirection = this.target.getLocation().clone().add(new Vector(0, 0.5D, 0))
				.subtract(this.entity.getLocation()).toVector();
		Vector targetDirectionNorm = targetDirection.clone().normalize();

		double angle = direction.angle(targetDirectionNorm);

		if (angle < 0.12D) {
			velocity = direction.clone().multiply(speed);
		} else {
			velocity = direction.clone().multiply((angle - 0.12D) / angle)
					.add(targetDirectionNorm.clone().multiply(0.12D / angle)).normalize().multiply(speed);
		}
		this.entity.setVelocity(velocity.add(new Vector(0.0D, 0.03D, 0.0D)));
	}

	@Override
	public void onHitBlock(Location loc) {
	}

	@Override
	public void onHitEntity(LivingEntity entity) {
	}

}
