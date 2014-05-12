package com.minnymin.zephyrus.core.spell.mobility;


import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.Target;
import com.minnymin.zephyrus.user.Target.TargetType;
import com.minnymin.zephyrus.user.Targeted;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Blink.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK)
public class BlinkSpell extends Spell {

	public BlinkSpell() {
		super("blink", "Gets you from point to point without bothering with whats in between", 50, 8, AspectList
				.newList(Aspect.ENDER, 40, Aspect.MOVEMENT, 20, Aspect.MYSTICAL, 20), 2, SpellElement.ENDER,
				SpellType.MOBILITY);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		Target target = user.getTarget(this.getDefaultName());
		if (target != null && target.getBlock() != null && target.getBlock().getType() != Material.AIR) {
			Block block = target.getBlock();
			Location bottom = block.getLocation().add(0, 1, 0);
			Location top = block.getLocation().add(0, 2, 0);
			Block bottomBlock = bottom.getBlock();
			Block topBlock = top.getBlock();
			if (bottomBlock.getType() == Material.AIR && topBlock.getType() == Material.AIR) {
				Location previous = player.getLocation();
				Location teleport = block.getLocation().add(0.5, 1.25, 0.5);
				teleport.setPitch(previous.getPitch());
				teleport.setYaw(previous.getYaw());
				ParticleEffects.sendParticle(Particle.ENDER, previous, 1, 1, 1, 1, 16);
				player.teleport(teleport);
				player.getWorld().playEffect(teleport, Effect.ENDER_SIGNAL, 0);
				return CastResult.SUCCESS;
			}
		}
		Language.sendError("spell.blink.failure", "You can't blink there!", player);
		return CastResult.FAILURE;
	}

}
