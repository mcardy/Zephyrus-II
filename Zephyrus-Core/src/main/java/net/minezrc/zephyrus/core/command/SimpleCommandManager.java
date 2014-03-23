package net.minezrc.zephyrus.core.command;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.command.CommandManager;
import net.minezrc.zephyrus.core.util.command.CommandFramework;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Zephyrus - CommandManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleCommandManager implements CommandManager {

	private CommandFramework framework;
	
	public SimpleCommandManager() {
		framework = new CommandFramework(Zephyrus.getPlugin());
	}
	
	@Override
	public boolean handle(CommandSender sender, Command command, String name, String[] args) {
		return framework.handleCommand(sender, command, name, args);
	}

	public void load() {
		framework.registerCommands(new SpellCommand());
		framework.registerCommands(new ItemCommand());
		framework.registerCommands(new UserCommand());
		framework.registerHelp();
	}
	
	@Override
	public void unload() {
	}
	
}
