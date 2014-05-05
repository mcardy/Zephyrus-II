package com.minnymin.zephyrus.core.hook;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.hook.EconomyHook;

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
		if (plugin != null && plugin instanceof Vault
				&& Bukkit.getServer().getServicesManager().getRegistration(Economy.class) != null) {
			Zephyrus.getPlugin().getLogger().info("[Plugin Hooks] Found and hooked Vault economy");
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
