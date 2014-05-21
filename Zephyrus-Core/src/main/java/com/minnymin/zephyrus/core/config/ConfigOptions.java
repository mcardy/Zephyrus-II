package com.minnymin.zephyrus.core.config;


import org.bukkit.configuration.file.FileConfiguration;

import com.minnymin.zephyrus.Zephyrus;

/**
 * Zephyrus - ConfigOptions.java
 * 
 * @author minnymin3
 * 
 */

public class ConfigOptions {

	public static boolean UPDATE_CHECKING;
	public static boolean ENABLE_METRICS;
	public static boolean PARTICLE_EFFECTS;
	public static boolean FACTION_CASTING;
	public static boolean TOWNY_CASTING;
	public static boolean DISABLE_ITEM_CRAFTING;
	public static boolean DISABLE_ENCHANTMENTS;
	public static boolean DISABLE_SPELL_CRAFTING;
	public static int MAX_BOOKS;
	public static int MANA_REGEN;
	
	public static void loadOptions(FileConfiguration config) {
		config.addDefaults(Zephyrus.getPlugin().getConfig().getDefaults());
		UPDATE_CHECKING = config.getBoolean("Update-Checking");
		ENABLE_METRICS = config.getBoolean("Metrics");
		MANA_REGEN = config.getInt("Mana-Regeneration");
		DISABLE_SPELL_CRAFTING = config.getBoolean("Disable-Spell-Crafting");
		DISABLE_ENCHANTMENTS = config.getBoolean("Disable-Enchantments");
		DISABLE_ITEM_CRAFTING = config.getBoolean("Disable-Item-Crafting");
		PARTICLE_EFFECTS = config.getBoolean("Particle-Effects");
		FACTION_CASTING = config.getBoolean("Faction-Casting");
		TOWNY_CASTING = config.getBoolean("Towny-Casting");
		MAX_BOOKS = config.getInt("Maximum-Books");
	}
		
}
