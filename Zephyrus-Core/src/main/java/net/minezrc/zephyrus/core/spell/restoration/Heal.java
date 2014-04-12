package net.minezrc.zephyrus.core.spell.restoration;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.MathUtils;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Heal.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Heal extends Spell {

	public Heal() {
		super("heal", "Quickly regenerate health", 10, 1, AspectList.newList(Aspect.LIFE, 8), 1, SpellElement.NEUTREAL,
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
