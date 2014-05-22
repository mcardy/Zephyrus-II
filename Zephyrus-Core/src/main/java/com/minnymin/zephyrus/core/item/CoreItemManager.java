package com.minnymin.zephyrus.core.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.config.ConfigOptions;
import com.minnymin.zephyrus.core.item.action.BlinkPearl;
import com.minnymin.zephyrus.core.item.action.HoeOfGrowth;
import com.minnymin.zephyrus.core.item.wand.AdvancedWand;
import com.minnymin.zephyrus.core.item.wand.BasicFireWand;
import com.minnymin.zephyrus.core.item.wand.BasicObsidianWand;
import com.minnymin.zephyrus.core.item.wand.BasicWand;
import com.minnymin.zephyrus.core.item.wand.StandardWand;
import com.minnymin.zephyrus.item.Item;
import com.minnymin.zephyrus.item.ItemManager;
import com.minnymin.zephyrus.item.LevelledItem;

/**
 * Zephyrus - SimpleItemManager.java
 * 
 * @author minnymin3
 * 
 */

public class CoreItemManager implements ItemManager {

	private Map<String, Item> itemMap;
	private YmlConfigFile itemConfig;

	public CoreItemManager() {
		itemMap = new HashMap<String, Item>();
		itemConfig = new YmlConfigFile("items.yml");
		itemConfig.saveDefaultConfig();
	}
	
	@Override
	public YmlConfigFile getConfig() {
		return itemConfig;
	}

	@Override
	public Item getItem(String name) {
		for (String s : itemMap.keySet()) {
			if (name.contains(s)) {
				return itemMap.get(s);
			}
		}
		return null;
	}

	@Override
	public Item getItem(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			return getItem(item.getItemMeta().getDisplayName());
		}
		return null;
	}
	
	@Override
	public Item getItemFromBaseName(String name) {
		for (String s : itemMap.keySet()) {
			if (name.equalsIgnoreCase(ChatColor.stripColor(s))) {
				return itemMap.get(s);
			}
		}
		return null;
	}

	@Override
	public void load() {
		Bukkit.getPluginManager().registerEvents(new ItemListener(), Zephyrus.getPlugin());
		registerItem(new BasicWand());
		registerItem(new BasicFireWand());
		registerItem(new BasicObsidianWand());
		registerItem(new StandardWand());
		registerItem(new AdvancedWand());
		registerItem(new SpellBook());
		registerItem(new BlinkPearl());
		registerItem(new HoeOfGrowth());
	}

	@Override
	public void unload() {
	}

	@Override
	public void registerItem(Item item) {
		if (itemConfig.getConfig().getBoolean(item.getDefaultName() + ".Enabled")) {
			itemMap.put(item.getName(), item);
			if (item instanceof Listener) {
				Bukkit.getPluginManager().registerEvents((Listener) item, Zephyrus.getPlugin());
			}
			ItemStack stack = new ItemStack(item.getMaterial());
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(item.getName());
			if (item instanceof LevelledItem) {
				meta.setLore(((LevelledItem)item).getLevelledLore(1));
			} else {
				meta.setLore(item.getLore());
			}
			for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
				meta.addEnchant(entry.getKey(), entry.getValue(), true);
			}
			stack.setItemMeta(meta);
			if (!ConfigOptions.DISABLE_ITEM_CRAFTING) {
				Bukkit.addRecipe(item.getRecipe().createRecipe(stack));
			}
		}
	}

}
