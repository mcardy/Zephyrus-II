package com.minnymin.zephyrus.core.spell.mobility;

import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;


/**
 * Zephyrus - Woosh.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class WooshSpell extends Spell {

	public WooshSpell() {
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
