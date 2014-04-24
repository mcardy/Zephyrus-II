package net.minezrc.zephyrus.core.spell.mobility;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.spell.annotation.Targeted;
import net.minezrc.zephyrus.user.Target;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Blink.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK)
public class Blink extends Spell {

	public Blink() {
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
