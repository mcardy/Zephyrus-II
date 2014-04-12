package net.minezrc.zephyrus.core.projectile;

import java.util.WeakHashMap;

import net.minezrc.zephyrus.Zephyrus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Zephyrus - ProjectileListener.java
 * 
 * @author minnymin3
 * 
 */

public class ProjectileHandler implements Listener {

	private WeakHashMap<Entity, Projectile> projectileData;
	private static ProjectileHandler INSTANCE;

	private ProjectileHandler() {
		projectileData = new WeakHashMap<Entity, Projectile>();
	}
	
	protected static ProjectileHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ProjectileHandler();
			Bukkit.getPluginManager().registerEvents(INSTANCE, Zephyrus.getPlugin());
		}
		return INSTANCE;
	}
	
	protected void launchProjectile(final Projectile projectile) {
		projectileData.put(projectile.getEntity(), projectile);
		Bukkit.getScheduler().runTask(Zephyrus.getPlugin(), new BukkitRunnable() {
			@Override
			public void run() {
				if (projectileData.containsKey(projectile.getEntity())) {
					projectile.onParticleDisplay(projectile.getEntity().getLocation());
					Bukkit.getScheduler().runTaskLater(Zephyrus.getPlugin(), this, 1);
				}
			}
		});
	}
	
	@EventHandler
	public void onProjectileHitEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Snowball) {
			if (projectileData.containsKey(event.getDamager())) {
				Projectile projectile = projectileData.get(event.getDamager());
				projectile.onHit(event.getDamager().getLocation());
				event.setDamage(projectile.getDamage());
				projectileData.remove(event.getDamager());
			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if (event.getEntity() instanceof Snowball) {
			if (projectileData.containsKey(event.getEntity())) {
				Projectile projectile = projectileData.get(event.getEntity());
				projectile.onHit(event.getEntity().getLocation());
				projectileData.remove(event.getEntity());
			}
		}
	}

}
