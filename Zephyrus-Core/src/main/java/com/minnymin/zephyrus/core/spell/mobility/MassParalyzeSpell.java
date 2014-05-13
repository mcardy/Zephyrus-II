package com.minnymin.zephyrus.core.spell.mobility;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - MassParalyzeSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class MassParalyzeSpell extends Spell implements ConfigurableSpell {

	private int duration;
	private int range;

	public MassParalyzeSpell() {
		super("massparalyze", "Freeze all mobs around you! Don't let 'em move!", 200, 20, AspectList.newList(
				Aspect.MOVEMENT, 150, Aspect.TIME, 75, Aspect.BEAST, 75), 7, SpellElement.NEUTREAL, SpellType.MOBILITY);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		for (Entity en : user.getPlayer().getNearbyEntities(range, range, range)) {
			if (en instanceof LivingEntity
					&& Zephyrus.getHookManager().canTarget(user.getPlayer(), (LivingEntity) en, false)) {
				LivingEntity entity = (LivingEntity) en;
				entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration * 20 * power, 100));
			}
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Duration", 30);
		map.put("Range", 5);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		duration = config.getInt("Duration");
		range = config.getInt("Range");
	}

}
