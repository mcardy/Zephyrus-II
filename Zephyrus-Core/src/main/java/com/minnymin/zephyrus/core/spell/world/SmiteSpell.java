package com.minnymin.zephyrus.core.spell.world;


import org.bukkit.block.Block;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.target.Targeted;
import com.minnymin.zephyrus.user.target.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Smite.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 50)
public class SmiteSpell extends Spell {

	public SmiteSpell() {
		super("smite", "Call down the power of the skies upon the earth", 50, 5, AspectList.newList(Aspect.ENERGY, 50,
				Aspect.WIND, 25, Aspect.LIGHT, 25), 3, SpellElement.AIR, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Block block = (Block) user.getTarget(this).getTarget();
		user.getPlayer().getWorld().strikeLightning(block.getLocation());
		return CastResult.SUCCESS;
	}

}
