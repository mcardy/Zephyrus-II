package com.minnymin.zephyrus.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;

/**
 * Zephyrus - Item.java <br>
 * Represents a custom item that can be registered with Zephyrus
 * 
 * @author minnymin3
 * 
 */

public abstract class Item {

	protected final String defaultName;
	private String name;
	private Material mat;
	private int craftingLevel;
	private List<String> lore;
	
	public Item(String name, int craftingLevel, Material mat, List<String> lore) {
		defaultName = ChatColor.stripColor(name).replace(" ", "-").toLowerCase();
		YmlConfigFile yml = Zephyrus.getItemManager().getConfig();
		yml.addDefaults(defaultName + ".Enabled", true);
		yml.addDefaults(defaultName + ".Name", translateToAlternate(name));
		yml.addDefaults(defaultName + ".Lore", translateToAlternate(lore));
		yml.addDefaults(defaultName + ".CraftingLevel", craftingLevel);
		yml.saveConfig();
		
		if (this instanceof Configurable) {
			Iterator<Entry<String, Object>> iter = ((Configurable) this).getDefaultConfiguration().entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, Object> value = iter.next();
				yml.addDefaults(defaultName + "." + value.getKey(), value.getValue());
			}
		}
		
		ConfigurationSection config = yml.getConfig().getConfigurationSection(defaultName);
		this.name = translateFromAlternate(config.getString("Name"));
		this.lore = translateFromAlternate(config.getStringList("Lore"));
		this.craftingLevel = config.getInt("CraftingLevel");
		this.mat = mat;
	}
	
	protected String translateToAlternate(String s) {
		return s.replace(ChatColor.COLOR_CHAR, '$');
	}
	
	protected List<String> translateToAlternate(List<String> array) {
		List<String> list = new ArrayList<String>();
		for (String s : array) {
			list.add(s.replace(ChatColor.COLOR_CHAR, '$'));
		}
		return list;
	}
	
	protected String translateFromAlternate(String s) {
		return ChatColor.translateAlternateColorCodes('$', s);
	}
	
	protected List<String> translateFromAlternate(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			list.set(i, ChatColor.translateAlternateColorCodes('$', list.get(i)));
		}
		return list;
	}
	
	/**
	 * The minimum required level to craft the item
	 * 
	 * @return An int of the crafting level
	 */
	public int getCraftingLevel() {
		return craftingLevel;
	}

	/**
	 * The enchantments applied to the item
	 * 
	 * @return A map of Enchantment and Integer
	 */
	public Map<Enchantment, Integer> getEnchantments() {
		return new HashMap<Enchantment, Integer>();
	}

	/**
	 * The lore of the item
	 * 
	 * @return The ItemStack lore
	 */
	public List<String> getLore() {
		return lore;
	}

	/**
	 * The material of the item
	 * 
	 * @return The ItemStack's material
	 */
	public Material getMaterial() {
		return mat;
	}

	public String getDefaultName() {
		return defaultName;
	}
	
	/**
	 * The display name of the item
	 * 
	 * @return The ItemStack name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The recipe of the item
	 * 
	 * @return An ItemRecipe object
	 */
	public abstract ItemRecipe getRecipe();

}
