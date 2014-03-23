package net.minezrc.zephyrus.core.state;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.state.StateManager;

import org.bukkit.Bukkit;

/**
 * Zephyrus - StateManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleStateManager implements StateManager {
		
	public SimpleStateManager() {
	}
	
	@Override
	public void load() {
		Bukkit.getPluginManager().registerEvents(StateList.ARMOR, Zephyrus.getPlugin());
	}

	@Override
	public void unload() {
	}

}
