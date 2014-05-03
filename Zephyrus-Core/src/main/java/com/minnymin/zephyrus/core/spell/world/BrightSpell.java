package com.minnymin.zephyrus.core.spell.world;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Dim.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class BrightSpell extends Spell {

	public BrightSpell() {
		super("bright", "Destroy the darkness and embrace the light", 250, 50, AspectList.newList(Aspect.TIME, 50,
				Aspect.LIGHT, 25), 6, SpellElement.LIGHT, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().getWorld().setTime(2000);
		return CastResult.SUCCESS;
	}

}
