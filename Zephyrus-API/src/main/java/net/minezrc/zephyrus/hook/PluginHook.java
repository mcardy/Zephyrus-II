package net.minezrc.zephyrus.hook;

/**
 * Zephyrus - PluginHook.java<br>
 * Represents an external plugin hook
 * 
 * @author minnymin3
 * 
 */

public interface PluginHook {

	/**
	 * Checks if the plugin is available
	 * @return True if the plugin is available
	 */
	public boolean checkHook();

	/**
	 * Sets up the plugin hook
	 */
	public void setupHook();

}
