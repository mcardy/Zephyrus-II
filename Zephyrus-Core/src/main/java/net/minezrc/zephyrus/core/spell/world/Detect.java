package net.minezrc.zephyrus.core.spell.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Detect.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Detect extends Spell implements ConfigurableSpell {

	private int radius;

	public Detect() {
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
