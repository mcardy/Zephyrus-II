package net.minezrc.zephyrus.aspect;

import org.bukkit.ChatColor;

/**
 * Zephyrus - EssenceType.java
 * 
 * @author minnymin3
 * 
 */

public enum Aspect {
	FIRE("Ignibus", "Fire, Heat, Flame", ChatColor.RED), 
	WATER("Aqua", "Water, Aquatics", ChatColor.BLUE), 
	ICE("Rigentem", "Ice, Cold", ChatColor.AQUA), 
	WIND("Ventum", "Wind, Movement", ChatColor.GRAY), 
	PLANT("Frondibus","Life, Growth", ChatColor.GREEN), 
	PURE("Clara", "Purity", ChatColor.WHITE), 
	EVIL("Tenebris", "Evil, Darkness", ChatColor.DARK_PURPLE), 
	EARTH("Terrae",	"Ground, Soil", ChatColor.DARK_GREEN), 
	ANIMAL("Animalis", "Animal", ChatColor.GOLD), 
	ROCK("Lapideas", "Stone", ChatColor.DARK_GRAY), 
	RARE("Rarum", "Rare, Valuable", ChatColor.GOLD), 
	METAL("Metallica", "Metal", ChatColor.GRAY), 
	GLASS("Purgare", "Glass, Clear", ChatColor.WHITE), 
	MACHINE("Cogitatus", "Machine", ChatColor.DARK_GRAY), 
	ATTACK("Ingrata", "Attack, Harmful", ChatColor.DARK_RED), 
	MAGIC("Magia", "Magic, Arcane", ChatColor.LIGHT_PURPLE), 
	ENDER("Slutar", "Ender", ChatColor.DARK_PURPLE), 
	TOOL("Strumento", "Tool", ChatColor.DARK_GRAY), 
	WOOD("Lignum", "Wood, Tree", ChatColor.DARK_GREEN), 
	ARMOR("Armatura", "Armor Protection", ChatColor.DARK_GRAY);
	
	private String displayName;
	private String description;
	private ChatColor color;
	
	Aspect(String displayName, String description, ChatColor color) {
		this.displayName = displayName;
		this.description = description;
		this.color = color;
	}
	
}