package net.minezrc.zephyrus.core.spell.mobility;

import org.bukkit.entity.Player;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Woosh.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Woosh extends Spell {

	public Woosh() {
		super("woosh", "Woosh forwards in a burst of speed", 25, 3, AspectList.newList(Aspect.WIND, 40, Aspect.MOVEMENT, 20),
				2, SpellElement.AIR, SpellType.MOBILITY);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		player.setVelocity(player.getEyeLocation().getDirection().multiply(4 * power));
		return CastResult.SUCCESS;
	}

}
