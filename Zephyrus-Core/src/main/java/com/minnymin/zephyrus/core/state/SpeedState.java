package com.minnymin.zephyrus.core.state;

import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - SpeedState.java
 * 
 * @author minnymin3
 * 
 */

public class SpeedState implements State {
	
	@Override
	public int getTickTime() {
		return 0;
	}

	@Override
	public void onApplied(User user) {
		user.getPlayer().setWalkSpeed(0.4F);
		Language.sendMessage("spell.speed.applied", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		user.getPlayer().setWalkSpeed(0.2F);
		Language.sendMessage("spell.speed.removed", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
		user.getPlayer().setWalkSpeed(0.2F);
	}

	@Override
	public void onTick(User user) {
	}

	@Override
	public void onWarning(User user) {
		user.getPlayer().setWalkSpeed(0.3F);
		Language.sendMessage("spell.speed.removed", user.getPlayer());
	}

}
