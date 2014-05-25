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
public class DimSpell extends Spell {

	public DimSpell() {
		super("dim", "Destroy the light and embrace the darkness", 250, 50, AspectList.newList(Aspect.TIME, 50,
				Aspect.DARKNESS, 25), 7, SpellElement.DARKNESS, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().getWorld().setTime(14000);
		return CastResult.SUCCESS;
	}

}
