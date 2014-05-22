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

public class AdvancedWand extends Wand {

	// TODO Different variations of advanced wand
	private static String name = ChatColor.DARK_AQUA + "Advanced Wand";

	public AdvancedWand() {
		super(name, name + ChatColor.GOLD + " - " + ChatColor.DARK_AQUA + "[SPELL]", 10, 25, 25, Material.STICK,
				DataStructureUtils.createList(ChatColor.GRAY + "Advanced wand:", ChatColor.GREEN + " - Power +1",
						ChatColor.AQUA + " - Mana -10"), DataStructureUtils.createList(ChatColor.GRAY
						+ "Bound Spell: [SPELL]", ChatColor.GRAY + "Advanced wand:", ChatColor.GREEN + " - Power +1",
						ChatColor.AQUA + " - Mana -10"));
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("  d", " s ", "i  ");
		recipe.setIngredient('d', Material.GLOWSTONE);
		recipe.setIngredient('s', Material.STICK);
		recipe.setIngredient('i', Material.DIAMOND);
		return recipe;
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")),
				DataStructureUtils.createList(1));
	}

	@Override
	public String getSpell(ItemStack stack) {
		if (stack.getItemMeta().hasLore() && stack.getItemMeta().getLore().get(0).contains("Bound Spell:")) {
			return stack.getItemMeta().getLore().get(0).replace(ChatColor.GRAY + "Bound Spell: ", "");
		} else {
			return null;
		}
	}

	@Override
	public int getManaDiscount(Spell spell) {
		return 20;
	}

	@Override
	public int getPowerIncrease(Spell spell) {
		return 2;
	}

}
