package com.minnymin.zephyrus.core.item.action;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.item.Item;
import com.minnymin.zephyrus.item.ItemRecipe;

/**
 * Zephyrus - ManaPotion.java
 * 
 * @author minnymin3
 * 
 */

public class ManaPotion extends Item implements Listener {

	public ManaPotion() {
		super(ChatColor.AQUA + "Mana Potion", 1, Material.POTION, DataStructureUtils.createList(ChatColor.GRAY
				+ "Restores Mana"));
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")),
				DataStructureUtils.createList(1));
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("AAA", "ABA", "AAA");
		recipe.setIngredient('B', new ItemStack(Material.POTION, 1, (short) 8192).getData());
		recipe.setIngredient('A', Material.GLOWSTONE_DUST);
		return recipe;
	}
	
	@EventHandler
	public void onPotionConsume(PlayerItemConsumeEvent e) {
		if (Zephyrus.getItem(e.getItem()) == this) {
			Player player = e.getPlayer();
			Zephyrus.getUser(player).drainMana(-100);
		}
	}

}
