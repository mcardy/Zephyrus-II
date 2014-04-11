package net.minezrc.zephyrus.hook;

import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - ProtectionHook.java
 * 
 * @author minnymin3
 * 
 */

public interface ProtectionHook extends PluginHook {
	
	public boolean canCast(Player player, Spell spell);
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly);
	
}
