package net.minezrc.zephyrus.state;

import java.util.List;

import net.minezrc.zephyrus.Manager;

/**
 * Zephyrus - StateManager.java
 * 
 * @author minnymin3
 * 
 */

public interface StateManager extends Manager {
	
	public List<State> getStates();
	public void registerState(State state);
	
}
