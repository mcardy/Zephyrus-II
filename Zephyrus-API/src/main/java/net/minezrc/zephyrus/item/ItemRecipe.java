package net.minezrc.zephyrus.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

/**
 * Zephyrus - ItemRecipe.java
 * 
 * @author minnymin3
 * 
 */

public class ItemRecipe {

	private String[] shape;
	private Map<Character, Material> ingredients;
	
	public ItemRecipe() {
		ingredients = new HashMap<Character, Material>();
	}
	
	public ShapedRecipe createRecipe(ItemStack output) {
		ShapedRecipe recipe = new ShapedRecipe(output);
		recipe.shape(shape);
		for (Entry<Character, Material> entry : ingredients.entrySet()) {
			recipe.setIngredient(entry.getKey(), entry.getValue());
		}
		return recipe;
	}
	
	public void setIngredient(char key, Material ingredient) {
		ingredients.put(key, ingredient);
	}
	
	public void setShape(String... shape) {
		this.shape = shape;
	}
	
}
