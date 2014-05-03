package com.minnymin.zephyrus.core.spell.restoration;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
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
 * Zephyrus - Feeder.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(friendly = true, type = TargetType.ENTITY)
public class FeederSpell extends Spell {

	public FeederSpell() {
		super("feeder", "Feeds your target", 15, 1, AspectList.newList(Aspect.FLESH, 50, Aspect.LIFE, 25, Aspect.BEAST,
				25), 3, SpellElement.NEUTREAL, SpellType.RESTORATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		if (user.getTarget(this.getDefaultName()).getEntity() instanceof Player) {
			Player target = (Player) user.getTarget(this.getDefaultName()).getEntity();
			target.setFoodLevel(target.getFoodLevel() < 20 ? target.getFoodLevel() + 1 : 20);
			Location loc = target.getEyeLocation();
			for (double[] pos : MathUtils.getCircleMap()) {
				Location particle = loc.clone().add(pos[0] / 2F, -0.5, pos[1] / 2F);
				ParticleEffects.sendParticle(Particle.ENCHANTMENT_TABLE, particle, 0.1F, 0.5F, 0.1F, 0F, 4);
			}
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.notarget", "You do not have a target", user.getPlayer());
			return CastResult.FAILURE;
		}
	}

}
