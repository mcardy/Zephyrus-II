package net.minezrc.zephyrus.core.hook;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import net.minezrc.zephyrus.hook.EconomyHook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Zephyrus - LoadedVaultHook.java
 * 
 * @author minnymin3
 * 
 */

public class VaultHook implements EconomyHook {

	private Economy econ;

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Vault");
		if (plugin != null && plugin instanceof Vault) {
			return true;
		}
		return false;
	}
	
	@Override
	public void drainBalance(Player player, double amount) {
		econ.withdrawPlayer(player.getName(), amount);
	}

	@Override
	public double getBalance(Player player) {
		return econ.getBalance(player.getName());
	}

	@Override
	public void setupHook() {
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			Bukkit.getLogger().warning("Economy not found. Check your vault installation.");
			return;
		}
		econ = rsp.getProvider();
	}

}
