package net.minezrc.zephyrus.core.state;

import net.minezrc.zephyrus.state.State;

/**
 * Zephyrus - StateList.java
 * 
 * @author minnymin3
 * 
 */

public class StateList {

	public static final State ARMOR = new ArmorState();
	
	public static final State[] STATES = new State[] { ARMOR };

}
