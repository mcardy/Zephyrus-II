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

	BEAST("Bestia", "Animal, Beast", ChatColor.DARK_GREEN),
	CLEAR("Vetro", "Clear, Glass", ChatColor.WHITE),
	CONSTRUCT("Costruire", "Construction, Build", ChatColor.GRAY),
	DARKNESS("Tenebre", "Darkness, Hidden, Obscured", ChatColor.BLACK),
	DEFENSE("Difesa", "Defense, Protection", ChatColor.DARK_BLUE),
	DESTRUCTION("Contritio", "Destruction, Fractured", ChatColor.DARK_GRAY),
	EARTH("Terra", "Earth, Dirt", ChatColor.DARK_GREEN),
	ENDER("Endrich", "Ender, Strange", ChatColor.DARK_PURPLE),
	ENERGY("Energia", "Energy, Power", ChatColor.GRAY),
	EVIL("Malum", "Evil", ChatColor.DARK_RED),
	FLESH("Carnem", "Flesh, Bones", ChatColor.RED),
	FIRE("Ignis", "Fire, Heat", ChatColor.RED),
	ICE("Glaciem", "Ice, Cold", ChatColor.AQUA),
	KNOWLEDGE("Cognitio", "Knowledge", ChatColor.GOLD),
	LIFE("Vita", "Life, Sustanance", ChatColor.GREEN),
	LIGHT("Lucem", "Light, Brightness", ChatColor.YELLOW),
	MACHINE("Apparatus", "Machine, Device", ChatColor.DARK_AQUA),
	METAL("Metallum", "Metal, Ore", ChatColor.GRAY),
	MOVEMENT("Motus", "Movement, Motion", ChatColor.WHITE),
	MYSTICAL("Magicis", "Magic, Mystical", ChatColor.LIGHT_PURPLE),
	PLANT("Herba", "Herb, Plant", ChatColor.GREEN),
	STONE("Lapideas", "Stone, Rock", ChatColor.DARK_GRAY),
	TIME("Tempus", "Time", ChatColor.YELLOW),
	TOOL("Strumento", "Instrument, Tool", ChatColor.GREEN),
	VALUE("Valore", "Value, Expensive", ChatColor.GOLD),
	VOID("Vuoto", "Void, Empty", ChatColor.BLACK),
	WATER("Aqua", "Water", ChatColor.BLUE),
	WIND("Ventum", "Wind, Air", ChatColor.WHITE),
	WEAPON("Impetum", "Attack, Harmful, Weapon", ChatColor.RED),
	WOOD("Legno", "Wooden, Solid", ChatColor.DARK_GREEN);

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