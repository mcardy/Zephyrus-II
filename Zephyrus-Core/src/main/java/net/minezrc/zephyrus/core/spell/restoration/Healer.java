package net.minezrc.zephyrus.core.spell.restoration;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.MathUtils;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Bindable;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

/**
 * Zephyrus - Healer.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Healer implements Spell {

	@Override
	public String getName() {
		return "healer";
	}

	@Override
	public String getDescription() {
		return "Heals your target";
	}

	@Override
	public int getManaCost() {
		return 15;
	}

	@Override
	public int getXpReward() {
		return 1;
	}

	@Override
	public AspectList getRecipe() {
		return AspectList.newList().setAspectTypes(Aspect.PLANT, Aspect.ANIMAL).setAspectValues(8, 8);
	}

	@Override
	public int getRequiredLevel() {
		return 3;
	}

	@Override
	public SpellElement getElement() {
		return SpellElement.NEUTREAL;
	}

	@Override
	public SpellType getType() {
		return SpellType.RESTORATION;
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		if (user.getTarget(this) != null && user.getTarget(this).getEntity() != null) {
			LivingEntity target = user.getTarget(this).getEntity();
			target.setHealth(target.getHealth() < target.getMaxHealth() ? target.getHealth() + 1 : target.getMaxHealth());
			Location loc = target.getEyeLocation();
			for (double[] pos : MathUtils.getCircleMap()) {
				Location particle = loc.clone().add(pos[0] / 2F, -0.5, pos[1] / 2F);
				ParticleEffects.sendParticle(Particle.REDSTONE_DUST, particle, 0.1F, 0.5F, 0.1F, 0F, 4);
			}
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.notarget", "You do not have a target", user.getPlayer());
			return CastResult.FAILURE;
		}
	}

}
