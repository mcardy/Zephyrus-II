package net.minezrc.zephyrus.nms;

import net.minezrc.zephyrus.Manager;

/**
 * Zephyrus - NMSManager.java<br>
 * Represents the manager of NetMinecraftSource code
 * 
 * @author minnymin3
 * 
 */

public interface NMSManager extends Manager {

	/**
	 * Gets the UpgradeTrade for the version of Bukkit
	 * 
	 * @return UpgradeTrade object for this version
	 */
	public UpgradeTrade getItemUpgradeTrade();

	/**
	 * Gets the name of this version's NMS package
	 * 
	 * @return A string containing the version (eg. v1_7_R1)
	 */
	public String getVersion();

}
