package com.minnymin.zephyrus.core.item;

import java.util.Arrays;
import java.util.Map.Entry;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - Zephyronomicon.java
 * 
 * @author minnymin3
 * 
 */

public class MagicBooks {

	public static ItemStack createZephyronomicon() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();

		meta.setTitle(Language.get("item.zephyronomicon.name", ChatColor.BOLD + "" + ChatColor.AQUA + "Zephyronomicon"));
		meta.setLore(Arrays.asList(Language.get("item.zephyronomicon.lore", ChatColor.GRAY
				+ "Your encyclopedia to all things magic")));

		meta.addPage(Language.get("item.zephyronomicon.pg1", ChatColor.DARK_AQUA + "" + ChatColor.BOLD
				+ "Welcome to the magical world of Zephyrus.\n\n" + ChatColor.BLACK + ChatColor.RESET
				+ " This book is a quick start guide meant get you casting spells as quickly as possible.\n\n"
				+ ChatColor.GOLD + "Next Page"));
		// TODO Write brief tutorial

		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createZephyricRecipeBook(int startLevel, int endLevel) {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		meta.setTitle(Language.get("item.recipebook.name", ChatColor.GOLD + "Mystic Recipe Book"));
		meta.setLore(Arrays.asList(Language.get("item.recipebook.lore", ChatColor.GRAY
				+ "Recipes for all spells from level [START] to level [END]", "[START]", String.valueOf(startLevel),
				"[END]", String.valueOf(endLevel))));

		StringBuilder currentText = new StringBuilder();
		int pos = 0;

		for (Spell spell : Zephyrus.getSpellSet()) {
			if (spell.getRequiredLevel() > startLevel && spell.getRequiredLevel() < endLevel) {
				currentText
						.append(ChatColor.GOLD
								+ WordUtils.capitalize(spell.getName())
								+ ChatColor.DARK_GRAY
								+ " - "
								+ Language.get("item.recipebook.level", "Level: [LEVEL]", "[LEVEL]",
										String.valueOf(spell.getRequiredLevel())) + "\n "
								+ Language.get("item.recipebook.recipe", "Recipe") + ":" + ChatColor.GRAY);
				if (spell.getRecipe().getAspectMap().size() <= 4) {
					for (Entry<Aspect, Integer> recipe : spell.getRecipe().getAspectMap().entrySet()) {
						currentText.append("\n  "
								+ Language.get("aspect." + recipe.getKey().name().toLowerCase() + ".name", recipe
										.getKey().getDefaultName()) + " x" + recipe.getValue());
					}
				} else {
					for (Entry<Aspect, Integer> recipe : spell.getRecipe().getAspectMap().entrySet()) {
						currentText.append(Language.get("aspect." + recipe.getKey().name().toLowerCase() + ".name",
								recipe.getKey().getDefaultName()) + " x" + recipe.getValue());
						currentText.append(", ");
					}
				}
				if (pos == 0) {
					pos = 1;
					currentText.append("\n\n");
				} else {
					meta.addPage(currentText.toString());
					pos = 0;
					currentText = new StringBuilder();
				}
			}
		}
		if (currentText.length() > 0) {
			meta.addPage(currentText.toString());
		}
		item.setItemMeta(meta);
		return item;
	}
}
