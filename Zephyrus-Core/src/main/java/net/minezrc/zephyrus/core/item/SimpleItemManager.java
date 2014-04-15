package net.minezrc.zephyrus.core.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.item.wand.AdvancedWand;
import net.minezrc.zephyrus.core.item.wand.BasicFireWand;
import net.minezrc.zephyrus.core.item.wand.BasicObsidianWand;
import net.minezrc.zephyrus.core.item.wand.BasicWand;
import net.minezrc.zephyrus.core.item.wand.StandardWand;
import net.minezrc.zephyrus.item.Item;
import net.minezrc.zephyrus.item.ItemManager;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Zephyrus - SimpleItemManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleItemManager implements ItemManager {

	private Map<String, Item> itemMap;

	public SimpleItemManager() {
		itemMap = new HashMap<String, Item>();
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
	public void load() {
		Bukkit.getPluginManager().registerEvents(new ItemListener(), Zephyrus.getPlugin());
		registerItem(new BasicWand());
		registerItem(new BasicFireWand());
		registerItem(new BasicObsidianWand());
		registerItem(new StandardWand());
		registerItem(new AdvancedWand());
		registerItem(new SpellBook());
	}

	@Override
	public void unload() {
	}

	@Override
	public void registerItem(Item item) {
		itemMap.put(item.getName(), item);
		if (item instanceof Listener) {
			Bukkit.getPluginManager().registerEvents((Listener) item, Zephyrus.getPlugin());
		}
		ItemStack stack = new ItemStack(item.getMaterial());
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(item.getName());
		meta.setLore(item.getLore());
		for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
			meta.addEnchant(entry.getKey(), entry.getValue(), true);
		}
		stack.setItemMeta(meta);
		Bukkit.addRecipe(item.getRecipe().createRecipe(stack));
	}

}
