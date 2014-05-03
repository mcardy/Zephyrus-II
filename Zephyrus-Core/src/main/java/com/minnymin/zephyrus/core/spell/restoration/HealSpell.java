package com.minnymin.zephyrus.core.spell.restoration;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.MathUtils;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Heal.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class HealSpell extends Spell {

	public HealSpell() {
		super("heal", "Quickly regenerate health", 10, 1, AspectList.newList(Aspect.LIFE, 30, Aspect.BEAST, 15), 1, SpellElement.NEUTREAL,
				SpellType.RESTORATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		player.setHealth(player.getHealth() < player.getMaxHealth() ? player.getHealth() + 1 : player.getMaxHealth());
		Location loc = player.getEyeLocation();
		for (double[] pos : MathUtils.getCircleMap()) {
			Location particle = loc.clone().add(pos[0] / 2F, -0.5, pos[1] / 2F);
			ParticleEffects.sendParticle(Particle.REDSTONE_DUST, particle, 0.1F, 0.5F, 0.1F, 0F, 4);
		}
		return CastResult.SUCCESS;
	}

}
