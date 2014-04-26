package net.minezrc.zephyrus.core.spell.world;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Dim.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Bright extends Spell {

	public Bright() {
		super("bright", "Destroy the darkness and embrace the light", 250, 50, AspectList.newList(Aspect.TIME, 50,
				Aspect.LIGHT, 25), 6, SpellElement.LIGHT, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().getWorld().setTime(2000);
		return CastResult.SUCCESS;
	}

}
