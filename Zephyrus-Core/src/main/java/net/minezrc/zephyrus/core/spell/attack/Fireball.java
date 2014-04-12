package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.projectile.BallProjectile;
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
public class Fireball extends Spell {

	public Fireball() {
		super("fireball", "Launches a ball of fire", 25, 1, AspectList.newList()
				.setAspectTypes(Aspect.FIRE, Aspect.POWER).setAspectValues(20, 4), 1, SpellElement.FIRE,
				SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		BallProjectile projectile = new BallProjectile(Particle.FIRE, 4 * power, 16 * power);
		projectile.launchProjectile(user.getPlayer());
		return CastResult.SUCCESS;
	}

}
