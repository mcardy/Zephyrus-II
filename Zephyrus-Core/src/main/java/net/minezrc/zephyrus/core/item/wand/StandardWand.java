package net.minezrc.zephyrus.core.item.wand;

import java.util.List;
import java.util.Map;

import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.item.ItemRecipe;
import net.minezrc.zephyrus.item.Wand;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - BasicWand.java
 * 
 * @author minnymin3
 * 
 */

public class StandardWand implements Wand {

	// TODO Different variations of standard wand
	
	@Override
	public String getName() {
		return ChatColor.GOLD + "Standard Wand";
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
	public Material getMaterial() {
		return Material.STICK;
	}

	@Override
	public List<String> getLore() {
		return DataStructureUtils.createList(ChatColor.GRAY + "Standard wand with no perks");
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")), DataStructureUtils.createList(1));
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
	public int getCraftingLevel() {
		return 0;
	}

	@Override
	public int getCraftingAbilityLevel() {
		return 10;
	}
	
	@Override
	public int getBindingAbilityLevel() {
		return 10;
	}

	@Override
	public String getBoundName(Spell spell) {
		return getName() + ChatColor.GRAY + " - " + ChatColor.GOLD + spell.getName();
	}
	
	@Override
	public List<String> getBoundLore(Spell spell) {
		return DataStructureUtils.createList(ChatColor.GRAY + "Bound Spell: " + spell.getName(), ChatColor.GRAY + "Standard wand with no perks");
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
