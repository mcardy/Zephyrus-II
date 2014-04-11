package net.minezrc.zephyrus.core.hook;

import net.minezrc.zephyrus.hook.ProtectionHook;
import net.minezrc.zephyrus.spell.Spell;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
	public boolean canCast(Player player, Spell spell) {
		UPlayer uplayer = UPlayer.get(player);
		Location loc = player.getLocation();
		Faction faction = BoardColls.get().getFactionAt(PS.valueOf(loc));
		if (faction == null) {
			return true;
		} else if (faction.getUPlayers().contains(uplayer)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canTarget(Player player, LivingEntity entity, boolean friendly) {
		if (entity instanceof Player) {
			Player target = (Player) entity;
			UPlayer uplayer = UPlayer.get(player);
			UPlayer utarget = UPlayer.get(target);
			if (uplayer.getFaction() != null && uplayer.getFaction().getUPlayers().contains(utarget)) {
				return friendly;
			}
		}
		return true;
	}

	@Override
	public boolean checkHook() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");
		if (plugin != null && plugin instanceof Factions) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setupHook() {
	}

}