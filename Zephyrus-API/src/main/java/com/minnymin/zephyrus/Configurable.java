package com.minnymin.zephyrus;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

/**
 * Zephyrus - ConfigurableSpell.java<br>
 * Represents a spell that can has configuration options
 * 
 * @author minnymin3
 * 
 */

public interface Configurable {

	/**
	 * Gets the default map of keys and values<br>
	 * Example map would contain 'power' as the key and '5' as the value
	 * 
	 * @return A Map<String, Object> full of the default values
	 */
	public Map<String, Object> getDefaultConfiguration();

	/**
	 * Loads the configuration from the config section.<br>
	 * Example loading would load the string 'power' from the config
	 * 
	 * @param config The ConfigurationSection pertaining to this spell
	 */
	public void loadConfiguration(ConfigurationSection config);

}
