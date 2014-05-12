package com.minnymin.zephyrus.core.spell.attack;

import org.bukkit.entity.SmallFireball;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Firecharge.java
 * 
 * @author minnymin3
 * 
 */

public class FirechargeSpell extends Spell {

	public FirechargeSpell() {
		super("firecharge", "Shoots a firecharge", 50, 4, AspectList
				.newList(Aspect.FIRE, 30, Aspect.ENERGY, 15, Aspect.DESTRUCTION, 15), 2, SpellElement.FIRE,
				SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().launchProjectile(SmallFireball.class);
		return CastResult.SUCCESS;
	}

}
