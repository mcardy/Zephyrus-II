package net.minezrc.zephyrus.event;

import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Zephyrus - UserCraftSpellEvent.java <br>
 * An event called when a user crafts a spell
 * 
 * @author minnymin3
 * 
 */

public class UserCraftSpellEvent extends UserEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private Spell spell;

	private boolean cancelled = false;

	public UserCraftSpellEvent(Player player, Spell spell) {
		super(player);
		this.spell = spell;
	}

	public UserCraftSpellEvent(User user, Spell spell) {
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
