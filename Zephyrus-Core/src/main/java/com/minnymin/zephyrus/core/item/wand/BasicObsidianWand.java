package com.minnymin.zephyrus.core.item.wand;

import java.util.List;
import java.util.Map;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.item.ItemRecipe;
import com.minnymin.zephyrus.item.Wand;
import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - BasicObsidianWand.java
 * 
 * @author minnymin3
 * 
 */

public class BasicObsidianWand implements Wand {
	
	@Override
	public String getName() {
		return ChatColor.GRAY + "Simple Wand";
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("aad", "asa", "iaa");
		recipe.setIngredient('a', Material.OBSIDIAN);
		recipe.setIngredient('d', Material.REDSTONE);
		recipe.setIngredient('s', Material.STICK);
		recipe.setIngredient('i', Material.IRON_INGOT);
		return recipe;
	}

	@Override
	public Material getMaterial() {
		return Material.STICK;
	}

	@Override
	public List<String> getLore() {
		return DataStructureUtils.createList(ChatColor.GRAY + "Basic wand with increased power and discounted mana");
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")), DataStructureUtils.createList(1));
	}
	
	@Override
	public int getPowerIncrease(Spell spell) {
		return 1;
	}

	@Override
	public int getManaDiscount(Spell spell) {
		return 5;
	}

	@Override
	public int getCraftingLevel() {
		return 3;
	}

	@Override
	public int getCraftingAbilityLevel() {
		return 5;
	}
	
	@Override
	public int getBindingAbilityLevel() {
		return 5;
	}

	@Override
	public String getBoundName(Spell spell) {
		return getName() + ChatColor.GOLD + " - " + ChatColor.GRAY + spell.getName();
	}
	
	@Override
	public List<String> getBoundLore(Spell spell) {
		return DataStructureUtils.createList(ChatColor.GRAY + "Bound Spell: " + spell.getName(), ChatColor.GRAY + "Basic wand with increased power and discounted mana");
	}

	@Override
	public String getSpell(ItemStack stack) {
		if (stack.getItemMeta().hasLore() && stack.getItemMeta().getLore().get(0).contains("Bound Spell:")) {
			return stack.getItemMeta().getLore().get(0).replace(ChatColor.GRAY + "Bound Spell: ", "");
		} else {
			return null;
		}
	}
	
}
