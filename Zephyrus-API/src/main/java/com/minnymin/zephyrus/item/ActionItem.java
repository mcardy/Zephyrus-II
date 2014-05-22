package com.minnymin.zephyrus.item;

import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Zephyrus - ActionItem.java<br>
 * Represents a custom item that is activated on interact
 * 
 * @author minnymin3
 * 
 */

public abstract class ActionItem extends Item {

	public ActionItem(String name, int craftingLevel, Material mat, List<String> lore) {
		super(name, craftingLevel, mat, lore);
	}

	/**
	 * Gets the types of actions that this item responds to
	 * 
	 * @return A set of actions
	 */
	public abstract Set<Action> getActions();

	/**
	 * Called when this item is in hand during a PlayerInteractEvent
	 * 
	 * @param event The event that was called
	 */
	public abstract void onInteract(PlayerInteractEvent event);

}
