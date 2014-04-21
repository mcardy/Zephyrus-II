package net.minezrc.zephyrus.core.spell.illusion;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.MathUtils;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Disorient.java
 * 
 * @author minnymin3
 * 
 */

public class Disorient extends Spell {

	public Disorient() {
		super("disorient", "Disorients your target", 100, 20, AspectList.newList(Aspect.MOVEMENT, 50, Aspect.ENDER, 25,
				Aspect.BEAST, 25), 4, SpellElement.NEUTREAL, SpellType.ILLUSION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		LivingEntity entity = user.getTarget(this).getEntity();
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
