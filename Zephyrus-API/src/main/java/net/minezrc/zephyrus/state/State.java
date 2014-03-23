package net.minezrc.zephyrus.state;

import net.minezrc.zephyrus.user.User;

import org.bukkit.event.Listener;

/**
 * Zephyrus - State.java
 * 
 * @author minnymin3
 * 
 */

public interface State extends Listener {

	/**
	 * The amount of time (in 10ths of a second) that the state should tick
	 * 
	 * @return
	 */
	public int getTickTime();

	/**
	 * Called when the state is first applied
	 */
	public void onApplied(User user);

	/**
	 * Called when the state is removed
	 */
	public void onRemoved(User user);

	/**
	 * Called when a player first logs in to remove reminants of effect
	 */
	public void onStartup(User user);
	
	/**
	 * Method called to tick the effect every time the tick time is achieved
	 * 
	 * @param user The user to tick
	 */
	public void onTick(User user);

	/**
	 * Called when there is 5 seconds left for the effect
	 */
	public void onWarning(User user);

}
