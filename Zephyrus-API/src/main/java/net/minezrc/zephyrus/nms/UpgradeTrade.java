package net.minezrc.zephyrus.nms;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - UpgradeTrade.java
 * 
 * @author minnymin3
 * 
 */

public interface UpgradeTrade extends Cloneable {

	public void setOffer(ItemStack first, ItemStack second, ItemStack output);
	public void openTrade(Player player);
	public ItemStack getFirstInput();
	public ItemStack getSecondInput();
	public ItemStack getOutput();
	
}
