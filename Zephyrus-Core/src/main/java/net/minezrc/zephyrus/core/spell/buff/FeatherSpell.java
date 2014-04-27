package net.minezrc.zephyrus.core.spell.buff;

import java.util.HashMap;
import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.state.StateList;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.configuration.ConfigurationSection;

/**
 * Zephyrus - Feather.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class FeatherSpell extends Spell implements ConfigurableSpell {

	private int duration;

	public FeatherSpell() {
		super("feather", "Lets you float like a feather", 100, 10, AspectList.newList(Aspect.WIND, 50, Aspect.MOVEMENT,
				25), 3, SpellElement.AIR, SpellType.BUFF);
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
