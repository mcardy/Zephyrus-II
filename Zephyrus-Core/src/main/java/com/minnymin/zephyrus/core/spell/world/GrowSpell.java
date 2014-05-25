package com.minnymin.zephyrus.core.spell.world;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.item.action.plant.PlantRegistry;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - GrowSpell.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 8)
public class GrowSpell extends Spell {

	public GrowSpell() {
		super("grow", "Grows your target block", 20, 2, AspectList.newList(Aspect.PLANT, 30, Aspect.LIFE, 15,
				Aspect.WOOD, 15), 1, SpellElement.EARTH, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Block target = (Block) user.getTarget(this).getTarget();
		if (PlantRegistry.grow(target)) {
			Location loc = target.getLocation();
			loc.setX(loc.getX() + 0.6);
			loc.setZ(loc.getZ() + 0.6);
			loc.setY(loc.getY() + 0.3);
			ParticleEffects.sendParticle(Particle.GREEN_SPARKLE, loc, (float) 0.25, (float) 0.1, (float) 0.25, 100, 20);
			return CastResult.SUCCESS;
		}
		return CastResult.FAILURE;
	}

}
