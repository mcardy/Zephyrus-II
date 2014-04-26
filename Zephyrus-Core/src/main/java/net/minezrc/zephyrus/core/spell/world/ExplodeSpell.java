package net.minezrc.zephyrus.core.spell.world;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.user.Targeted;
import net.minezrc.zephyrus.user.User;

import org.bukkit.block.Block;

/**
 * Zephyrus - Explode.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 15)
public class ExplodeSpell extends Spell {

	public ExplodeSpell() {
		super("explode", "Ka-BOOM", 200, 10, AspectList.newList(Aspect.DESTRUCTION, 100, Aspect.ENERGY, 50,
				Aspect.FIRE, 50), 5, SpellElement.FIRE, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Block target = user.getTarget(getDefaultName()).getBlock();
		if (target.getWorld().createExplosion(target.getLocation(), 4F)) {
			return CastResult.SUCCESS;
		}
		return CastResult.FAILURE;
	}

}
