package net.minezrc.zephyrus.core.projectile;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Projectile.java
 * 
 * @author minnymin3
 * 
 */

public interface Projectile {

	public int getDamage();
	public Entity getEntity();
	public void launchProjectile(Player player);
	public void onParticleDisplay(Location loc);
	public void onHit(Location loc);
	
}
