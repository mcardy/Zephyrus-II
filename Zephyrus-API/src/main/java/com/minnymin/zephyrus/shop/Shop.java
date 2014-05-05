package com.minnymin.zephyrus.shop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

/**
 * Zephyrus - Shop.java <br>
 * Represents a Sign Shop
 * 
 * @author minnymin3
 * 
 */

public interface Shop {

	/**
	 * Called when creating a sign of this shop. Checks if the sign can
	 * successfully be created.
	 * 
	 * @param event The SignChangeEvent called when creating this sign
	 * @return True if the sign can be turned into a shop
	 */
	public boolean create(SignChangeEvent event);

	/**
	 * Gets the chat color that will be appended to the beginning of the first
	 * line of signs that properly enable
	 * 
	 * @return A ChatColor
	 */
	public ChatColor getChatColorIdentifier();

	/**
	 * Gets the name of the sign to be used on the first line of a sign when
	 * making shops for this
	 * 
	 * @return The name of the sign
	 */
	public String getName();

	/**
	 * Called when a sign with the given
	 * 
	 * @param player
	 * @param args
	 */
	public void onClick(Player player, String[] args);

}
