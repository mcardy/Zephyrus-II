package net.minezrc.zephyrus.core.user;

import java.util.Map;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.user.User;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * Zephyrus - UserSaveTask.java
 * 
 * @author minnymin3
 * 
 */

public class UserSaveTask extends BukkitRunnable {

	@Override
	public void run() {
		Map<String, User> userMap = ((SimpleUserManager) Zephyrus.getUserManager()).getCloneMap();
		for (String username : userMap.keySet()) {
			((OnlineUser) userMap.get(username)).save();
		}
	}

}
