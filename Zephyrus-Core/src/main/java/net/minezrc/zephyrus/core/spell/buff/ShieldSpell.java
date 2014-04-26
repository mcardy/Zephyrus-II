package net.minezrc.zephyrus.core.spell.buff;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.state.StateList;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Shield.java
 * 
 * @author minnymin3
 * 
 */

public class ShieldSpell extends Spell implements ConfigurableSpell {

	private int duration;
	
	public ShieldSpell() {
		super("shield", "Creates a shield of energy around you", 200, 10, AspectList.newList(Aspect.DEFENSE, 25,
				Aspect.WEAPON, 25, Aspect.ENERGY, 50, Aspect.MYSTICAL, 50), 3, SpellElement.ARCANE, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.addState(StateList.SHIELD, duration);
		return null;
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
