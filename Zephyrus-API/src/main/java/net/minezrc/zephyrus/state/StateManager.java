package net.minezrc.zephyrus.state;

import java.util.List;

import net.minezrc.zephyrus.Manager;

/**
 * Zephyrus - StateManager.java<br>
 * Represents the manager for all states
 * 
 * @author minnymin3
 * 
 */

public interface StateManager extends Manager {

	/**
	 * Gets the list of registered states
	 * 
	 * @return A list of states
	 */
	public List<State> getStates();

	/**
	 * Registers a state with Zephyrus
	 * 
	 * @param state The state to register
	 */
	public void registerState(State state);

}
