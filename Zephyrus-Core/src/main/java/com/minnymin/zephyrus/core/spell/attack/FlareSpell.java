package com.minnymin.zephyrus.core.spell.attack;

import org.bukkit.entity.Fireball;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.Target.TargetType;
import com.minnymin.zephyrus.user.Targeted;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - FlareSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK, range = 200)
@Prerequisite(requiredSpell = FireballSpell.class)
// Making sure player can target fireball destination
public class FlareSpell extends Spell {

	public FlareSpell() {
		super("flare", "Fire a ball of fire that will explode into more fire", 80, 8, AspectList.newList(Aspect.FIRE,
				50, Aspect.ENERGY, 25, Aspect.DESTRUCTION, 25), 4, SpellElement.FIRE, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().launchProjectile(Fireball.class);
		return CastResult.SUCCESS;
	}

}
