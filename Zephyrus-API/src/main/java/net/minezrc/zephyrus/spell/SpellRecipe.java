package net.minezrc.zephyrus.spell;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - SpellRecipe.java
 * 
 * @author minnymin3
 * 
 */

public class SpellRecipe {

	private Set<ItemStack> items;

	/**
	 * Creates a new SpellRecipe with the given items as the recipe
	 * 
	 * @param items The items that this recipe contains
	 */
	public SpellRecipe(ItemStack... items) {
		Set<ItemStack> s = new HashSet<ItemStack>();
		for (ItemStack i : items) {
			s.add(i);
		}
		this.items = s;
	}

	public Set<ItemStack> getItems() {
		return items;
	}

	public boolean isEqual(Set<ItemStack> items) {
		return this.getItems().equals(items);
	}

	public boolean isEqual(SpellRecipe recipe) {
		return isEqual(recipe.getItems());
	}

}
