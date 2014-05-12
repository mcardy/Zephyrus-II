package com.minnymin.zephyrus.core.spell.buff;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.state.StateList;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - FireShieldSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class FireShieldSpell extends Spell implements ConfigurableSpell {

	private int duration;
	
	public FireShieldSpell() {
		super("fireshield", "Creates a shield of fire around you", 400, 40, AspectList.newList(Aspect.FIRE, 150,
				Aspect.DEFENSE, 75, Aspect.MYSTICAL, 75), 7, SpellElement.FIRE, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.addState(StateList.FIRE_SHIELD, duration * power);
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
