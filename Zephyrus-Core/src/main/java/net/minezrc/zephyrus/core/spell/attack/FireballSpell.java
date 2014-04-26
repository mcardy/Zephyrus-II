package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.projectile.DamagingBallProjectile;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

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
