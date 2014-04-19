package net.minezrc.zephyrus.user;

import java.util.Collection;
import java.util.List;

import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.state.State;

import org.bukkit.entity.Player;

/**
 * Zephyrus - User.java<br>
 * Represents a Zephyrus user
 * 
 * @author minnymin3
 * 
 */

public interface User {

	/**
	 * Adds extra mana to the user
	 * 
	 * @param mana The amount of extra mana to add
	 */
	public void addExtraMana(int mana);

	/**
	 * Adds level progress to the user
	 * 
	 * @param progress The amount of progress to add
	 * @return The total progress
	 */
	public int addLevelProgress(int progress);

	/**
	 * Teaches the user the spell
	 * 
	 * @param spell The spell to teach
	 */
	public void addSpell(Spell spell);

	/**
	 * Adds the status effect to the user's current states
	 * 
	 * @param state The state to add
	 * @param time The amount of time to last
	 */
	public void addState(State state, int time);

	/**
	 * Called to cast a spell
	 * 
	 * @param spell The spell to cast
	 * @param power The power to cast with
	 * @param args The arguments to cast with
	 */
	public void castSpell(Spell spell, int power, String[] args);

	/**
	 * Drains mana from the user
	 * 
	 * @param mana The amount of mana to drain
	 * @return The remaining mana
	 */
	public float drainMana(float mana);

	/**
	 * Gets the list of the player's learned spells
	 * 
	 * @return A list of spell names
	 */
	public List<String> getLearnedSpells();

	/**
	 * Gets the level of the user
	 * 
	 * @return The level of the user
	 */
	public int getLevel();

	/**
	 * Gets the level progress of the user
	 * 
	 * @return The progress of the user
	 */
	public int getLevelProgress();

	/**
	 * Gets the user's current mana
	 * 
	 * @return The user's current mana
	 */
	public int getMana();

	/**
	 * Gets whether or not to display the mana bar
	 * 
	 * @return True to display the bar
	 */
	public boolean getManaDisplay();

	/**
	 * Gets the user's maximum mana
	 * 
	 * @return The users maximum mana
	 */
	public int getMaxMana();

	/**
	 * Gets the player this user relates to
	 * 
	 * @return A player represented by this user
	 */
	public Player getPlayer();

	/**
	 * The current states applied to this user
	 * 
	 * @return A collection of states
	 */
	public Collection<State> getStates();

	/**
	 * Gets the user's target for the given spell
	 * 
	 * @param spell The spell to get the target for
	 * @return The target of the user
	 */
	public Target getTarget(Spell spell);

	/**
	 * Gets whether or not the user is currently casting a continuous spell
	 * 
	 * @return True if the player is casting a spell
	 */
	public boolean isCastingSpell();

	/**
	 * Gets whether or not the user knows the given spell
	 * 
	 * @param spell The spell to check
	 * @return True if the player knows the spell
	 */
	public boolean isSpellLearned(Spell spell);

	/**
	 * Checks whether or not the user has the given state applied
	 * 
	 * @param state The state to check
	 * @return
	 */
	public boolean isStateApplied(State state);

	/**
	 * Sets the target for the given spell
	 * 
	 * @param spell The spell setting the target
	 * @param type The TargetType of the spell
	 * @param friendly Whether or not the spell is benificial
	 */
	public void setTarget(Spell spell, TargetType type, boolean friendly);

	/**
	 * Stops the current spell from being cast
	 */
	public void stopCasting();

}
