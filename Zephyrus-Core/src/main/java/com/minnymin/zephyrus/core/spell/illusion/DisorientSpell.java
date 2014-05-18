package com.minnymin.zephyrus.core.spell.illusion;


import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
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
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Disorient.java
 * 
 * @author minnymin3
 * 
 */

public class DisorientSpell extends Spell {

	public DisorientSpell() {
		super("disorient", "Disorients your target", 100, 20, AspectList.newList(Aspect.MOVEMENT, 50, Aspect.ENDER, 25,
				Aspect.BEAST, 25), 4, SpellElement.NEUTREAL, SpellType.ILLUSION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		LivingEntity entity = (LivingEntity) user.getTarget(this).getTarget();
		if (entity instanceof Player) {
			Player target = (Player) entity;
			Location ploc = target.getLocation();
			ploc.setYaw(ploc.getYaw() - 180);
			target.teleport(ploc);
			Location loc = target.getEyeLocation();
			for (double[] pos : MathUtils.getCircleMap()) {
				Location particle = loc.clone().add(pos[0] / 2F, -0.5, pos[1] / 2F);
				ParticleEffects.sendParticle(Particle.DEPTH_SUSPEND, particle, 0.1F, 0.5F, 0.1F, 0F, 4);
			}
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.notarget", "You do not have a target", user.getPlayer());
			return CastResult.FAILURE;
		}
	}

}
