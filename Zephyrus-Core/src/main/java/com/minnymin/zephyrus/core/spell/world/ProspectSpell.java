package com.minnymin.zephyrus.core.spell.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ProspectSpell.java
 * 
 * @author minnymin3
 * 
 */

public class ProspectSpell extends Spell implements Configurable {
	
	private int radius;

	public ProspectSpell() {
		super("prospect", "Find nearby ores", 100, 10, AspectList.newList(Aspect.STONE, 60, Aspect.VALUE, 30,
				Aspect.METAL, 30), 4, SpellElement.EARTH, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		int range = radius * power;
		final Block block = user.getPlayer().getLocation().getBlock();
		Set<String> s = new HashSet<String>();
		for (int x = -(range); x <= range; x++) {
			for (int y = -(range); y <= range; y++) {
				for (int z = -(range); z <= range; z++) {
					if (block.getRelative(x, y, z).getType() == Material.GOLD_ORE) {
						s.add(Language.get("spell.prospect.gold", ChatColor.GOLD + "Gold"));
					}
					if (block.getRelative(x, y, z).getType() == Material.IRON_ORE) {
						s.add(Language.get("spell.prospect.iron", ChatColor.GRAY + "Iron"));
					}
					if (block.getRelative(x, y, z).getType() == Material.COAL_ORE) {
						s.add(Language.get("spell.prospect.coal", ChatColor.DARK_GRAY + "Coal"));
					}
					if (block.getRelative(x, y, z).getType() == Material.REDSTONE_ORE) {
						s.add(Language.get("spell.prospect.redstone", ChatColor.RED + "Redstone"));
					}
					if (block.getRelative(x, y, z).getType() == Material.LAPIS_ORE) {
						s.add(Language.get("spell.prospect.lapis", ChatColor.BLUE + "Lapis Lazulite"));
					}
					if (block.getRelative(x, y, z).getType() == Material.DIAMOND_ORE) {
						s.add(Language.get("spell.prospect.diamond", ChatColor.AQUA + "Diamond"));
					}
				}
			}
		}
		if (s.size() == 0) {
			StringBuilder ores = new StringBuilder();
			for (String str : s) {
				ores.append(str + " ");
			}
			Language.sendMessage("spell.prospect.ores", user.getPlayer(),
					"[ORES]", ores.toString());
		} else {
			Language.sendMessage("spell.prospect.noores", user.getPlayer());
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Radius", 4);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		radius = config.getInt("Radius");
	}

}
