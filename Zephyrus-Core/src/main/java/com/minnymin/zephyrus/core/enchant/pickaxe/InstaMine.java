package com.minnymin.zephyrus.core.enchant.pickaxe;

import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.enchant.Enchant;
import com.minnymin.zephyrus.enchant.EnchantTarget;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - InstaMine.java
 * 
 * @author minnymin3
 * 
 */

public class InstaMine implements Enchant, Listener {

	protected List<String> blacklist;

	@Override
	public int getChance() {
		return 8;
	}

	@Override
	public int getCostPerLevel() {
		return 25;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public String getName() {
		return "InstaMine";
	}

	@Override
	public EnchantTarget getTarget() {
		return EnchantTarget.PICKAXE;
	}

	@Override
	public boolean isEnchantmentIncompatible(Enchantment enchantment) {
		return false;
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onInteract(PlayerInteractEvent event) {
		if (blacklist == null) {
			blacklist = Zephyrus.getSpellConfig().getConfig().getStringList("dig.Blacklist");
		}
		Player player = (Player) event.getPlayer();
		ItemStack item = player.getItemInHand();
		if (item != null && event.getAction() == Action.LEFT_CLICK_BLOCK) {
			for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
				Enchant ench = Zephyrus.getEnchantmentManager().getEnchant(entry.getKey().getId());
				if (ench instanceof InstaMine) {
					User user = Zephyrus.getUser(player);
					user.setTarget(this, TargetType.BLOCK, 5, false);
					Block block = (Block) user.getTarget(this).getTarget();
					if (block.getType() != Material.BEDROCK) {
						block.breakNaturally();
						item.setDurability((short) (item.getDurability() - 1));
					}
				}
			}
		}
	}

}
