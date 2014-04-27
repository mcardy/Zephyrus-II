package net.minezrc.zephyrus.core.spell.illusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.core.util.reflection.NMSUtils;
import net.minezrc.zephyrus.core.util.reflection.ReflectionUtils;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

/**
 * Zephyrus - Confuse.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class ConfuseSpell extends Spell implements ConfigurableSpell {

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
