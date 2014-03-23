package net.minezrc.zephyrus.core.spell;

import java.util.Arrays;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.Spell;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * Zephyrus - Zephyronomicon.java
 * 
 * @author minnymin3
 * 
 */

public class Zephyronomicon {

	public static ItemStack createZephyronomicon() {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();

		meta.setTitle(Language.get("item.zephyronomicon.name", ChatColor.BOLD + "" + ChatColor.AQUA + "Zephyronomicon"));
		meta.setLore(Arrays.asList(Language.get("item.zephyronomicon.lore", ChatColor.GRAY
				+ "Your encyclopedia to all things magic")));

		meta.addPage(Language.get("item.zephyronomicon.pg1", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Welcome to the magical world of Zephyrus.\n\n"
				+ ChatColor.BLACK + ChatColor.RESET
				+ " This book is a quick start guide meant get you casting spells as quickly as possible.\n\n"
				+ ChatColor.GOLD + "Next Page"));
		// TODO Write brief tutorial

		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createZephyricRecipeBook(int startLevel, int endLevel) {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();

		meta.setTitle(Language.get("item.recipebook.name", ChatColor.GOLD + "Zephyric Recipe Book"));
		meta.setLore(Arrays.asList(Language.get("item.recipebook.lore", ChatColor.GRAY
				+ "Recipes for all spells from level " + startLevel + " to level " + endLevel)));

		StringBuilder currentText = new StringBuilder();
		int pos = 0;

		for (Spell spell : Zephyrus.getSpellSet()) {
			if (spell.getRequiredLevel() > startLevel && spell.getRequiredLevel() < endLevel) {
				currentText.append(ChatColor.GOLD + WordUtils.capitalize(spell.getName()) + ChatColor.DARK_GRAY
						+ "\n " + Language.get("item.recipebook.recipe", "Recipe") + ":" + ChatColor.GRAY);
				if (spell.getRecipe().getItems().size() <= 4) {
					for (ItemStack recipe : spell.getRecipe().getItems()) {
						currentText.append("\n  "
								+ WordUtils.capitalizeFully(recipe.getType().name().toLowerCase().replace("_", " "))
								+ " x" + recipe.getAmount());
						if (recipe.getDurability() > 0) {
							currentText.append(" meta " + recipe.getDurability());
						}
					}
				} else {
					for (ItemStack recipe : spell.getRecipe().getItems()) {
						currentText.append(WordUtils.capitalizeFully(recipe.getType().name().toLowerCase()
								.replace("_", " "))
								+ " x" + recipe.getAmount());
						if (recipe.getDurability() > 0) {
							currentText.append(" meta " + recipe.getDurability());
						}
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
