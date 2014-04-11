package net.minezrc.zephyrus.core.hook;

import java.util.List;

import net.minezrc.zephyrus.hook.EconomyHook;
import net.minezrc.zephyrus.hook.PluginHookManager;
import net.minezrc.zephyrus.hook.ProtectionHook;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - SimpleHookManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleHookManager implements PluginHookManager {

	private EconomyHook vault;
	private List<ProtectionHook> hooks;
	
	@Override
	public void load() {
		VaultHook vault = new VaultHook();
		if (vault.checkHook()) {
			vault.setupHook();
			this.vault = vault;
		}
		addProtectionHook(new FactionsHook());
		addProtectionHook(new TownyHook());
		addProtectionHook(new WorldGuardHook());
	}

	@Override
	public void unload() {
	}

	@Override
	public void addProtectionHook(ProtectionHook hook) {
		if (hook.checkHook()) {
			hook.setupHook();
			this.hooks.add(hook);
		}
	}
	
	@Override
	public boolean canBuild(Player player, Block block) {
		for (ProtectionHook hook : this.hooks) {
			if (!hook.canBuild(player, block)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canBuild(Player player, Location loc) {
		for (ProtectionHook hook : this.hooks) {
			if (!hook.canBuild(player, loc)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canCast(Player player, Spell spell) {
		for (ProtectionHook hook : this.hooks) {
			if (!hook.canCast(player, spell)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canTarget(Player player, LivingEntity target, boolean friendly) {
		for (ProtectionHook hook : this.hooks) {
			if (!hook.canTarget(player, target, friendly)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public EconomyHook getVaultHook() {
		return vault;
	}

}
