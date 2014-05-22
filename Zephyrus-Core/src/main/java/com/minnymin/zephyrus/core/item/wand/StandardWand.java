package com.minnymin.zephyrus.core.item.wand;

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
 * Zephyrus - BasicWand.java
 * 
 * @author minnymin3
 * 
 */

public class StandardWand extends Wand {

	// TODO Different variations of standard wand

	private static String name = ChatColor.GOLD + "Standard Wand";

	public StandardWand() {
		super(name, name + ChatColor.GRAY + " - " + ChatColor.GOLD + "[SPELL]", 0, 10, 10, Material.STICK,
				DataStructureUtils.createList(ChatColor.GRAY + "Standard wand with no perks"), DataStructureUtils
						.createList(ChatColor.GRAY + "Bound Spell: [SPELL]", ChatColor.GRAY
								+ "Standard wand with no perks"));
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("  d", " s ", "i  ");
		recipe.setIngredient('d', Material.GLOWSTONE_DUST);
		recipe.setIngredient('s', Material.STICK);
		recipe.setIngredient('i', Material.GOLD_INGOT);
		return recipe;
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")),
				DataStructureUtils.createList(1));
	}

	@Override
	public int getPowerIncrease(Spell spell) {
		return 0;
	}

	@Override
	public int getManaDiscount(Spell spell) {
		return 0;
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
