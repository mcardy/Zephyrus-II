package net.minezrc.zephyrus.nms;

import net.minezrc.zephyrus.Manager;

/**
 * Zephyrus - NMSManager.java
 * 
 * @author minnymin3
 * 
 */

public interface NMSManager extends Manager {

	public UpgradeTrade getItemUpgradeTrade();
	public String getVersion();
	
}
