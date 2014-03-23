package net.minezrc.zephyrus.spell;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

/**
 * Zephyrus - ConfigurableSpell.java
 * 
 * @author minnymin3
 * 
 */

public interface ConfigurableSpell {

	public Map<String, Object> getDefaultConfiguration();
	public void loadConfiguration(ConfigurationSection config);
	
}
