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
				+ "Welcome to the magical world of Zephyrus." + ChatColor.BLACK + ChatColor.RESET
				+ "\n\nThis book is a quick start guide meant get you casting spells as quickly as possible.\n\n"
				+ ChatColor.BOLD + "     Next Page"));
		meta.addPage(Language.get("item.zephyronomicon.pg2", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + ""
				+ ChatColor.BOLD + "First Wand" + ChatColor.RESET + ChatColor.BLACK
				+ "\nThe wand is the core feature of Zephyrus.\n" + ChatColor.GRAY + "  Crafting:\n" + ChatColor.GRAY
				+ "    \u2588\u2588" + ChatColor.RED + "\u2588\n" + ChatColor.GRAY + "    \u2588" + ChatColor.GREEN
				+ "\u2588" + ChatColor.GRAY + "\u2588\n" + ChatColor.BLUE + "    \u2588" + ChatColor.GRAY
				+ "\u2588\u2588\n\n" + ChatColor.RED + "\u2588" + ChatColor.BLACK + " Redstone\n" + ChatColor.GREEN
				+ "\u2588" + ChatColor.BLACK + " Stick\n" + ChatColor.BLUE + "\u2588" + ChatColor.BLACK
				+ " Iron Ingot\n" + ChatColor.GRAY + "\u2588" + ChatColor.BLACK + " Nothing"));
		meta.addPage(Language
				.get("item.zephyronomicon.pg3",
						ChatColor.DARK_AQUA
								+ ""
								+ ChatColor.UNDERLINE
								+ ""
								+ ChatColor.BOLD
								+ "Wands"
								+ ChatColor.RESET
								+ ChatColor.BLACK
								+ "\nWands are used for many of Zephyrus's features. "
								+ "They are used to craft spells and can be used to cast spells. "
								+ "There are many types of wands that can be found on the wiki:\n http://wiki.minnymin.com/Portal:Wands"));
		meta.addPage(Language.get("item.zephyronomicon.pg4", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + ""
				+ ChatColor.BOLD + "Spell Crafting" + ChatColor.RESET + ChatColor.BLACK
				+ "\nSpell crafting is done by dropping items onto a bookshelf and clicking the shelf with your wand. "
				+ "Each spell has a special recipe composed of aspects. "
				+ "A book of all recipes can be obtained with '/book recipe'."));
		meta.addPage(Language.get("item.zephyronomicon.pg5", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + ""
				+ ChatColor.BOLD + "Spell Crafting Continued" + ChatColor.RESET + ChatColor.BLACK
				+ "\nSome spells require you to be a certain level or already know a spell to craft them (see levelling). "
				+ "Once you craft a spell, you can pickup a spelltome and view the spell's information. "
				+ "Left click a spelltome to learn the spell."));
		meta.addPage(Language.get("item.zephyronomicon.pg6", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + ""
				+ ChatColor.BOLD + "Aspects" + ChatColor.RESET + ChatColor.BLACK
				+ "\nEach item in the game has aspects on it. "
				+ "You can view the aspects of an item by typing '/aspects' when holding the item in your hand. "
				+ "A list of all aspects can be obtained by typing '/aspects list'."));
		meta.addPage(Language.get("item.zephyronomicon.pg7", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + ""
				+ ChatColor.BOLD + "Casting" + ChatColor.RESET + ChatColor.BLACK + "\nSpells can be cast in two ways: "
				+ "by binding them to a wand with '/bind <spell>' when holding a wand and right clicking "
				+ "or by typing '/cast <spell>'."));
		meta.addPage(Language
				.get("item.zephyronomicon.pg8",
						ChatColor.DARK_AQUA
								+ ""
								+ ChatColor.UNDERLINE
								+ ""
								+ ChatColor.BOLD
								+ "Mana"
								+ ChatColor.RESET
								+ ChatColor.BLACK
								+ "\nWhen you cast spells, some mana is drained. This mana regenerates slowly over time. "
								+ "Your mana can either be viewed on your HUD where the boss health bar would be or by typing '/mana'."));
		meta.addPage(Language.get("item.zephyronomicon.pg9", ChatColor.DARK_AQUA + "" + ChatColor.UNDERLINE + ""
				+ ChatColor.BOLD + "Levelling" + ChatColor.RESET + ChatColor.BLACK
				+ "\nSome spells require you to be a higher level. You level up by casting spells. "
				+ "Each spell gives you a certain amount of XP when you successfully cast them. "
				+ "You can view your current level and progress by typing '/level'."));
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
			if (spell.getRequiredLevel() >= startLevel && spell.getRequiredLevel() <= endLevel) {
				currentText.append(ChatColor.GOLD
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
