package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

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
		user.getPlayer().launchProjectile(org.bukkit.entity.Fireball.class);
		return CastResult.SUCCESS;
	}

}
