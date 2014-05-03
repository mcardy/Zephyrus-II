package com.minnymin.zephyrus.core.user;

import java.util.Map;


import org.bukkit.scheduler.BukkitRunnable;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - UserTick.java
 * 
 * @author minnymin3
 * 
 */

public class UserTickTask extends BukkitRunnable {

	@Override
	public void run() {
		Map<String, User> userMap = ((SimpleUserManager) Zephyrus.getUserManager()).getCloneMap();
		for (String username : userMap.keySet()) {
			((OnlineUser) userMap.get(username)).tick();
		}
	}

}
