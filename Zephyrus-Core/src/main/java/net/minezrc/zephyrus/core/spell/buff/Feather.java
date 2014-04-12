package net.minezrc.zephyrus.core.spell.buff;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.state.StateList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Feather.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Feather extends Spell {

	public Feather() {
		super("feather", "Lets you float like a feather", 100, 10, AspectList.newList(Aspect.WIND, 32), 3,
				SpellElement.AIR, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.addState(StateList.FEATHER, 60);
		return CastResult.SUCCESS;
	}

}
