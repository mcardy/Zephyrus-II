package net.minezrc.zephyrus.core.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Zephyrus - UserListener.java
 * 
 * @author minnymin3
 * 
 */

public class UserListener implements Listener {

	private SimpleUserManager manager;

	protected UserListener(SimpleUserManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onConnect(PlayerJoinEvent event) {
		if (manager.userMap.containsKey(event.getPlayer().getName()))
			return;
		manager.userMap.put(event.getPlayer().getName(), new OnlineUser(event.getPlayer()));
	}

}
