package com.minnymin.zephyrus.nms;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - UpgradeTrade.java<br>
 * An interface representing a merchant
 * 
 * @author minnymin3
 * 
 */

public interface UpgradeTrade extends Cloneable {

	/**
	 * Gets the first input item
	 * 
	 * @return An ItemStack representing the first input
	 */
	public ItemStack getFirstInput();

	/**
	 * Gets the output item
	 * 
	 * @return An ItemStack representing the output
	 */
	public ItemStack getOutput();

	/**
	 * Gets the second input item
	 * 
	 * @return An ItemStack representing the second input
	 */
	public ItemStack getSecondInput();

	/**
	 * Opens the merchant trade window
	 * 
	 * @param player The target player
	 */
	public void openTrade(Player player);

	/**
	 * Sets the offer of the trade window
	 * 
	 * @param first The first trade item
	 * @param second The second trade item
	 * @param output The output trade item
	 */
	public void setOffer(ItemStack first, ItemStack second, ItemStack output);

}
