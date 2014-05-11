package com.minnymin.zephyrus.core.state;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - LightState.java
 * 
 * @author minnymin3
 * 
 */

public class LightState implements State {

	@SuppressWarnings("deprecation")
	@Override
	public void onApplied(User user) {
		Location loc = user.getPlayer().getLocation();
		loc.setY(loc.getY() - 1);
		user.getPlayer().sendBlockChange(loc, Material.GLOWSTONE, (byte) 0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemoved(User user) {
		Location loc = user.getPlayer().getLocation();
		loc.setY(loc.getY() - 1);
		user.getPlayer().sendBlockChange(loc, loc.getBlock().getType(), loc.getBlock().getData());
	}

	@Override
	public void onTick(User user) {
	}

	@Override
	public void onWarning(User user) {
	}

	@Override
	public void onStartup(User user) {
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (Zephyrus.getUser(e.getPlayer()).isStateApplied(this)) {
			Location newBlock = e.getTo().clone().add(0, -1, 0);
			Material meterial = newBlock.getBlock().getType();
			if (meterial != Material.AIR && meterial != Material.WATER && meterial != Material.STATIONARY_WATER
					&& meterial != Material.LAVA && meterial != Material.STATIONARY_LAVA) {
				e.getPlayer().sendBlockChange(newBlock, Material.GLOWSTONE, (byte) 0);
			}
			Location oldBlock = e.getFrom().clone().add(0, -1, 0);
			if (newBlock.getBlockX() != oldBlock.getBlockX() || newBlock.getBlockY() != oldBlock.getBlockY()
					|| newBlock.getBlockZ() != oldBlock.getBlockZ()) {
				e.getPlayer().sendBlockChange(oldBlock, oldBlock.getBlock().getType(), oldBlock.getBlock().getData());
			}
		}
	}

	@Override
	public int getTickTime() {
		return 0;
	}

}
