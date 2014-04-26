package net.minezrc.zephyrus.spell;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;

import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - SpellRecipe.java<br>
 * A class to represent a spell recipe
 * 
 * @author minnymin3
 * 
 */

public class SpellRecipe {

	private AspectList list;

	/**
	 * Creates a new SpellRecipe with the given aspect list
	 * 
	 * @param list The aspect list to craft with
	 */
	public SpellRecipe(AspectList list) {
		this.list = list;
	}

	/**
	 * Gets the list of aspects in this recipe
	 * 
	 * @return The list of this recipe
	 */
	public AspectList getList() {
		return list;
	}

	/**
	 * Checks if the recipe is satisfied with the given items
	 * 
	 * @param items The items to check
	 * @return True if the recipe is satisfied
	 */
	public boolean isSatisfied(Set<ItemStack> items) {
		Map<Aspect, Integer> map = new HashMap<Aspect, Integer>();
		for (ItemStack i : items) {
			Map<Aspect, Integer> itemMap = Zephyrus.getAspectManager().getAspects(i).getAspectMap();
			for (Aspect aspect : itemMap.keySet()) {
				if (map.containsKey(aspect)) {
					map.put(aspect, itemMap.get(aspect) + map.get(aspect));
				} else {
					map.put(aspect, itemMap.get(aspect));
				}
			}
		}
		Map<Aspect, Integer> recipe = list.getAspectMap();
		for (Aspect aspect : list.getAspectMap().keySet()) {
			if (!map.containsKey(aspect)) {
				return false;
			}
			if (map.get(aspect) < recipe.get(aspect)) {
				return false;
			}
		}
		return true;
	}

}
