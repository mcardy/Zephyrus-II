package net.minezrc.zephyrus.event;

import net.minezrc.zephyrus.item.Wand;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Zephyrus - UserBindSpellEvent.java <br>
 * An event called when a user binds a spell to a wand
 * 
 * @author minnymin3
 * 
 */

public class UserBindSpellEvent extends UserEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private boolean cancelled = false;
	private Spell spell;
	private Wand wand;

	public UserBindSpellEvent(Player player, Spell spell, Wand wand) {
		super(player);
		this.spell = spell;
		this.wand = wand;
	}

	public UserBindSpellEvent(User player, Spell spell, Wand wand) {
		super(player);
		this.spell = spell;
		this.wand = wand;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Gets the spell that will be bound
	 * 
	 * @return The spell that was bound
	 */
	public Spell getSpell() {
		return spell;
	}

	/**
	 * Gets the wand that is being bound to
	 * 
	 * @return The wand being bound or null if being bound to a spellbook
	 */
	public Wand getWand() {
		return wand;
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
