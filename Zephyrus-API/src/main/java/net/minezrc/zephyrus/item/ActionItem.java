package net.minezrc.zephyrus.item;

import java.util.Set;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Zephyrus - ActionItem.java
 * 
 * @author minnymin3
 * 
 */

public interface ActionItem extends Item {

	public Set<Action> getActions();
	public void onInteract(PlayerInteractEvent event);
	
}
