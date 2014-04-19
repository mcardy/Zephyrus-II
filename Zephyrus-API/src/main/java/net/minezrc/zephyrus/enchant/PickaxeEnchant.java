package net.minezrc.zephyrus.enchant;

import org.bukkit.event.block.BlockBreakEvent;

/**
 * Zephyrus - PickaxeEnchant.java <br>
 * Represents an enchantment that can be applied to a pickaxe
 * 
 * @author minnymin3
 * 
 */

public interface PickaxeEnchant extends Enchant {

	/**
	 * Called when a pickaxe with this enchant breaks a block
	 * 
	 * @param level The level of the enchant
	 * @param event The event that was called
	 */
	public void onBlockBreak(int level, BlockBreakEvent event);

}
