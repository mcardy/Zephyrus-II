package net.minezrc.zephyrus.enchant;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Zephyrus - PickaxeEnchant.java
 * 
 * @author minnymin3
 * 
 */

public interface PickaxeEnchant extends Enchant {

	public void onBlockBreak(int level, BlockBreakEvent event);
	
}
