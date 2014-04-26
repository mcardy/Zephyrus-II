package net.minezrc.zephyrus.core;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.aspect.SimpleAspectManager;
import net.minezrc.zephyrus.core.command.ItemCommand;
import net.minezrc.zephyrus.core.command.SpellCommand;
import net.minezrc.zephyrus.core.command.UserCommand;
import net.minezrc.zephyrus.core.config.ConfigOptions;
import net.minezrc.zephyrus.core.enchant.SimpleEnchantManager;
import net.minezrc.zephyrus.core.hook.SimpleHookManager;
import net.minezrc.zephyrus.core.item.SimpleItemManager;
import net.minezrc.zephyrus.core.nms.SimpleNMSManager;
import net.minezrc.zephyrus.core.spell.SimpleSpellManager;
import net.minezrc.zephyrus.core.state.SimpleStateManager;
import net.minezrc.zephyrus.core.user.SimpleUserManager;
import net.minezrc.zephyrus.core.util.Metrics;
import net.minezrc.zephyrus.core.util.Updater;
import net.minezrc.zephyrus.core.util.Updater.UpdateResult;
import net.minezrc.zephyrus.core.util.VersionInfo;
import net.minezrc.zephyrus.core.util.command.CommandFramework;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Zephyrus - ZephyrusPlugin.java
 * 
 * TODO Re-implement all spells and items TODO Item level and upgrades
 * 
 * TODO Implement Combo-Spells - Later release
 * 
 * @author minnymin3
 * 
 */

public class ZephyrusPlugin extends JavaPlugin {

	private CommandFramework command;

	@Override
	public void onLoad() {
		command = new CommandFramework(this);
		saveDefaultConfig();
		Zephyrus.setPlugin(this);
		Zephyrus.setAspectManager(new SimpleAspectManager());
		Zephyrus.setEnchantmentManager(new SimpleEnchantManager());
		Zephyrus.setHookManager(new SimpleHookManager());
		Zephyrus.setItemManager(new SimpleItemManager());
		Zephyrus.setNMSManager(new SimpleNMSManager());
		Zephyrus.setSpellManager(new SimpleSpellManager());
		Zephyrus.setStateManager(new SimpleStateManager());
		Zephyrus.setUserManager(new SimpleUserManager());
	}

	@Override
	public void onEnable() {
		new Metrics(this).start();
		ConfigOptions.loadOptions(getConfig());

		command.registerCommands(new SpellCommand());
		command.registerCommands(new ItemCommand());
		command.registerCommands(new UserCommand());
		command.registerHelp();

		Zephyrus.getAspectManager().load();
		Zephyrus.getEnchantmentManager().load();
		Zephyrus.getHookManager().load();
		Zephyrus.getItemManager().load();
		Zephyrus.getNMSManager().load();
		Zephyrus.getSpellManager().load();
		Zephyrus.getStateManager().load();
		Zephyrus.getUserManager().load();

		schedulePostLoadTask(Updater.update());
		getLogger().info("Fully loaded Zephyrus v" + getDescription().getVersion());
	}

	@Override
	public void onDisable() {
		Zephyrus.getEnchantmentManager().unload();
		Zephyrus.getHookManager().unload();
		Zephyrus.getItemManager().unload();
		Zephyrus.getNMSManager().unload();
		Zephyrus.getSpellManager().unload();
		Zephyrus.getStateManager().unload();
		Zephyrus.getUserManager().unload();
		Zephyrus.unload();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
		this.command.handleCommand(sender, command, name, args);
		return true;
	}

	public void schedulePostLoadTask(final Updater updater) {
		Bukkit.getScheduler().runTaskAsynchronously(this, new BukkitRunnable() {
			@Override
			public void run() {
				UpdateResult result = updater.getResult();
				switch (result) {
				case DISABLED:
					getLogger().info("[Updater] Update checking has been disabled for Zephyrus");
					break;
				case FAIL_APIKEY:
					getLogger()
							.info("[Updater] Update checking API key is not correctly configured. See /plugins/Updater/config.yml for details");
					break;
				case FAIL_BADID:
					getLogger()
							.info("[Updater] Update checker failed to find a project for Zephyrus. It may have been removed.");
					break;
				case FAIL_DBO:
					getLogger()
							.info("[Updater] Unable to connect to dev.bukkit.org. If this is the first time you are seeing this then the server is most likely temporairily unreachable.");
					break;
				case FAIL_NOVERSION:
					getLogger()
							.info("[Updater] Something went wrong when checking for updates. Expected version format not found. Contact the plugin author for more details.");
					break;
				case NO_UPDATE:
					getLogger().info("[Updater] Zephyrus is up to date.");
					break;
				case UPDATE_AVAILABLE:
					getLogger().info(
							"[Updater] An update is available for Zephyrus: " + updater.getLatestName() + " "
									+ updater.getLatestType() + ". Get it here:");
					getLogger().info(updater.getLatestFileLink());
					break;
				case DEVELOPMENT:
					getLogger().info(
							"[Updater] You are running a development version of Zephyrus. No update was checked");
				default:
					break;
				}
				getLogger().info("[Spells] Loaded " + Zephyrus.getSpellManager().getSpellSet().size() + " spells");
				VersionInfo.load();
			}
		});
	}
}
