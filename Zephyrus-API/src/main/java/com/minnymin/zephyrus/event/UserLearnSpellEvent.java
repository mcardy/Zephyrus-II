package com.minnymin.zephyrus.event;


import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - UserLearnSpellEvent.java <br>
 * An event called when a user learns a spell
 * 
 * @author minnymin3
 * 
 */

public class UserLearnSpellEvent extends UserEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private boolean cancelled = false;

	private Spell spell;

	public UserLearnSpellEvent(Player player, Spell spell) {
		super(player);
		this.spell = spell;
	}

	public UserLearnSpellEvent(User user, Spell spell) {
		super(user);
		this.spell = spell;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Gets the spell that will be cast
	 * 
	 * @return The spell that was cast
	 */
	public Spell getSpell() {
		return spell;
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
