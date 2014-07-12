package com.minnymin.zephyrus.core.state;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - FlyState.java
 * 
 * @author minnymin3
 * 
 */

public class FlyState implements State {
	
	@Override
	public int getTickTime() {
		return 0;
	}

	@Override
	public void onApplied(User user) {
		user.getPlayer().setAllowFlight(true);
		Language.sendMessage("spell.fly.applied", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		user.getPlayer().setAllowFlight(false);
		Language.sendMessage("spell.fly.removed", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
		Player player = user.getPlayer();
		if (player.getGameMode() != GameMode.CREATIVE) {
			player.setAllowFlight(false);
		}
	}

	@Override
	public void onTick(User user) {
	}

	@Override
	public void onWarning(User user) {
		Language.sendMessage("spell.fly.warning", user.getPlayer());
	}

}
