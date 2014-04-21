package net.minezrc.zephyrus.core.aspect;

import net.minezrc.zephyrus.aspect.Aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.aspect.AspectManager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - SimpleAspectManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleAspectManager implements AspectManager {

	private Map<String, AspectList> map;

	public SimpleAspectManager() {
		map = new HashMap<String, AspectList>();
	}

	private void add(Material material, Object... values) {
		add(material, 0, AspectList.newList(values));
	}

	private void add(Material material, int data, Object... values) {
		add(material, data, AspectList.newList(values));
	}

	private void add(Material material, int data, AspectList list) {
		map.put(material.name() + ":" + data, list);
	}

	@Override
	public AspectList getAspects(ItemStack item) {
		if (item != null) {
			String name = item.getType() + ":" + item.getDurability();
			AspectList list;
			if (map.containsKey(name)) {
				list = map.get(name);
			} else {
				list = map.get(item.getType() + ":0");
			}
			List<Integer> values = new ArrayList<Integer>();
			for (int i : list.getAmountList()) {
				values.add(i * item.getAmount());
			}
			return AspectList.newList().setAspectLists(list.getTypeList(), values);
		}
		return AspectList.newList();
	}

	@Override
	public void load() {
		add(Material.ACACIA_STAIRS, Aspect.WOOD, 2);
		add(Material.ACTIVATOR_RAIL, Aspect.MACHINE, 4, Aspect.METAL, 1);
		add(Material.ANVIL, Aspect.METAL, 32, Aspect.CONSTRUCT, 8, Aspect.TOOL, 4);
		add(Material.APPLE, Aspect.LIFE, 2, Aspect.PLANT, 1);
		add(Material.ARROW, Aspect.WEAPON, 4, Aspect.WOOD, 1);

		add(Material.BAKED_POTATO, Aspect.LIFE, 2, Aspect.PLANT, 1);
		add(Material.BEACON, Aspect.MYSTICAL, 32, Aspect.VALUE, 64, Aspect.ENERGY, 128);
		add(Material.BED, Aspect.WOOD, 6, Aspect.LIFE, 1);
		add(Material.BIRCH_WOOD_STAIRS, Aspect.WOOD, 2);
		add(Material.BLAZE_POWDER, Aspect.FIRE, 4, Aspect.MYSTICAL, 1, Aspect.ENERGY, 1);
		add(Material.BLAZE_ROD, Aspect.FIRE, 8, Aspect.MYSTICAL, 2, Aspect.ENERGY, 2);
		add(Material.BOAT, Aspect.WOOD, 10, Aspect.WATER, 5, Aspect.MOVEMENT, 10);
		add(Material.BONE, Aspect.EVIL, 1, Aspect.FLESH, 4);
		add(Material.BOOK, Aspect.KNOWLEDGE, 8, Aspect.PLANT, 4);
		add(Material.BOOK_AND_QUILL, Aspect.KNOWLEDGE, 8, Aspect.BEAST, 1, Aspect.PLANT);
		add(Material.BOOKSHELF, Aspect.KNOWLEDGE, 24, Aspect.WOOD, 12, Aspect.PLANT, 12);
		add(Material.BOW, Aspect.WEAPON, 8, Aspect.PLANT, 2, Aspect.WOOD, 3);
		add(Material.BOWL, Aspect.WOOD, 3, Aspect.VOID, 1);
		add(Material.BREAD, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.BREWING_STAND_ITEM, Aspect.FIRE, 2, Aspect.WATER, 2, Aspect.CONSTRUCT);
		add(Material.BRICK, Aspect.STONE, 4, Aspect.EARTH, 4);
		add(Material.BRICK_STAIRS, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(Material.BROWN_MUSHROOM, Aspect.LIFE, 4, Aspect.PLANT, 4);
		add(Material.BUCKET, Aspect.METAL, 12, Aspect.VOID, 4);

		add(Material.CACTUS, Aspect.WEAPON, 1, Aspect.PLANT, 4, Aspect.LIFE, 1);
		add(Material.CAKE, Aspect.LIFE, 4);
		add(Material.CARPET, Aspect.BEAST, 1);
		add(Material.CARROT_ITEM, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.CARROT_STICK, Aspect.WOOD, 1, Aspect.LIFE, 2, Aspect.PLANT, 2, Aspect.MOVEMENT, 2);
		add(Material.CAULDRON_ITEM, Aspect.METAL, 20, Aspect.VOID, 8, Aspect.WATER, 4);
		add(Material.CHEST, Aspect.WOOD, 16, Aspect.VOID, 4);
		add(Material.CLAY, Aspect.EARTH, 8);
		add(Material.CLAY, Aspect.EARTH, 2);
		add(Material.CLAY_BRICK, Aspect.EARTH, 1, Aspect.STONE, 1);
		add(Material.COAL, Aspect.FIRE, 2, Aspect.ENERGY, 2);
		add(Material.COAL_BLOCK, Aspect.FIRE, 18, Aspect.ENERGY, 18);
		add(Material.COAL_ORE, Aspect.FIRE, 2, Aspect.ENERGY, 2, Aspect.STONE, 2);
		add(Material.COBBLESTONE, Aspect.DESTRUCTION, 1, Aspect.STONE, 1);
		add(Material.COBBLE_WALL, Aspect.DESTRUCTION, 1, Aspect.STONE, 1);
		add(Material.COBBLESTONE_STAIRS, Aspect.DESTRUCTION, 1, Aspect.STONE, 1);
		add(Material.COCOA, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.COMPASS, Aspect.METAL, 4, Aspect.KNOWLEDGE, 1, Aspect.MOVEMENT, 1, Aspect.MACHINE, 2);
		add(Material.COOKED_BEEF, Aspect.FLESH, 4, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(Material.COOKED_CHICKEN, Aspect.FLESH, 4, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(Material.COOKED_FISH, Aspect.FLESH, 4, Aspect.LIFE, 2, Aspect.BEAST, 2);
		add(Material.COOKIE, Aspect.LIFE, 4, Aspect.PLANT, 1);
		
		
	}

	@Override
	public void unload() {
	}

}
