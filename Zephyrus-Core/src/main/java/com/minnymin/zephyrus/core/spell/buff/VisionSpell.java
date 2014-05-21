package com.minnymin.zephyrus.core.spell.buff;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
 * Zephyrus - VisionSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class VisionSpell extends Spell implements ConfigurableSpell {

	private int duration;

	public VisionSpell() {
		super("vanish", "Ever wanted to see everything...?", 90, 9, AspectList.newList(Aspect.LIGHT, 40, Aspect.BEAST,
				20), 2, SpellElement.LIGHT, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, duration * 20 * power, 1));
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
