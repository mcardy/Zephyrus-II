package com.minnymin.zephyrus.core.spell.mobility;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Paralyze.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY, friendly = false, range = 20)
public class ParalyzeSpell extends Spell implements Configurable {

	private int duration;
	
	public ParalyzeSpell() {
		super("paralyze", "Freeze your target! Don't let 'em move!", 40, 4, AspectList.newList(Aspect.MOVEMENT, 50,
				Aspect.TIME, 25, Aspect.BEAST, 25), 3, SpellElement.NEUTREAL, SpellType.MOBILITY);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		LivingEntity entity = (LivingEntity) user.getTarget(this).getTarget();
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20 * power, 100));
		return CastResult.SUCCESS;
	}
	
	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Duration", 30);
		return map;
	}
	
	@Override
	public void loadConfiguration(ConfigurationSection config) {
		duration = config.getInt("Duration");
	}

}
