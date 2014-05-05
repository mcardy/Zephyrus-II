package com.minnymin.zephyrus.core;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.aspect.CoreAspectManager;
import com.minnymin.zephyrus.core.command.ItemCommand;
import com.minnymin.zephyrus.core.command.SpellCommand;
import com.minnymin.zephyrus.core.command.UserCommand;
import com.minnymin.zephyrus.core.config.ConfigOptions;
import com.minnymin.zephyrus.core.enchant.CoreEnchantManager;
import com.minnymin.zephyrus.core.hook.CoreHookManager;
import com.minnymin.zephyrus.core.item.CoreItemManager;
import com.minnymin.zephyrus.core.nms.CoreNMSManager;
import com.minnymin.zephyrus.core.permissions.PermissionsManager;
import com.minnymin.zephyrus.core.spell.CoreSpellManager;
import com.minnymin.zephyrus.core.state.CoreStateManager;
import com.minnymin.zephyrus.core.user.CoreUserManager;
import com.minnymin.zephyrus.core.util.Metrics;
import com.minnymin.zephyrus.core.util.Updater;
import com.minnymin.zephyrus.core.util.Updater.UpdateResult;
import com.minnymin.zephyrus.core.util.VersionInfo;
import com.minnymin.zephyrus.core.util.command.CommandFramework;

/**
 * Zephyrus - ZephyrusPlugin.java
 * 
 * TODO Implement Combo-Spells - Later release
 * TODO Implement Spell and Item Shops
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
		Zephyrus.setAspectManager(new CoreAspectManager());
		Zephyrus.setEnchantmentManager(new CoreEnchantManager());
		Zephyrus.setHookManager(new CoreHookManager());
		Zephyrus.setItemManager(new CoreItemManager());
		Zephyrus.setNMSManager(new CoreNMSManager());
		Zephyrus.setSpellManager(new CoreSpellManager());
		Zephyrus.setStateManager(new CoreStateManager());
		Zephyrus.setUserManager(new CoreUserManager());
	}

	@Override
	public void onEnable() {
		new Metrics(this).start();
		ConfigOptions.loadOptions(getConfig());
		PermissionsManager.registerPermissions();

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
