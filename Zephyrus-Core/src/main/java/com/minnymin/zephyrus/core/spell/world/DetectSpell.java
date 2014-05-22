package com.minnymin.zephyrus.core.spell.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Detect.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class DetectSpell extends Spell implements Configurable {

	private int radius;

	public DetectSpell() {
		super("detect", "Detects monsters around you", 50, 5, AspectList.newList(Aspect.BEAST, 40, Aspect.LIFE, 20,
				Aspect.MOVEMENT, 20), 2, SpellElement.NEUTREAL, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		int radius = this.radius * power;
		List<Entity> entityList = player.getNearbyEntities(radius, radius, radius);
		Language.sendMessage("spell.detect.nearby", ChatColor.GRAY + "Nearby mobs:", player);
		Map<String, Integer> entityMap = new HashMap<String, Integer>();
		for (Entity entity : entityList) {
			if (entity instanceof LivingEntity) {
				if (entityMap.containsKey(entity.getType().toString())) {
					entityMap.put(entity.getType().toString(), entityMap.get(entity.getType().toString()) + 1);
				} else {
					entityMap.put(entity.getType().toString(), 1);
				}
			}
		}
		for (Entry<String, Integer> entry : entityMap.entrySet()) {
			player.sendMessage(ChatColor.GREEN + WordUtils.capitalize(entry.getKey().toLowerCase().replace("_", " "))
					+ ": " + entry.getValue());
		}
		if (entityMap.isEmpty()) {
			Language.sendMessage("spell.detect.none", ChatColor.GRAY + "None...", player);
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Radius", 10);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("Radius");
	}

}
