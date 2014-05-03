package com.minnymin.zephyrus.core.spell.attack;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.projectile.DamagingBeamProjectile;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;


/**
 * Zephyrus - Bolt.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class BoltSpell extends Spell {

	public BoltSpell() {
		super("bolt", "Launches a ball of electricity", 20, 1, AspectList
				.newList(Aspect.ENERGY, 30, Aspect.WIND, 15, Aspect.MOVEMENT, 15, Aspect.WEAPON, 15), 1, SpellElement.AIR, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		DamagingBeamProjectile projectile = new DamagingBeamProjectile(Particle.MAGIC_CRITICAL_HIT, 4 * power);
		projectile.launchProjectile(user.getPlayer());
		return CastResult.SUCCESS;
	}

}
