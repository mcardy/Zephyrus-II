package net.minezrc.zephyrus.core;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.aspect.SimpleAspectManager;
import net.minezrc.zephyrus.core.command.SimpleCommandManager;
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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Zephyrus - ZephyrusPlugin.java
 * 
 * TODO Re-implement all spells and items
 * TODO Implement Combo-Spells
 * TODO Item level and upgrades
 * TODO Particle projectiles
 * 
 * @author minnymin3
 * 
 */

public class ZephyrusPlugin extends JavaPlugin {
	
	@Override
	public void onLoad() {
		saveDefaultConfig();
		Zephyrus.setPlugin(this);
		Zephyrus.setAspectManager(new SimpleAspectManager());
		Zephyrus.setCommandManager(new SimpleCommandManager());
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
		try {
			new Metrics(this).start();
			ConfigOptions.loadOptions(getConfig());
			Zephyrus.getAspectManager().load();
			Zephyrus.getCommandManager().load();
			Zephyrus.getEnchantmentManager().load();
			Zephyrus.getHookManager().load();
			Zephyrus.getItemManager().load();
			Zephyrus.getNMSManager().load();
			Zephyrus.getSpellManager().load();
			Zephyrus.getStateManager().load();
			Zephyrus.getUserManager().load();
			schedulePostLoadTask(Updater.update());
			getLogger().info("Fully loaded Zephyrus v" + getDescription().getVersion());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		Zephyrus.getCommandManager().unload();
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
		return Zephyrus.getCommandManager().handle(sender, command, name, args);
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
					getLogger().info("[Updater] Update checker failed to find a project for Zephyrus. It may have been removed.");
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
					getLogger().info("[Updater] An update is available for Zephyrus: " + updater.getLatestName() + " "
							+ updater.getLatestType() + ". Get it here:");
					getLogger().info(updater.getLatestFileLink());
					break;
				default:
					break;
				}
				getLogger().info("[Spells] Loaded " + Zephyrus.getSpellManager().getSpellSet().size() + " spells");
			}
		});
	}
}
