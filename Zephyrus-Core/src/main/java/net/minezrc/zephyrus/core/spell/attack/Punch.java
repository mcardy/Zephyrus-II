package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.spell.annotation.Targeted;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Punch.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY)
public class Punch extends Spell {

	public Punch() {
		super("punch", "Punches your target with a superpunch", 25, 2, AspectList.newList()
				.setAspectTypes(Aspect.ATTACK, Aspect.POWER), 1, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getTarget(this).getEntity().damage(2.0D * power, user.getPlayer());
		return CastResult.SUCCESS;
	}

}
