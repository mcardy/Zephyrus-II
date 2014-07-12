package com.minnymin.zephyrus.core.hook;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.config.ConfigOptions;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.hook.ProtectionHook;
import com.minnymin.zephyrus.spell.Spell;
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
	public boolean canBuild(Player player, Block block) {
		return canBuild(player, block.getLocation());
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		TownBlock tblock = TownyUniverse.getTownBlock(loc);
		if (tblock != null) {
			if (tblock.getType() == TownBlockType.WILDS) {
				return true;
			}
			Language.sendError("user.target.block.town", player);
			return false;
		}
		return true;
	}

	@Override
	public boolean canCast(Player player, Spell spell) {
		TownBlock tblock = TownyUniverse.getTownBlock(player.getLocation());
		if (tblock != null) {
			if (ConfigOptions.TOWNY_CASTING) {
				if (tblock.getType() == TownBlockType.ARENA || tblock.getType() == TownBlockType.WILDS) {
					return true;
				}
			}
			Language.sendError("spell.cast.town", player);
			return false;
		}
		return true;
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			Player target = (Player) entity;
			if (CombatUtil.preventDamageCall(towny, player, target)) {
				if (friendly) {
					return true;
				} else {
					Language.sendError("user.target.entity.towny", player);
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Towny");
		if (plugin != null && plugin instanceof Towny) {
			Zephyrus.getPlugin().getLogger().info("[Plugin Hooks] Found and hooked Towny");
			return true;
		}
		return false;
	}

	@Override
	public void setupHook() {
		this.towny = (Towny) Bukkit.getPluginManager().getPlugin("Towny");
	}

}
