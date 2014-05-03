package com.minnymin.zephyrus.core.spell.attack;

import java.util.List;
import java.util.Map;


import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Butcher.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class ButcherSpell extends Spell implements ConfigurableSpell {

	private int radius;
	private int damage;

	public ButcherSpell() {
		super("butcher", "Butchers all monsters nearby", 100, 10, AspectList
				.newList(Aspect.BEAST, 30, Aspect.WEAPON, 30), 4, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		int radius = this.radius * power;
		List<Entity> entities = player.getNearbyEntities(radius, radius, radius);
		for (Entity entity : entities) {
			if (entity instanceof Monster) {
				((Monster) entity).damage(damage);
			}
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Radius", 5);
		map.put("Damage", 5);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("Radius");
		this.damage = config.getInt("Damage");
	}

}
