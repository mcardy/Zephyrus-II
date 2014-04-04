package net.minezrc.zephyrus.core.spell.mobility;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Bindable;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
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
public class Blink implements Spell {

	@Override
	public String getName() {
		return "blink";
	}

	@Override
	public String getDescription() {
		return "Gets you from point A to point B. How you ask? MAGIC!";
	}

	@Override
	public int getManaCost() {
		return 50;
	}

	@Override
	public int getXpReward() {
		return 8;
	}

	@Override
	public AspectList getRecipe() {
		return AspectList.newList().setAspectTypes(Aspect.ENDER, Aspect.MAGIC).setAspectValues(24, 4);
	}

	@Override
	public int getRequiredLevel() {
		return 3;
	}

	@Override
	public SpellElement getElement() {
		return SpellElement.ENDER;
	}

	@Override
	public SpellType getType() {
		return SpellType.MOBILITY;
	}

	@Override
	public CastPriority getPriority() {
		return CastPriority.LOW;
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
	}

	@SuppressWarnings("deprecation")
	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		Block block = player.getTargetBlock(null, 30);
		if (block != null && block.getType() != Material.AIR) {
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
				return CastResult.NORMAL_SUCCESS;
			}
		}
		Language.sendError("spell.blink.failure", "You can't blink there!", player);
		return CastResult.NORMAL_FAIL;
	}

}
