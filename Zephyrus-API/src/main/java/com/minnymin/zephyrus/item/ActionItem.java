package com.minnymin.zephyrus.item;

import java.util.Set;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Zephyrus - ActionItem.java<br>
 * Represents a custom item that is activated on interact
 * 
 * @author minnymin3
 * 
 */

public interface ActionItem extends Item {

	/**
	 * Gets the types of actions that this item responds to
	 * 
	 * @return A set of actions
	 */
	public Set<Action> getActions();

	/**
	 * Called when this item is in hand during a PlayerInteractEvent
	 * 
	 * @param event The event that was called
	 */
	public void onInteract(PlayerInteractEvent event);

}
