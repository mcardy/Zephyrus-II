package com.minnymin.zephyrus.core.spell.buff;

import java.util.HashMap;
import java.util.Map;


import org.bukkit.configuration.ConfigurationSection;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.state.StateList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Feather.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class FeatherSpell extends Spell implements Configurable {

	private int duration;

	public FeatherSpell() {
		super("feather", "Lets you float like a feather", 100, 10, AspectList.newList(Aspect.WIND, 40, Aspect.MOVEMENT,
				20), 2, SpellElement.AIR, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.addState(StateList.FEATHER, duration * power);
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Duration", 60);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.duration = config.getInt("Duration");
	}

}
