package com.minnymin.zephyrus.core.state;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - FeatherState.java
 * 
 * @author minnymin3
 * 
 */

public class FeatherState implements State {

	@Override
	public int getTickTime() {
		return 0;
	}

	@Override
	public void onApplied(User user) {
		Language.sendMessage("spell.feather.start", "You feel like you can float like a feather", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.feather.end", "You feel heavy again", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
	}

	@Override
	public void onTick(User user) {
	}

	@Override
	public void onWarning(User user) {
		Language.sendMessage("spell.feather.warn", "You begin to feel heavy", user.getPlayer());
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (Zephyrus.getUser(e.getPlayer()).isStateApplied(this) && !e.getPlayer().isFlying()) {
			if (e.getFrom().getY() > e.getTo().getY()) {
				Location loc = e.getPlayer().getLocation();
				loc.setY(loc.getY() - 1);
				if (loc.getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
					Vector v = e.getPlayer().getVelocity();
					v.setY(v.getY() / 1.5);
					e.getPlayer().setVelocity(v);
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		try {
			if (e.getEntity() instanceof Player && e.getCause() == DamageCause.FALL) {
				Player player = (Player) e.getEntity();
				if (Zephyrus.getUser(player).isStateApplied(this)) {
					e.setCancelled(true);
				}
			}
		} catch (Exception ex) {
		}
	}

}
