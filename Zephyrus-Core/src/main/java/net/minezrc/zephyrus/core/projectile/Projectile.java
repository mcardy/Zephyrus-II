package net.minezrc.zephyrus.core.projectile;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Projectile.java
 * 
 * @author minnymin3
 * 
 */

public interface Projectile {

	public Entity getEntity();
	public void launchProjectile(Player player);
	public void onParticleDisplay(Location loc);
	public void onHitBlock(Location loc);
	public void onHitEntity(LivingEntity entity);
	
}
