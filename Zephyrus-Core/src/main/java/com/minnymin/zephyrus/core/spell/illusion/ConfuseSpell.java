package com.minnymin.zephyrus.core.spell.illusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.core.util.reflection.NMSUtils;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Confuse.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class ConfuseSpell extends Spell implements Configurable {

	private int radius;

	public ConfuseSpell() {
		super("confuse", "Confuses nearby mobs to fight eachouther", 100, 5, AspectList.newList(Aspect.BEAST, 40,
				Aspect.FLESH, 20), 2, SpellElement.NEUTREAL, SpellType.ILLUSION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		int radius = this.radius * power;
		List<Entity> entityList = user.getPlayer().getNearbyEntities(radius, radius, radius);
		List<Monster> monsterList = new ArrayList<Monster>();
		for (Entity entity : entityList) {
			if (entity instanceof Monster) {
				monsterList.add((Monster) entity);
			}
		}
		for (int i = 0; i < monsterList.size(); i++) {
			int targetIndex = i + 1;
			if (targetIndex >= monsterList.size()) {
				targetIndex = 0;
			}
			Monster monster = monsterList.get(i);
			Monster target = monsterList.get(targetIndex);
			monster.setTarget(target);
			Object monsterHandle = NMSUtils.getHandle(monster);
			Object targetHandle = NMSUtils.getHandle(target);
			ReflectionUtils.invokeMethod(monsterHandle, "setGoalTarget", targetHandle);
			ParticleEffects.sendParticle(Particle.ANGRY_VILLAGER, monster.getTarget().getLocation().add(0, 1, 0),
					0.25F, 0.25F, 0.25F, 5, 5);
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("radius", 8);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("radius");
	}

}
