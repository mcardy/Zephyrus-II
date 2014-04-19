package net.minezrc.zephyrus.event;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Zephyrus - UserEvent.java <br>
 * An abstract superclass representing an event called by a user
 * 
 * @author minnymin3
 * 
 */

public abstract class UserEvent extends Event {

	private User user;

	public UserEvent(Player player) {
		this.user = Zephyrus.getUser(player.getName());
	}

	public UserEvent(User user) {
		this.user = user;
	}

	/**
	 * Gets the user who performed the action
	 * 
	 * @return The player's user object
	 */
	public User getUser() {
		return this.user;
	}

}
