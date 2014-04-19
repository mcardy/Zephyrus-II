package net.minezrc.zephyrus.event;

import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Zephyrus - UserPreCastEvent.java <br>
 * An event called after a spell is cast
 * 
 * @author minnymin3
 * 
 */

public class UserPostCastEvent extends UserEvent {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private Spell spell;
	private String[] args;

	private int power;

	public UserPostCastEvent(Player player, Spell spell, int power, String[] args) {
		super(player);
		this.spell = spell;
		this.power = power;
		this.args = args;
	}

	public UserPostCastEvent(User player, Spell spell, int power, String[] args) {
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

}
