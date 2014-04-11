package net.minezrc.zephyrus.core.hook;

import net.minezrc.zephyrus.hook.ProtectionHook;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.utils.CombatUtil;

/**
 * Zephyrus - TownyHook.java
 * 
 * @author minnymin3
 * 
 */

public class TownyHook implements ProtectionHook {

	private Towny towny;
	
	@Override
	public boolean canCast(Player player, Spell spell) {
		TownBlock block = TownyUniverse.getTownBlock(player.getLocation());
		if (block != null) {
			if (block.getType() == TownBlockType.ARENA || block.getType() == TownBlockType.WILDS) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			Player target = (Player) entity;
			if (CombatUtil.preventDamageCall(towny, player, target)) {
				return friendly;
			}
		}
		return true;
	}
	
	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Towny");
		if (plugin != null && plugin instanceof Towny) {
			return true;
		}
		return false;
	}

	@Override
	public void setupHook() {
		this.towny = (Towny) Bukkit.getPluginManager().getPlugin("Towny");
	}

}
