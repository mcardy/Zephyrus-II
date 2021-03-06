package com.minnymin.zephyrus.core.spell.buff;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.spell.world.DigSpell;
import com.minnymin.zephyrus.core.state.StateList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - BuildSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Prerequisite(requiredSpell = DigSpell.class)
public class BuildSpell extends Spell {

	public BuildSpell() {
		super("build", "Allows you to build far away", 300, 10, AspectList.newList(Aspect.CONSTRUCT, 100,
				Aspect.DESTRUCTION, 100), 8, SpellElement.NEUTREAL, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.addState(StateList.BUILD, 30 * power);
		return CastResult.SUCCESS;
	}

}
