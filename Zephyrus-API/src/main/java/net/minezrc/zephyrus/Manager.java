package net.minezrc.zephyrus;

/**
 * Zephyrus - Manager.java
 * 
 * The basis of a Zephyrus manager. All manager classes extend this
 * 
 * @author minnymin3
 * 
 */

public interface Manager {

	/**
	 * Called when Zephyrus Core enables
	 */
	public void load();
	
	/**
	 * Called when Zephyrus Core disables
	 */
	public void unload();
	
}
