package net.minezrc.zephyrus.event;

import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Zephyrus - UserPreCastEvent.java <br>
 * An event called when a user is preparing a spell cast
 * 
 * @author minnymin3
 * 
 */

public class UserPreCastEvent extends UserEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private String[] args;
	private boolean cancelled = false;
	private int power;

	private Spell spell;

	public UserPreCastEvent(Player player, Spell spell, int power, String[] args) {
		super(player);
		this.spell = spell;
		this.power = power;
		this.args = args;
	}

	public UserPreCastEvent(User player, Spell spell, int power, String[] args) {
		super(player);
		this.spell = spell;
		this.power = power;
		this.args = args;
	}

	/**
	 * Gets the arguments used to cast the spell
	 * 
	 * @return Null if cast from wand
	 */
	public String[] getArgs() {
		return args;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Gets the power that this spell is cast at
	 * 
	 * @return Value of 1 if cast with default power
	 */
	public int getPower() {
		return this.power;
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
