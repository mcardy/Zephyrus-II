package net.minezrc.zephyrus.command;

import net.minezrc.zephyrus.Manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Zephyrus - CommandManager.java <br>
 * An interface used to handle all command processing and registering
 * 
 * TODO Migrate to Core. Not needed in API.
 * 
 * @author minnymin3
 * 
 */

public interface CommandManager extends Manager {

	/**
	 * Handles the default command method to utilize the command framework
	 * 
	 * @param sender Default sender
	 * @param command Default command
	 * @param label Default command label
	 * @param args Default command args
	 * @return Will return true
	 */
	public boolean handle(CommandSender sender, Command command, String label, String[] args);

}
