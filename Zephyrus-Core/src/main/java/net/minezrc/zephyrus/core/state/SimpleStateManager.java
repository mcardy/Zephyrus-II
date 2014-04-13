package net.minezrc.zephyrus.core.state;

import java.util.ArrayList;
import java.util.List;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.state.State;
import net.minezrc.zephyrus.state.StateManager;

import org.bukkit.Bukkit;

import com.google.common.collect.Lists;

/**
 * Zephyrus - StateManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleStateManager implements StateManager {
		
	private List<State> states;
	
	public SimpleStateManager() {
		states = new ArrayList<State>();
	}
	
	@Override
	public List<State> getStates() {
		return Lists.newArrayList(states);
	}
	
	@Override
	public void registerState(State state) {
		Bukkit.getPluginManager().registerEvents(state, Zephyrus.getPlugin());
		states.add(state);
	}
	
	@Override
	public void load() {
		registerState(StateList.ARMOR);
		registerState(StateList.FEATHER);
	}

	@Override
	public void unload() {
	}

}
