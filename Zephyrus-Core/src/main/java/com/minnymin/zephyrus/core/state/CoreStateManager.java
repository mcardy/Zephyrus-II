package com.minnymin.zephyrus.core.state;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.Bukkit;

import com.google.common.collect.Lists;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.state.StateManager;

/**
 * Zephyrus - StateManager.java
 * 
 * @author minnymin3
 * 
 */

public class CoreStateManager implements StateManager {
		
	private List<State> states;
	
	public CoreStateManager() {
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
		registerState(StateList.BUILD);
		registerState(StateList.FEATHER);
		registerState(StateList.FIRE_SHIELD);
		registerState(StateList.FLAME_STEP);
		registerState(StateList.FLY);
		registerState(StateList.LIGHT);
		registerState(StateList.SHIELD);
		registerState(StateList.SPEED);
		registerState(StateList.ZEPHYR);
	}

	@Override
	public void unload() {
	}

}
