package com.minnymin.zephyrus.core.spell.mobility;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.BlockUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - PhaseSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK, range = 30)
public class PhaseSpell extends Spell {

	public PhaseSpell() {
		super("phase", "Ever wanted to go through blocks? Now you can!", 100, 10, AspectList.newList(Aspect.MOVEMENT,
				80, Aspect.ENDER, 40, Aspect.MYSTICAL, 40), 6, SpellElement.ENDER, SpellType.MOBILITY);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		Block block = (Block) user.getTarget(this).getTarget();
		Location loc = block.getLocation();
		BlockFace blockFace = posToBlockFace(player);
		if (!canPhase(loc, blockFace)) {
			Language.sendError("spell.phase.fail", "You cannot phase through that block", player);
			return CastResult.FAILURE;
		}
		loc.add(0.5, -1, 0.5);
		loc.setYaw(player.getLocation().getYaw());
		loc.setPitch(player.getLocation().getPitch());
		switch (blockFace) {
		case NORTH:
			loc.setZ(loc.getZ() - 1);
			break;
		case SOUTH:
			loc.setZ(loc.getZ() + 1);
			break;
		case EAST:
			loc.setX(loc.getX() + 1);
			break;
		case WEST:
			loc.setX(loc.getX() - 1);
			break;
		case UP:
			loc.setY(loc.getY() + 3);
			break;
		case DOWN:
			loc.setY(loc.getY() - 1);
			break;
		default:
			break;
		}
		player.teleport(loc);
		player.getWorld().playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, -1F);
		Location particleLocation = player.getLocation().clone();
		particleLocation.add(0.5, 2, 0.5);
		ParticleEffects.sendParticle(Particle.ENDER, player.getLocation(), 0, 0, 0, 2, 40);
		return CastResult.SUCCESS;
	}

	private boolean canPhase(Location targetLoc, BlockFace blockFace) {
		if (targetLoc.getBlock().getType() == Material.AIR) {
			return false;
		}
		Location loc1 = targetLoc.clone();
		Location loc2;
		switch (blockFace) {
		case NORTH:
			loc1.add(0, 0, -1);
			break;
		case SOUTH:
			loc1.add(0, 0, 1);
			break;
		case EAST:
			loc1.add(1, 0, 0);
			break;
		case WEST:
			loc1.add(-1, 0, 0);
			break;
		case UP:
			loc1.add(0, 3, 0);
			break;
		case DOWN:
			loc1.add(0, -1, 0);
			break;
		default:
			break;
		}
		loc2 = loc1.clone().add(0, -1, 0);
		System.out.println(blockFace);
		System.out.println(loc1.getX() + " " + loc1.getY() + " " + loc1.getZ() + " " + loc2.getX() + " " + loc2.getY()
				+ " " + loc2.getZ());
		if (loc1.getBlock().getType() == Material.AIR && loc2.getBlock().getType() == Material.AIR) {
			return true;
		}
		return false;
	}

	private BlockFace posToBlockFace(Player player) {
		@SuppressWarnings("deprecation")
		List<Block> blocks = player.getLastTwoTargetBlocks(BlockUtils.getTransparent(), 30);
		return blocks.get(0).getFace(blocks.get(1));
	}

}
