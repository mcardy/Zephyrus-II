package com.minnymin.zephyrus.core.spell.world;


import org.bukkit.block.Block;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.SpellAttributes.TargetType;
import com.minnymin.zephyrus.user.Targeted;
import com.minnymin.zephyrus.user.User;

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
