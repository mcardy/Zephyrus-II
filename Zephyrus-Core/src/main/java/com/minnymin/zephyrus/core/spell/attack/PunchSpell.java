package com.minnymin.zephyrus.core.spell.attack;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;

/**
 * Zephyrus - Punch.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY)
public class PunchSpell extends Spell implements Configurable {

	private int damage;

	public PunchSpell() {
		super("punch", "Punches your target with a superpunch", 25, 2, AspectList.newList(Aspect.WEAPON, 40,
				Aspect.FLESH, 20, Aspect.MYSTICAL, 20), 2, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		LivingEntity entity = (LivingEntity) user.getTarget(this).getTarget();
		entity.damage(damage * power, user.getPlayer());
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Damage", 2);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.damage = config.getInt("Damage");
	}

}
