package net.minezrc.zephyrus.core.spell.world;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.user.Targeted;
import net.minezrc.zephyrus.user.User;

import org.bukkit.block.Block;

/**
 * Zephyrus - Smite.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 50)
public class SmiteSpell extends Spell {

	public SmiteSpell() {
		super("smite", "Call down the power of the skies upon the earth", 50, 5, AspectList.newList(Aspect.ENERGY, 50,
				Aspect.WIND, 25, Aspect.LIGHT, 25), 3, SpellElement.AIR, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Block block = user.getTarget(getDefaultName()).getBlock();
		user.getPlayer().getWorld().strikeLightning(block.getLocation());
		return CastResult.SUCCESS;
	}

}
