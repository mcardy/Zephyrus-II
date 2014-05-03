package com.minnymin.zephyrus.core.user;

import java.util.HashMap;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.UserManager;

/**
 * Zephyrus - UserManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleUserManager implements UserManager {

	protected HashMap<String, User> userMap;
	
	private UserBarDisplay barDisplay;
	
	public SimpleUserManager() {
		userMap = new HashMap<String, User>();
		barDisplay = new UserBarDisplay();
	}
	
	public UserBarDisplay getBarDisplay() {
		return barDisplay;
	}
	
	@SuppressWarnings("unchecked")
	protected synchronized Map<String, User> getCloneMap() {
		return (Map<String, User>) userMap.clone();
	}
	
	public User getUser(Player player) {
		return userMap.get(player.getName());
	}
	
	public User getUser(String player) {
		return userMap.get(player);
	}
	
	public void load() {
		Bukkit.getPluginManager().registerEvents(new UserListener(this), Zephyrus.getPlugin());
		Bukkit.getScheduler().runTaskTimerAsynchronously(Zephyrus.getPlugin(), new UserSaveTask(), 1200L, 1200L);
		Bukkit.getScheduler().runTaskTimerAsynchronously(Zephyrus.getPlugin(), new UserTickTask(), 2L, 2L);
		for (Player player : Bukkit.getOnlinePlayers()) {
			userMap.put(player.getName(), new OnlineUser(player));
		}
	}
	
	protected void removeUser(String player) {
		this.userMap.remove(player);
	}

	public void unload() {
		for (User user : userMap.values()) {
			((OnlineUser)user).save();
		}
		userMap.clear();
	}
	
}
