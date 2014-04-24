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

	private void add(int data, Material material, Object... values) {
		add(material, data, AspectList.newList(values));
	}

	private void add(Material material, int data, AspectList list) {
		map.put(material.name() + ":" + data, list);
	}

	@Override
	public AspectList getAspects(ItemStack item) {
		if (item != null) {
			String name = item.getType().name() + ":" + item.getDurability();
			AspectList list;
			if (map.containsKey(name)) {
				list = map.get(name);
			} else {
				list = map.get(item.getType().name() + ":0");
			}
			if (list == null) {
				return null;
			}
			List<Integer> values = new ArrayList<Integer>();
			for (int i : list.getAmountList()) {
				values.add(i * item.getAmount());
			}
			return AspectList.newList().setAspectLists(list.getTypeList(), values);
		}
		return null;
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
		add(Material.BOOK_AND_QUILL, Aspect.KNOWLEDGE, 8, Aspect.BEAST, 1, Aspect.PLANT, 4);
		add(Material.BOOKSHELF, Aspect.KNOWLEDGE, 24, Aspect.WOOD, 12, Aspect.PLANT, 12);
		add(Material.BOW, Aspect.WEAPON, 8, Aspect.PLANT, 2, Aspect.WOOD, 3);
		add(Material.BOWL, Aspect.WOOD, 3, Aspect.VOID, 1);
		add(Material.BREAD, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.BREWING_STAND_ITEM, Aspect.FIRE, 2, Aspect.WATER, 2, Aspect.CONSTRUCT);
		add(Material.BRICK, Aspect.STONE, 4, Aspect.EARTH, 4);
		add(Material.BRICK_STAIRS, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(Material.BROWN_MUSHROOM, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.BUCKET, Aspect.METAL, 24, Aspect.VOID, 4);

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

		add(Material.DARK_OAK_STAIRS, Aspect.WOOD, 2);
		add(Material.DAYLIGHT_DETECTOR, Aspect.LIGHT, 4, Aspect.MACHINE, 4);
		add(Material.DEAD_BUSH, Aspect.EVIL, 1, Aspect.PLANT, 1);
		add(Material.DETECTOR_RAIL, Aspect.MACHINE, 4, Aspect.METAL, 1);
		add(Material.DIAMOND, Aspect.CLEAR, 8, Aspect.VALUE, 8);
		add(Material.DIAMOND_AXE, Aspect.CLEAR, 24, Aspect.VALUE, 24, Aspect.TOOL, 8);
		add(Material.DIAMOND, Aspect.CLEAR, 48, Aspect.VALUE, 48, Aspect.DEFENSE, 8);
		add(Material.DIAMOND_BLOCK, Aspect.CLEAR, 72, Aspect.VALUE, 72);
		add(Material.DIAMOND_BOOTS, Aspect.CLEAR, 32, Aspect.VALUE, 32, Aspect.DEFENSE, 8);
		add(Material.DIAMOND_CHESTPLATE, Aspect.CLEAR, 64, Aspect.VALUE, 64, Aspect.DEFENSE, 8);
		add(Material.DIAMOND_HELMET, Aspect.CLEAR, 40, Aspect.VALUE, 40, Aspect.DEFENSE, 8);
		add(Material.DIAMOND_HOE, Aspect.CLEAR, 16, Aspect.VALUE, 16, Aspect.TOOL, 8);
		add(Material.DIAMOND_LEGGINGS, Aspect.CLEAR, 56, Aspect.VALUE, 56, Aspect.TOOL, 8);
		add(Material.DIAMOND_ORE, Aspect.STONE, 2, Aspect.CLEAR, 4, Aspect.VALUE, 4);
		add(Material.DIAMOND_PICKAXE, Aspect.CLEAR, 24, Aspect.VALUE, 24, Aspect.TOOL, 8);
		add(Material.DIAMOND_SPADE, Aspect.CLEAR, 8, Aspect.VALUE, 8, Aspect.TOOL, 8);
		add(Material.DIAMOND_SWORD, Aspect.CLEAR, 16, Aspect.VALUE, 16, Aspect.WEAPON, 8);
		add(Material.DIODE, Aspect.MACHINE, 8, Aspect.STONE, 4, Aspect.ENERGY, 2);
		add(Material.DIRT, Aspect.EARTH, 2);
		add(Material.DISPENSER, Aspect.STONE, 8, Aspect.MACHINE, 8);
		add(Material.DRAGON_EGG, Aspect.ENDER, 128, Aspect.EVIL, 128, Aspect.LIFE, 128, Aspect.DARKNESS, 128,
				Aspect.LIGHT, 128, Aspect.BEAST, 128, Aspect.VOID, 128);
		add(Material.DROPPER, Aspect.STONE, 8, Aspect.MACHINE, 8);

		add(Material.EGG, Aspect.LIFE, 8, Aspect.BEAST, 2);
		add(Material.EMERALD, Aspect.CLEAR, 8, Aspect.VALUE, 8, Aspect.CONSTRUCT, 4);
		add(Material.EMERALD_BLOCK, Aspect.CLEAR, 72, Aspect.VALUE, 72, Aspect.CONSTRUCT, 36);
		add(Material.EMERALD_ORE, Aspect.STONE, 2, Aspect.CLEAR, 4, Aspect.VALUE, 4);
		add(Material.EMPTY_MAP, Aspect.TOOL, 8, Aspect.PLANT, 4, Aspect.KNOWLEDGE, 8);
		add(Material.ENCHANTED_BOOK, Aspect.MYSTICAL, 8, Aspect.KNOWLEDGE, 8);
		add(Material.ENCHANTMENT_TABLE, Aspect.MYSTICAL, 16, Aspect.KNOWLEDGE, 16);
		add(Material.ENDER_CHEST, Aspect.VOID, 8, Aspect.ENDER, 8);
		add(Material.ENDER_PEARL, Aspect.ENDER, 8);
		add(Material.ENDER_STONE, Aspect.ENDER, 1, Aspect.STONE, 1);
		add(Material.EXP_BOTTLE, Aspect.KNOWLEDGE, 2);
		add(Material.EXPLOSIVE_MINECART, Aspect.DESTRUCTION, 16, Aspect.METAL, 40, Aspect.MACHINE, 8, Aspect.MOVEMENT,
				8, Aspect.ENERGY, 8);
		add(Material.EYE_OF_ENDER, Aspect.FIRE, 2, Aspect.ENDER, 8, Aspect.MYSTICAL, 4);

		add(Material.FEATHER, Aspect.WIND, 4, Aspect.BEAST, 2);
		add(Material.FENCE, Aspect.WOOD, 3);
		add(Material.FENCE_GATE, Aspect.WOOD, 8, Aspect.MACHINE, 2);
		add(Material.FERMENTED_SPIDER_EYE, Aspect.EVIL, 2, Aspect.BEAST, 1, Aspect.FLESH, 1);
		add(Material.FIREBALL, Aspect.DESTRUCTION, 4, Aspect.FIRE, 4);
		add(Material.FIREWORK, Aspect.FIRE, 2, Aspect.ENERGY, 4, Aspect.LIGHT, 4);
		add(Material.FIREWORK_CHARGE, Aspect.FIRE, 2, Aspect.ENERGY, 4, Aspect.LIGHT, 4);
		add(Material.FISHING_ROD, Aspect.TOOL, 4);
		add(Material.FLINT, Aspect.STONE, 2);
		add(Material.FLINT_AND_STEEL, Aspect.STONE, 2, Aspect.METAL, 4, Aspect.FIRE, 2);
		add(Material.FLOWER_POT_ITEM, Aspect.STONE, 4);
		add(Material.FURNACE, Aspect.STONE, 8, Aspect.FIRE, 1);

		add(Material.GHAST_TEAR, Aspect.BEAST, 2, Aspect.MYSTICAL, 2, Aspect.FIRE, 2, Aspect.EVIL, 2);
		add(Material.GLASS, Aspect.CLEAR, 3);
		add(Material.GLASS_BOTTLE, Aspect.CLEAR, 3);
		add(Material.GLOWSTONE, Aspect.STONE, 1, Aspect.LIGHT, 8);
		add(Material.GLOWSTONE_DUST, Aspect.LIGHT, 2);
		add(Material.GOLD_INGOT, Aspect.METAL, 4, Aspect.VALUE, 4);
		add(Material.GOLD_AXE, Aspect.METAL, 12, Aspect.VALUE, 12, Aspect.TOOL, 2);
		add(Material.GOLD_BARDING, Aspect.METAL, 20, Aspect.VALUE, 12, Aspect.DEFENSE, 2);
		add(Material.GOLD_BLOCK, Aspect.METAL, 36, Aspect.VALUE, 36);
		add(Material.GOLD_BOOTS, Aspect.METAL, 16, Aspect.VALUE, 16, Aspect.DEFENSE, 2);
		add(Material.GOLD_CHESTPLATE, Aspect.METAL, 32, Aspect.VALUE, 32, Aspect.DEFENSE, 2);
		add(Material.GOLD_HELMET, Aspect.METAL, 20, Aspect.VALUE, 20, Aspect.DEFENSE, 2);
		add(Material.GOLD_HOE, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.TOOL, 2);
		add(Material.GOLD_LEGGINGS, Aspect.METAL, 28, Aspect.VALUE, 28, Aspect.DEFENSE, 2);
		add(Material.GOLD_NUGGET, Aspect.METAL, 1);
		add(Material.GOLD_ORE, Aspect.STONE, 2, Aspect.VALUE, 2, Aspect.METAL, 2);
		add(Material.GOLD_PICKAXE, Aspect.METAL, 12, Aspect.VALUE, 12, Aspect.TOOL, 2);
		add(Material.GOLD_PLATE, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.MACHINE, 4);
		add(Material.GOLD_SPADE, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.TOOL, 2);
		add(Material.GOLD_SWORD, Aspect.METAL, 8, Aspect.VALUE, 8, Aspect.WEAPON, 2);
		add(Material.GOLDEN_APPLE, Aspect.METAL, 4, Aspect.VALUE, 4, Aspect.LIFE, 8);
		add(Material.GOLDEN_CARROT, Aspect.METAL, 4, Aspect.VALUE, 4, Aspect.LIFE, 2);
		add(Material.GRASS, Aspect.EARTH, 1, Aspect.PLANT, 1);
		add(Material.GRAVEL, Aspect.EARTH, 1, Aspect.STONE, 1);
		add(Material.GRILLED_PORK, Aspect.FLESH, 4, Aspect.BEAST, 2, Aspect.LIFE, 2);

		add(Material.HARD_CLAY, Aspect.STONE, 2);
		add(Material.HAY_BLOCK, Aspect.PLANT, 2);
		add(Material.HOPPER, Aspect.METAL, 24, Aspect.MACHINE, 8, Aspect.VOID, 4);
		add(Material.HOPPER_MINECART, Aspect.METAL, 48, Aspect.MACHINE, 16, Aspect.VOID, 4, Aspect.MOVEMENT, 8);

		add(Material.ICE, Aspect.ICE, 2);
		add(0, Material.INK_SACK, Aspect.DARKNESS, 1, Aspect.BEAST, 1);
		add(1, Material.INK_SACK, Aspect.PLANT, 1);
		add(2, Material.INK_SACK, Aspect.PLANT, 1);
		add(3, Material.INK_SACK, Aspect.PLANT, 1);
		add(4, Material.INK_SACK, Aspect.VALUE, 1);
		add(5, Material.INK_SACK, Aspect.PLANT, 1);
		add(6, Material.INK_SACK, Aspect.PLANT, 1);
		add(7, Material.INK_SACK, Aspect.PLANT, 1);
		add(8, Material.INK_SACK, Aspect.PLANT, 1);
		add(9, Material.INK_SACK, Aspect.PLANT, 1);
		add(10, Material.INK_SACK, Aspect.PLANT, 1);
		add(11, Material.INK_SACK, Aspect.PLANT, 1);
		add(12, Material.INK_SACK, Aspect.PLANT, 1);
		add(13, Material.INK_SACK, Aspect.PLANT, 1);
		add(14, Material.INK_SACK, Aspect.PLANT, 1);
		add(15, Material.INK_SACK, Aspect.FLESH, 1);
		add(Material.IRON_INGOT, Aspect.METAL, 8);
		add(Material.IRON_AXE, Aspect.METAL, 24, Aspect.TOOL, 4);
		add(Material.IRON_BARDING, Aspect.METAL, 40, Aspect.DEFENSE, 4);
		add(Material.IRON_BLOCK, Aspect.METAL, 72);
		add(Material.IRON_BOOTS, Aspect.METAL, 32, Aspect.DEFENSE, 4);
		add(Material.IRON_CHESTPLATE, Aspect.METAL, 64, Aspect.DEFENSE, 4);
		add(Material.IRON_DOOR, Aspect.METAL, 48, Aspect.MACHINE, 4);
		add(Material.IRON_FENCE, Aspect.METAL, 3);
		add(Material.IRON_HELMET, Aspect.METAL, 40, Aspect.DEFENSE, 4);
		add(Material.IRON_HOE, Aspect.METAL, 16, Aspect.TOOL, 4);
		add(Material.IRON_LEGGINGS, Aspect.METAL, 56, Aspect.DEFENSE, 4);
		add(Material.IRON_ORE, Aspect.STONE, 2, Aspect.METAL, 4);
		add(Material.IRON_PICKAXE, Aspect.METAL, 24, Aspect.TOOL, 4);
		add(Material.IRON_PLATE, Aspect.METAL, 16, Aspect.MACHINE, 4);
		add(Material.IRON_SPADE, Aspect.METAL, 8, Aspect.TOOL, 4);
		add(Material.IRON_SWORD, Aspect.METAL, 16, Aspect.WEAPON, 4);
		add(Material.ITEM_FRAME, Aspect.WOOD, 8);

		add(Material.JACK_O_LANTERN, Aspect.LIGHT, 8, Aspect.LIFE, 8, Aspect.PLANT, 8);
		add(Material.JUKEBOX, Aspect.VALUE, 8, Aspect.WOOD, 8, Aspect.MACHINE, 8);
		add(Material.JUNGLE_WOOD_STAIRS, Aspect.WOOD, 2);

		add(Material.LADDER, Aspect.WOOD, 2);
		add(Material.LAPIS_BLOCK, Aspect.VALUE, 9);
		add(Material.LAPIS_ORE, Aspect.STONE, 2, Aspect.VALUE, 4);
		add(Material.LAVA_BUCKET, Aspect.FIRE, 8, Aspect.METAL, 24);
		add(Material.LEASH, Aspect.BEAST, 2);
		add(Material.LEATHER, Aspect.BEAST, 2);
		add(Material.LEATHER_BOOTS, Aspect.BEAST, 8, Aspect.DEFENSE, 2);
		add(Material.LEATHER_CHESTPLATE, Aspect.BEAST, 16, Aspect.DEFENSE, 2);
		add(Material.LEATHER_HELMET, Aspect.BEAST, 10, Aspect.DEFENSE, 2);
		add(Material.LEATHER_LEGGINGS, Aspect.BEAST, 14, Aspect.DEFENSE, 2);
		add(Material.LEAVES, Aspect.PLANT, 1);
		add(Material.LEAVES_2, Aspect.PLANT, 1);
		add(Material.LEVER, Aspect.MACHINE, 2);
		add(Material.LOG, Aspect.WOOD, 8);
		add(Material.LOG_2, Aspect.WOOD, 8);
		add(Material.LONG_GRASS, Aspect.WIND, 1, Aspect.PLANT, 1);

		add(Material.MAGMA_CREAM, Aspect.BEAST, 2, Aspect.FIRE, 2);
		add(Material.MAP, Aspect.TOOL, 8, Aspect.PLANT, 4, Aspect.KNOWLEDGE, 8);
		add(Material.MELON, Aspect.LIFE, 1, Aspect.PLANT, 1);
		add(Material.MELON_BLOCK, Aspect.LIFE, 8, Aspect.PLANT, 8);
		add(Material.MELON_SEEDS, Aspect.LIFE, 1);
		add(Material.MILK_BUCKET, Aspect.METAL, 24, Aspect.BEAST, 4);
		add(Material.MINECART, Aspect.METAL, 40, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8);
		add(Material.MOSSY_COBBLESTONE, Aspect.STONE, 1, Aspect.PLANT, 1);
		add(Material.MUSHROOM_SOUP, Aspect.LIFE, 2, Aspect.PLANT, 4);
		add(Material.MYCEL, Aspect.EARTH, 1, Aspect.PLANT, 1, Aspect.MYSTICAL, 1);

		add(Material.NAME_TAG, Aspect.BEAST);
		add(Material.NETHER_BRICK, Aspect.EVIL, 1, Aspect.STONE, 4, Aspect.FIRE, 1);
		add(Material.NETHER_BRICK_ITEM, Aspect.STONE, 1);
		add(Material.NETHER_BRICK_STAIRS, Aspect.EVIL, 1, Aspect.STONE, 4, Aspect.FIRE, 1);
		add(Material.NETHER_FENCE, Aspect.EVIL, 1, Aspect.STONE, 4, Aspect.FIRE, 1);
		add(Material.NETHER_STALK, Aspect.EVIL, 2, Aspect.PLANT, 2);
		add(Material.NETHER_STAR, Aspect.MYSTICAL, 128);
		add(Material.NETHER_WARTS, Aspect.EVIL, 2, Aspect.PLANT, 2);
		add(Material.NETHERRACK, Aspect.FIRE, 1, Aspect.STONE, 1);
		add(Material.NOTE_BLOCK, Aspect.WOOD, 8, Aspect.MACHINE, 8);

		add(Material.OBSIDIAN, Aspect.DARKNESS, 2, Aspect.STONE, 2);

		add(Material.PACKED_ICE, Aspect.ICE, 4);
		add(Material.PAINTING, Aspect.KNOWLEDGE, 1, Aspect.WOOD, 1);
		add(Material.PAPER, Aspect.PLANT, 2, Aspect.KNOWLEDGE, 1);
		add(Material.PISTON_BASE, Aspect.MACHINE, 8, Aspect.STONE, 8, Aspect.WOOD, 4, Aspect.ENERGY, 4,
				Aspect.MOVEMENT, 4);
		add(Material.PISTON_STICKY_BASE, Aspect.MACHINE, 8, Aspect.STONE, 8, Aspect.WOOD, 4, Aspect.ENERGY, 4,
				Aspect.MOVEMENT, 4, Aspect.BEAST, 2);
		add(Material.POISONOUS_POTATO, Aspect.EVIL, 2, Aspect.PLANT, 2);
		add(Material.PORK, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(Material.POTATO, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.POWERED_MINECART, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8, Aspect.ENERGY, 8, Aspect.METAL, 40);
		add(Material.POWERED_RAIL, Aspect.METAL, 4, Aspect.MACHINE, 1, Aspect.ENERGY, 4);
		add(Material.PUMPKIN, Aspect.LIFE, 8, Aspect.PLANT, 8);
		add(Material.PUMPKIN_PIE, Aspect.LIFE, 8, Aspect.PLANT, 4, Aspect.BEAST, 2);
		add(Material.PUMPKIN_SEEDS, Aspect.LIFE, 1);

		add(Material.QUARTZ, Aspect.STONE, 1, Aspect.VALUE, 1);
		add(Material.QUARTZ_BLOCK, Aspect.STONE, 4, Aspect.VALUE, 4);
		add(Material.QUARTZ_ORE, Aspect.STONE, 2, Aspect.VALUE, 1, Aspect.FIRE, 1);
		add(Material.QUARTZ_STAIRS, Aspect.STONE, 4, Aspect.VALUE, 4);

		add(Material.RAILS, Aspect.METAL, 4, Aspect.MACHINE, 1);
		add(Material.RAW_BEEF, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(Material.RAW_CHICKEN, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(Material.RAW_FISH, Aspect.BEAST, 4, Aspect.FLESH, 4);
		add(Material.RED_MUSHROOM, Aspect.LIFE, 2, Aspect.PLANT, 2);
		add(Material.RED_ROSE, Aspect.LIFE, 2);
		add(Material.REDSTONE, Aspect.ENERGY, 1, Aspect.MACHINE, 1);
		add(Material.REDSTONE_BLOCK, Aspect.ENERGY, 9, Aspect.MACHINE, 9);
		add(Material.REDSTONE_COMPARATOR, Aspect.MACHINE, 8, Aspect.STONE, 4, Aspect.ENERGY, 2);
		add(Material.REDSTONE_LAMP_ON, Aspect.MACHINE, 4, Aspect.LIGHT, 4);
		add(Material.REDSTONE_ORE, Aspect.STONE, 2, Aspect.ENERGY, 4);
		add(Material.REDSTONE_TORCH_ON, Aspect.ENERGY, 2);

		add(Material.SADDLE, Aspect.BEAST, 4);
		add(Material.SAND, Aspect.EARTH, 1);
		add(Material.SANDSTONE, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(Material.SANDSTONE_STAIRS, Aspect.STONE, 2, Aspect.EARTH, 2);
		add(Material.SAPLING, Aspect.PLANT, 4, Aspect.WOOD, 1);
		add(Material.SEEDS, Aspect.PLANT, 1);
		add(Material.SHEARS, Aspect.TOOL, 2, Aspect.METAL, 8);
		add(Material.SIGN, Aspect.WOOD, 8);
		add(Material.SKULL_ITEM, Aspect.EVIL, 8, Aspect.FLESH, 8);
		add(Material.SLIME_BALL, Aspect.BEAST, 2);
		add(0, Material.SMOOTH_BRICK, Aspect.STONE, 2);
		add(1, Material.SMOOTH_BRICK, Aspect.STONE, 1, Aspect.PLANT, 1);
		add(2, Material.SMOOTH_BRICK, Aspect.STONE, 1, Aspect.DESTRUCTION, 1);
		add(Material.SMOOTH_STAIRS, Aspect.STONE, 2);
		add(Material.SNOW, Aspect.ICE, 4);
		add(Material.SNOW_BALL, Aspect.ICE, 1);
		add(Material.SNOW_BLOCK, Aspect.ICE, 4);
		add(Material.SOUL_SAND, Aspect.LIFE, 1, Aspect.EARTH, 1, Aspect.EVIL, 1, Aspect.DARKNESS, 1);
		add(Material.SPECKLED_MELON, Aspect.LIFE, 4, Aspect.PLANT, 1);
		add(Material.SPIDER_EYE, Aspect.BEAST, 2, Aspect.FLESH, 1);
		add(Material.SPRUCE_WOOD_STAIRS, Aspect.WOOD, 2);
		add(Material.STAINED_CLAY, Aspect.STONE, 2);
		add(Material.STAINED_GLASS, Aspect.CLEAR, 3);
		add(Material.STAINED_GLASS_PANE, Aspect.CLEAR, 1);
		add(Material.STEP, Aspect.STONE, 1);
		add(Material.STICK, Aspect.WOOD, 1);
		add(Material.STONE, Aspect.STONE, 2);
		add(Material.STONE_AXE, Aspect.STONE, 6, Aspect.TOOL, 1);
		add(Material.STONE_BUTTON, Aspect.STONE, 2, Aspect.MACHINE, 1);
		add(Material.STONE_HOE, Aspect.STONE, 4, Aspect.TOOL, 1);
		add(Material.STONE_PICKAXE, Aspect.STONE, 6, Aspect.TOOL, 1);
		add(Material.STONE_PLATE, Aspect.STONE, 4, Aspect.MACHINE, 4);
		add(Material.STONE_SPADE, Aspect.STONE, 2, Aspect.TOOL, 1);
		add(Material.STONE_SWORD, Aspect.STONE, 4, Aspect.WEAPON, 1);
		add(Material.STORAGE_MINECART, Aspect.METAL, 40, Aspect.MACHINE, 8, Aspect.MOVEMENT, 8, Aspect.WOOD, 8,
				Aspect.VOID, 8);
		add(Material.STRING, Aspect.BEAST, 1);
		add(Material.SUGAR, Aspect.PLANT, 1);
		add(Material.SUGAR_CANE, Aspect.PLANT, 1);
		add(Material.SULPHUR, Aspect.DESTRUCTION, 8, Aspect.BEAST, 2, Aspect.ENERGY, 4);
		
		add(Material.THIN_GLASS, Aspect.CLEAR, 1);
		add(Material.TNT, Aspect.DESTRUCTION, 16, Aspect.ENERGY, 8);
		add(Material.TORCH, Aspect.LIGHT, 1);
		add(Material.TRAP_DOOR, Aspect.MOVEMENT, 2, Aspect.MACHINE, 2, Aspect.WOOD, 4);
		add(Material.TRAPPED_CHEST, Aspect.WOOD, 16, Aspect.VOID, 4, Aspect.MACHINE, 2);
		add(Material.TRIPWIRE_HOOK, Aspect.WOOD, 2, Aspect.METAL, 4, Aspect.MACHINE, 2);
		
		add(Material.VINE, Aspect.PLANT, 2);
		
		add(Material.WATCH, Aspect.TIME, 16, Aspect.VALUE, 16, Aspect.METAL, 16);
		add(Material.WATER_BUCKET, Aspect.METAL, 24, Aspect.WATER, 8);
		add(Material.WATER_LILY, Aspect.PLANT, 2, Aspect.WATER, 4);
		add(Material.WHEAT, Aspect.LIFE, 1, Aspect.PLANT, 1);
		add(Material.WOOD, Aspect.WOOD, 2);
		add(Material.WOOD_AXE, Aspect.WOOD, 4, Aspect.TOOL, 1);
		add(Material.WOOD_BUTTON, Aspect.WOOD, 1, Aspect.MACHINE, 1);
		add(Material.WOOD_DOOR, Aspect.WOOD, 4, Aspect.MACHINE, 1);
		add(Material.WOOD_HOE, Aspect.WOOD, 4, Aspect.TOOL, 1);
		add(Material.WOOD_PICKAXE, Aspect.WOOD, 6, Aspect.TOOL, 1);
		add(Material.WOOD_PLATE, Aspect.WOOD, 4, Aspect.MACHINE, 4);
		add(Material.WOOD_SPADE, Aspect.WOOD, 2, Aspect.TOOL, 1);
		add(Material.WOOD_STAIRS, Aspect.WOOD, 2);
		add(Material.WOOD_STEP, Aspect.WOOD, 1);
		add(Material.WOOD_SWORD, Aspect.WOOD, 4, Aspect.WEAPON, 1);
		add(Material.WOODEN_DOOR, Aspect.WOOD, 4, Aspect.MACHINE, 1);
		add(Material.WOOL, Aspect.BEAST, 2);
		add(Material.WORKBENCH, Aspect.WOOD, 8, Aspect.CONSTRUCT, 8);
		add(Material.WRITTEN_BOOK, Aspect.KNOWLEDGE, 8, Aspect.PLANT, 4);
		
		add(Material.YELLOW_FLOWER, Aspect.PLANT, 1);
	}

	@Override
	public void unload() {
	}

}
