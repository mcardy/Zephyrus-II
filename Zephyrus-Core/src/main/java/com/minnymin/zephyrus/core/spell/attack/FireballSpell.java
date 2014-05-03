package com.minnymin.zephyrus.core.spell.attack;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.projectile.DamagingBallProjectile;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;


/**
 * Zephyrus - Fireball.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class FireballSpell extends Spell {

	public FireballSpell() {
		super("fireball", "Launches a ball of fire", 25, 1, AspectList
				.newList(Aspect.FIRE, 30, Aspect.MOVEMENT, 15, Aspect.WEAPON, 15), 1, SpellElement.FIRE,
				SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		DamagingBallProjectile projectile = new DamagingBallProjectile(Particle.FIRE, 4 * power, 16 * power);
		projectile.launchProjectile(user.getPlayer());
		return CastResult.SUCCESS;
	}

}
