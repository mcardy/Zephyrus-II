package com.minnymin.zephyrus.core.state;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - BuildState.java
 * 
 * @author minnymin3
 * 
 */

public class BuildState implements State {

	@Override
	public int getTickTime() {
		return 0;
	}

	@Override
	public void onApplied(User user) {
		Language.sendMessage("spell.build.applied", ChatColor.GRAY + "You feel your reach extend", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.build.removed", ChatColor.GRAY + "You feel your reach shorten back to normal", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
	}

	@Override
	public void onTick(User user) {
	}

	@Override
	public void onWarning(User user) {
		Language.sendMessage("spell.build.warning", ChatColor.GRAY + "You feel your begin to shorten back to normal", user.getPlayer());
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		User user = Zephyrus.getUser(player);
		if (user.isStateApplied(this)) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR) {
				List<Block> blocks = player.getLastTwoTargetBlocks(null, 100); // FIXME Transparent blocks
				if (Zephyrus.getHookManager().canBuild(player, blocks.get(0)) && player.getItemInHand() != null
						&& player.getItemInHand().getType().isBlock()) {
					Block target = blocks.get(0);
					ItemStack inHand = player.getItemInHand();
					target.setType(inHand.getType());
					target.setData((byte) inHand.getDurability());
					inHand.setAmount(inHand.getAmount() - 1);
					player.setItemInHand(inHand.getAmount() != 0 ? inHand : null);
					target.getWorld().playEffect(target.getLocation(), Effect.STEP_SOUND, target.getType(), 0);
				}
			} else if (event.getAction() == Action.LEFT_CLICK_AIR) {
				Block block = player.getTargetBlock(null, 100);
				if (Zephyrus.getHookManager().canBuild(player, block) && player.getItemInHand() != null
						&& player.getItemInHand().getType().isBlock()) {
					if (player.getGameMode() == GameMode.CREATIVE) {
						block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getTypeId());
						block.setType(Material.AIR);
						block.setData((byte) 0);
					} else {
						block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getTypeId());
						block.breakNaturally();
					}
				}
			}
		}
	}

}
