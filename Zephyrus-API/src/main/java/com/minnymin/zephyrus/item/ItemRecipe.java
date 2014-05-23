package com.minnymin.zephyrus.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

/**
 * Zephyrus - ItemRecipe.java<br>
 * Represents a recipe
 * 
 * @author minnymin3
 * 
 */

public class ItemRecipe {

	private Map<Character, ItemStack> ingredients;
	private String[] shape;

	public ItemRecipe() {
		ingredients = new HashMap<Character, ItemStack>();
	}

	/**
	 * Creates a Bukkit recipe with the given ItemStack as the output
	 * 
	 * @param output The output item
	 * @return A new Bukkit recipe
	 */
	public ShapedRecipe createRecipe(ItemStack output) {
		ShapedRecipe recipe = new ShapedRecipe(output);
		recipe.shape(shape);
		for (Entry<Character, ItemStack> entry : ingredients.entrySet()) {
			recipe.setIngredient(entry.getKey(), entry.getValue().getData());
		}
		return recipe;
	}

	/**
	 * Sets the ingredient of a character in the shape
	 * 
	 * @param key The character in the shape
	 * @param ingredient The material of the ingredient
	 */
	public void setIngredient(char key, Material ingredient) {
		ingredients.put(key, new ItemStack(ingredient));
	}

	/**
	 * Sets the ingredient of a character in the shape
	 * 
	 * @param key The character in the shape
	 * @param data The material data of the ingredient
	 */
	public void setIngredient(char key, MaterialData data) {
		
	}
	
	/**
	 * Set the shape of this recipe to the specified rows.<br>
	 * Each character represents a different ingredient; exactly what each
	 * character represents is set separately. The first row supplied
	 * corresponds with the upper most part of the recipe on the workbench e.g.
	 * if all three rows are supplies the first string represents the top row on
	 * the workbench.
	 * 
	 * @param shape The shape of the recipe
	 */
	public void setShape(String... shape) {
		this.shape = shape;
	}

}
