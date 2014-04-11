package net.minezrc.zephyrus.hook;

import net.minezrc.zephyrus.Manager;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - PluginCompatibilityManager.java
 * 
 * @author minnymin3
 * 
 */

public interface PluginHookManager extends Manager {

	public boolean canCast(Player player, Spell spell);
	public boolean canTarget(Player player, LivingEntity target, boolean friendly);
	public void addProtectionHook(ProtectionHook hook);
	public EconomyHook getVaultHook();

}
