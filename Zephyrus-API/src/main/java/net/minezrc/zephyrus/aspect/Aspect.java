package net.minezrc.zephyrus.aspect;

import org.bukkit.ChatColor;

/**
 * Zephyrus - Aspect.java <br>
 * Represents all of the types of aspects available
 * 
 * @author minnymin3
 * 
 */

public enum Aspect {

	ANIMAL("Animalis", "Animal", ChatColor.GOLD), // Leather, Wool, etc.
	ARMOR("Armatura", "Armor Protection", ChatColor.DARK_GRAY), // Armor
	ATTACK("Impetum", "Attack, Harmful", ChatColor.DARK_RED), // Sword, Arrow,
																// Bow, etc.
	DIRT("Terrae", "Ground, Soil", ChatColor.DARK_GREEN), // Dirt, Sand,
															// Sandstone
	ENDER("Slutar", "Ender", ChatColor.DARK_PURPLE), // Ender pearls, Ender egg
	EVIL("Tenebris", "Darkness, Evil, Death", ChatColor.DARK_PURPLE), // Skull,
																		// Bone,
																		// etc.
	FIRE("Ignibus", "Fire, Heat, Flame", ChatColor.RED), // Blazerod, Lava
	GLASS("Purgare", "Glass, Clear, Crystal", ChatColor.WHITE), // Glass
	ICE("Rigentem", "Ice, Cold", ChatColor.AQUA), // Ice, Snow
	LIFE("Folia", "Life, Growth", ChatColor.GREEN), // Grass, Leaves, etc.
	LIGHT("Lucem", "Light, Glow", ChatColor.YELLOW), // Torch, Glowstone
	MACHINE("Cogitatus", "Machine, Mechanical", ChatColor.DARK_GRAY), // Redstone,
																		// Dispenser,
																		// Piston,
																		// etc.
	MAGIC("Magia", "Magic, Arcane", ChatColor.LIGHT_PURPLE), // Blaze Rod,
																// Cauldron,
																// Enchanting
																// table, etc.
	METAL("Metallicis", "Metal", ChatColor.GRAY), // Metal ingot, Metal ore,
													// Bucket, Metal tools, etc.
	POWER("Potenza", "Power, Energy", ChatColor.RED),
	STONE("Lapideas", "Stone", ChatColor.DARK_GRAY), // Diamonds, Gold, Lapis,
														// etc.
	TOOL("Strumento", "Tool", ChatColor.DARK_GRAY), // Stone, Sandstone,
													// Cobblestone, etc.
	VALUE("Prezioso", "Rare, Valuable", ChatColor.GOLD), // Shovel, Pickaxe,
															// etc.
	WATER("Aqua", "Water, Aquatics", ChatColor.BLUE), // Water, Lilly pad, etc.
	WIND("Ventum", "Wind, Movement", ChatColor.GRAY), // Feather, Featherfalling
														// boots, Tall grass,
														// etc.
	WOOD("Lignum", "Wood, Tree", ChatColor.DARK_GREEN); // Wood, Planks,
														// Saplings, etc...

	private ChatColor color;
	private String description;
	private String name;

	Aspect(String name, String description, ChatColor color) {
		this.name = name;
		this.description = description;
		this.color = color;
	}

	/**
	 * Gets the ChatColor of the aspect
	 */
	public ChatColor getColor() {
		return color;
	}

	/**
	 * Gets the default description written in English
	 */
	public String getDefaultDescription() {
		return description;
	}

	/**
	 * Gets the default name written in English
	 */
	public String getDefaultName() {
		return name;
	}

}