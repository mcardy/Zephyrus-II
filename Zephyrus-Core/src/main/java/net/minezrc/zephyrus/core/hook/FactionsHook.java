package net.minezrc.zephyrus.core.hook;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.config.ConfigOptions;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.hook.ProtectionHook;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.massivecraft.factions.Factions;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;
import com.massivecraft.mcore.ps.PS;

/**
 * Zephyrus - FactionsHook.java
 * 
 * @author minnymin3
 * 
 */

public class FactionsHook implements ProtectionHook {

	@Override
	public boolean canBuild(Player player, Block block) {
		return canBuild(player, block.getLocation());
	}

	@Override
	public boolean canBuild(Player player, Location loc) {
		UPlayer uplayer = UPlayer.get(player);
		Faction faction = BoardColls.get().getFactionAt(PS.valueOf(loc));
		if (faction != null) {
			if (uplayer.getFactionId().equals(faction.getId())) {
				return true;
			}
			Language.sendError("user.target.block.faction", "You cannot target blocks inside of a faction", player);
			return false;
		}
		return true;
	}

	@Override
	public boolean canCast(Player player, Spell spell) {
		UPlayer uplayer = UPlayer.get(player);
		Location loc = player.getLocation();
		Faction faction = BoardColls.get().getFactionAt(PS.valueOf(loc));
		if (faction != null && !faction.isNone()) {
			if (ConfigOptions.FACTION_CASTING) {
				if (uplayer.getFactionId().equals(faction.getId())) {
					return true;
				}
			}
			Language.sendError("spell.cast.faction", "You cannot cast spells inside of a faction", player);
			return false;
		}
		return true;
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			Player target = (Player) entity;
			UPlayer uplayer = UPlayer.get(player);
			UPlayer utarget = UPlayer.get(target);
			if (uplayer.getFaction() != null && uplayer.getFaction().getUPlayers().contains(utarget)) {
				if (friendly) {
					return true;
				} else {
					Language.sendError("user.target.entity.faction", "You cannot target that player", player);
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
		if (plugin != null && plugin instanceof Factions) {
			Zephyrus.getPlugin().getLogger().info("[Plugin Hooks] Found and hooked Factions");
			return true;
		}
		return false;
	}

	@Override
	public void setupHook() {
	}

}
