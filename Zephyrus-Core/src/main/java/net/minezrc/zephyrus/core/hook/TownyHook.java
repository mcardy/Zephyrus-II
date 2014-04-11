package net.minezrc.zephyrus.core.hook;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import net.minezrc.zephyrus.hook.ProtectionHook;
import net.minezrc.zephyrus.spell.Spell;

/**
 * Zephyrus - TownyHook.java
 * 
 * @author minnymin3
 * 
 */

public class TownyHook implements ProtectionHook {

	// TODO Towny hooking
	
	@Override
	public boolean canCast(Player player, Spell spell) {
		return false;
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		return false;
	}
	
	@Override
	public boolean checkHook() {
		return false;
	}

	@Override
	public void setupHook() {
	}

}
