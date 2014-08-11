package com.minnymin.zephyrus.core.hook;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.hook.EconomyHook;
import com.minnymin.zephyrus.hook.PluginHookManager;
import com.minnymin.zephyrus.hook.ProtectionHook;
import com.minnymin.zephyrus.spell.Spell;

/**
 * Zephyrus - SimpleHookManager.java
 * 
 * @author minnymin3
 * 
 */

public class CoreHookManager implements PluginHookManager {

	private EconomyHook vault;
	private List<ProtectionHook> hooks;
	
	public CoreHookManager() {
		hooks = new ArrayList<ProtectionHook>();
	}
	
	@Override
	public void load() {
		VaultHook vault = new VaultHook();
		if (vault.checkHook()) {
			vault.setupHook();
			this.vault = vault;
		}
		addProtectionHook(new FactionsHook());
		addProtectionHook(new ResidenceHook());
		addProtectionHook(new TownyHook());
		addProtectionHook(new WorldGuardHook());
	}

	@Override
	public void unload() {
	}

	@Override
	public void addProtectionHook(ProtectionHook hook) {
		try {
			if (hook.checkHook()) {
				hook.setupHook();
				this.hooks.add(hook);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean canBuild(Player player, Block block) {
		for (ProtectionHook hook : this.hooks) {
			try {
				if (!hook.canBuild(player, block)) {
					return false;
				}
			} catch (Exception e) {
				return true;
			}
		}
		return true;
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		for (ProtectionHook hook : this.hooks) {
			try {
				if (!hook.canBuild(player, loc)) {
					return false;
				}
			} catch (Exception e) {
				return true;
			}
		}
		return true;
	}
	
	@Override
	public boolean canCast(Player player, Spell spell) {
		for (ProtectionHook hook : this.hooks) {
			try {
				if (!hook.canCast(player, spell)) {
					return false;
				}
			} catch (Exception e) {
				return true;
			}
		}
		return true;
	}
	
	@Override
	public boolean canTarget(Player player, LivingEntity target, boolean friendly) {
		for (ProtectionHook hook : this.hooks) {
			try {
				if (!hook.canTarget(player, target, friendly)) {
					return false;
				}
			} catch (Exception e) {
				return true;
			}
		}
		return true;
	}

	@Override
	public EconomyHook getEconomyHook() {
		return vault;
	}

}
