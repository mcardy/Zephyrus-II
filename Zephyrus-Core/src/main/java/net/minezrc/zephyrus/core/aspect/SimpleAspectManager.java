package net.minezrc.zephyrus.core.aspect;

import static net.minezrc.zephyrus.aspect.Aspect.ANIMAL;
import static net.minezrc.zephyrus.aspect.Aspect.ATTACK;
import static net.minezrc.zephyrus.aspect.Aspect.DIRT;
import static net.minezrc.zephyrus.aspect.Aspect.EVIL;
import static net.minezrc.zephyrus.aspect.Aspect.FIRE;
import static net.minezrc.zephyrus.aspect.Aspect.GLASS;
import static net.minezrc.zephyrus.aspect.Aspect.ICE;
import static net.minezrc.zephyrus.aspect.Aspect.LIGHT;
import static net.minezrc.zephyrus.aspect.Aspect.MACHINE;
import static net.minezrc.zephyrus.aspect.Aspect.MAGIC;
import static net.minezrc.zephyrus.aspect.Aspect.METAL;
import static net.minezrc.zephyrus.aspect.Aspect.PLANT;
import static net.minezrc.zephyrus.aspect.Aspect.STONE;
import static net.minezrc.zephyrus.aspect.Aspect.TOOL;
import static net.minezrc.zephyrus.aspect.Aspect.VALUE;
import static net.minezrc.zephyrus.aspect.Aspect.WATER;
import static net.minezrc.zephyrus.aspect.Aspect.WIND;
import static net.minezrc.zephyrus.aspect.Aspect.WOOD;

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
	
	private void add(Material material, AspectList list) {
		add(material, 0, list);
	}
	
	private void add(Material material, int data, AspectList list) {
		map.put(material.name() + ":" + data, list);
	}
	
	@Override
	public AspectList getAspects(ItemStack item) {
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
	
	@Override
	public void load() {
		add(Material.STONE, AspectList.newList(STONE, 2));
		add(Material.GRASS, AspectList.newList().setAspectTypes(DIRT, PLANT).setAspectValues(2, 1));
		add(Material.DIRT, AspectList.newList(DIRT, 2));
		add(Material.COBBLESTONE, AspectList.newList(STONE, 1));
		add(Material.WOOD, AspectList.newList(WOOD, 2));
		add(Material.SAPLING, AspectList.newList().setAspectTypes(PLANT, WOOD).setAspectValues(4, 1));
		add(Material.BEDROCK, AspectList.newList(STONE, 16));
		add(Material.WATER, AspectList.newList(WATER, 8));
		add(Material.STATIONARY_WATER, AspectList.newList(WATER, 8));
		add(Material.LAVA, AspectList.newList(FIRE, 8));
		add(Material.STATIONARY_LAVA, AspectList.newList(FIRE, 8));
		add(Material.SAND, AspectList.newList(DIRT, 2));
		add(Material.GRAVEL, AspectList.newList().setAspectTypes(STONE, DIRT).setAspectValues(1, 1));
		add(Material.GOLD_ORE, AspectList.newList().setAspectTypes(STONE, METAL, VALUE).setAspectValues(2, 2, 2));
		add(Material.IRON_ORE, AspectList.newList().setAspectTypes(STONE, METAL).setAspectValues(2, 4));
		add(Material.COAL_ORE, AspectList.newList().setAspectTypes(STONE, FIRE).setAspectValues(2, 2));
		add(Material.LOG, AspectList.newList().setAspectTypes(WOOD, PLANT).setAspectValues(8, 1));
		add(Material.LEAVES, AspectList.newList(PLANT, 2));
		add(Material.SPONGE, AspectList.newList(PLANT, 1));
		add(Material.GLASS, AspectList.newList(GLASS, 4));
		add(Material.LAPIS_ORE, AspectList.newList().setAspectTypes(VALUE, STONE).setAspectValues(4, 2));
		add(Material.LAPIS_BLOCK, AspectList.newList().setAspectTypes(VALUE).setAspectValues(9));
		add(Material.DISPENSER, AspectList.newList().setAspectTypes(STONE, MACHINE).setAspectValues(7, 8));
		add(Material.SANDSTONE, AspectList.newList().setAspectTypes(DIRT, STONE).setAspectValues(4, 1));
		add(Material.NOTE_BLOCK, AspectList.newList().setAspectTypes(WOOD, MACHINE).setAspectValues(16, 2));
		add(Material.POWERED_RAIL, AspectList.newList().setAspectTypes(METAL, MACHINE).setAspectValues(2, 4));
		add(Material.DETECTOR_RAIL, AspectList.newList().setAspectTypes(METAL, MACHINE).setAspectValues(4, 4));
		add(Material.PISTON_STICKY_BASE, AspectList.newList().setAspectTypes(METAL, WOOD, MACHINE, ANIMAL).setAspectValues(4, 4, 12, 1));
		add(Material.WEB, AspectList.newList(ANIMAL, 1));
		add(Material.LONG_GRASS, AspectList.newList().setAspectTypes(PLANT, WIND).setAspectValues(2, 1));
		add(Material.DEAD_BUSH, AspectList.newList().setAspectTypes(EVIL, PLANT).setAspectValues(2, 1));
		add(Material.PISTON_BASE, AspectList.newList().setAspectTypes(METAL, WOOD, MACHINE).setAspectValues(4, 4, 12));
		add(Material.WOOL, AspectList.newList(ANIMAL, 4));
		add(Material.YELLOW_FLOWER, AspectList.newList(PLANT, 4));
		add(Material.RED_ROSE, AspectList.newList(PLANT, 4));
		add(Material.RED_MUSHROOM, AspectList.newList(PLANT, 2));
		add(Material.BROWN_MUSHROOM, AspectList.newList(PLANT, 2));
		add(Material.GOLD_BLOCK, AspectList.newList().setAspectTypes(VALUE, METAL).setAspectValues(36, 36));
		add(Material.IRON_BLOCK, AspectList.newList(METAL, 72));
		add(Material.DOUBLE_STEP, 0,  AspectList.newList(STONE, 2));
		add(Material.DOUBLE_STEP, 2, AspectList.newList(WOOD, 2));
		add(Material.DOUBLE_STEP, 6, AspectList.newList().setAspectTypes(STONE, EVIL).setAspectValues(2, 2));
		add(Material.STEP, 0, AspectList.newList(STONE, 1));
		add(Material.STEP, 2, AspectList.newList(WOOD, 1));
		add(Material.STEP, 6, AspectList.newList().setAspectTypes(STONE, EVIL).setAspectValues(1, 1));
		add(Material.BRICK, AspectList.newList(STONE, 4));
		add(Material.TNT, AspectList.newList(FIRE, 4));
		add(Material.BOOKSHELF, AspectList.newList().setAspectTypes(WOOD, MAGIC).setAspectValues(12, 3));
		add(Material.MOSSY_COBBLESTONE, AspectList.newList().setAspectTypes(STONE, PLANT, EVIL).setAspectValues(1, 1, 1));
		add(Material.OBSIDIAN, AspectList.newList().setAspectTypes(STONE, WATER, FIRE).setAspectValues(4, 1, 1));
		add(Material.TORCH, AspectList.newList(LIGHT, 1));
		add(Material.FIRE, AspectList.newList(FIRE, 4));
		add(Material.MOB_SPAWNER, AspectList.newList().setAspectTypes(EVIL, ATTACK).setAspectValues(4, 2));
		add(Material.WOOD_STAIRS, AspectList.newList(WOOD, 1));
		add(Material.CHEST, AspectList.newList(WOOD, 16));
		add(Material.REDSTONE_WIRE, AspectList.newList(MACHINE, 1));
		add(Material.DIAMOND_ORE, AspectList.newList().setAspectTypes(STONE, VALUE, GLASS).setAspectValues(2, 4, 1));
		add(Material.DIAMOND_BLOCK, AspectList.newList().setAspectTypes(VALUE, GLASS).setAspectValues(72, 18));
		add(Material.WORKBENCH, AspectList.newList().setAspectTypes(WOOD, TOOL).setAspectValues(8, 2));
		add(Material.CROPS, AspectList.newList(PLANT, 1));
		add(Material.SOIL, AspectList.newList(DIRT, 2));
		add(Material.FURNACE, AspectList.newList().setAspectTypes(STONE, FIRE).setAspectValues(8, 1));
		add(Material.BURNING_FURNACE, AspectList.newList().setAspectTypes(STONE, FIRE).setAspectValues(8, 4));
		add(Material.SIGN_POST, AspectList.newList(WOOD, 4));
		add(Material.WOOD_DOOR, AspectList.newList(WOOD, 8));
		add(Material.LADDER, AspectList.newList(WOOD, 2));
		add(Material.RAILS, AspectList.newList().setAspectTypes(METAL, MACHINE).setAspectValues(1, 1));
		add(Material.COBBLESTONE_STAIRS, AspectList.newList(STONE, 1));
		add(Material.WALL_SIGN, AspectList.newList(WOOD, 2));
		add(Material.LEVER, AspectList.newList().setAspectTypes(WOOD, STONE, MACHINE).setAspectValues(1, 1, 1));
		add(Material.STONE_PLATE, AspectList.newList().setAspectTypes(STONE, MACHINE).setAspectValues(4, 1));
		add(Material.IRON_DOOR, AspectList.newList().setAspectTypes(METAL, MACHINE).setAspectValues(24, 1));
		add(Material.WOOD_PLATE, AspectList.newList().setAspectTypes(WOOD, MACHINE).setAspectValues(4, 1));
		add(Material.REDSTONE_ORE, AspectList.newList().setAspectTypes(STONE, MACHINE).setAspectValues(2, 4));
		add(Material.GLOWING_REDSTONE_ORE, AspectList.newList().setAspectTypes(STONE, MACHINE).setAspectValues(2, 4));
		add(Material.REDSTONE_TORCH_OFF, AspectList.newList(MACHINE, 1));
		add(Material.REDSTONE_TORCH_ON, AspectList.newList(MACHINE, 1));
		add(Material.STONE_BUTTON, AspectList.newList().setAspectTypes(MACHINE, STONE).setAspectValues(1, 1));
		add(Material.SNOW, AspectList.newList(ICE, 2));
		add(Material.ICE, AspectList.newList(ICE, 1));
		add(Material.SNOW_BLOCK, AspectList.newList(ICE, 2));
		add(Material.CACTUS, AspectList.newList().setAspectTypes(PLANT, ATTACK).setAspectValues(2, 1));
		add(Material.CLAY, AspectList.newList(DIRT, 4));
		add(Material.SUGAR_CANE_BLOCK, AspectList.newList(PLANT, 2));
		add(Material.JUKEBOX, AspectList.newList().setAspectTypes(VALUE, WOOD, MACHINE).setAspectValues(8, 16, 2));
		add(Material.FENCE, AspectList.newList(WOOD, 2));
		add(Material.PUMPKIN, AspectList.newList(PLANT, 4));
		
		add(Material.ARROW, AspectList.newList(ATTACK, 4));
	}

	@Override
	public void unload() {
	}

}
