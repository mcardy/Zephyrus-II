package com.minnymin.zephyrus.core.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.minnymin.zephyrus.Zephyrus;

/**
 * Zephyrus - UserListener.java
 * 
 * @author minnymin3
 * 
 */

public class UserListener implements Listener {

	private CoreUserManager manager;

	protected UserListener(CoreUserManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onConnect(PlayerJoinEvent event) {
		if (manager.userMap.containsKey(event.getPlayer().getName()))
			return;
		manager.userMap.put(event.getPlayer().getName(), new OnlineUser(event.getPlayer()));
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onTeleport(final PlayerTeleportEvent event) {
		new BukkitRunnable() {
			public void run() {
				Zephyrus.getUserManager().getBarDisplay().removeBar(event.getPlayer());
				Zephyrus.getUser(event.getPlayer()).drainMana(0);
			}
		}.runTaskLater(Zephyrus.getPlugin(), 10);
	}
	
	@EventHandler
	public void onWorldChange(final PlayerChangedWorldEvent event) {
		new BukkitRunnable() {
			public void run() {
				Zephyrus.getUserManager().getBarDisplay().removeBar(event.getPlayer());
				Zephyrus.getUser(event.getPlayer()).drainMana(0);
			}
		}.runTaskLater(Zephyrus.getPlugin(), 10);
	}

}
