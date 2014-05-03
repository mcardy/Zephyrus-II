package com.minnymin.zephyrus.event;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - UserTargetEntityEvent.java <br>
 * An event called when a user targets an entity
 * 
 * @author minnymin3
 * 
 */

public class UserTargetEntityEvent extends UserEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private boolean cancelled = false;
	private boolean friendly;
	private LivingEntity target;

	public UserTargetEntityEvent(Player player, LivingEntity target, boolean friendly) {
		super(player);
		this.target = target;
		this.friendly = friendly;
	}

	public UserTargetEntityEvent(User user, LivingEntity target, boolean friendly) {
		super(user);
		this.target = target;
		this.friendly = friendly;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Gets whether or not the applied spell is friendly
	 * 
	 * @return Whether or not the spell target is friendly
	 */
	public boolean getIsFriendly() {
		return friendly;
	}

	/**
	 * Gets the entity the user is trying to target
	 * 
	 * @return A LivingEntity target
	 */
	public LivingEntity getTarget() {
		return target;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

}
