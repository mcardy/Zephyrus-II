package com.minnymin.zephyrus.core.spell.restoration;


import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.MathUtils;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.SpellAttributes.TargetType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.Targeted;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Healer.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(friendly = true, type = TargetType.ENTITY)
public class HealerSpell extends Spell {

	public HealerSpell() {
		super("healer", "Heals your target", 15, 1, AspectList.newList(Aspect.LIFE, 50, Aspect.BEAST, 25), 3,
				SpellElement.NEUTREAL, SpellType.RESTORATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		LivingEntity target = user.getTarget(this.getDefaultName()).getEntity();
		target.setHealth(target.getHealth() < target.getMaxHealth() ? target.getHealth() + 1 : target.getMaxHealth());
		Location loc = target.getEyeLocation();
		for (double[] pos : MathUtils.getCircleMap()) {
			Location particle = loc.clone().add(pos[0] / 2F, -0.5, pos[1] / 2F);
			ParticleEffects.sendParticle(Particle.REDSTONE_DUST, particle, 0.1F, 0.5F, 0.1F, 0F, 4);
		}
		return CastResult.SUCCESS;
	}

}
