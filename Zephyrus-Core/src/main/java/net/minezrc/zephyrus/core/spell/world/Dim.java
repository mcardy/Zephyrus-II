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
public class Dim extends Spell {

	public Dim() {
		super("dim", "Destroy the light and embrace the darkness", 250, 50, AspectList.newList(Aspect.TIME, 50,
				Aspect.DARKNESS, 25), 6, SpellElement.DARKNESS, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().getWorld().setTime(14000);
		return CastResult.SUCCESS;
	}

}
