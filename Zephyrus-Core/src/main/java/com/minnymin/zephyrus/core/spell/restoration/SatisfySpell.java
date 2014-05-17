package com.minnymin.zephyrus.core.spell.restoration;

import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - SsatisfySpell.java
 * 
 * @author minnymin3
 * 
 */

public class SatisfySpell extends Spell {

	public SatisfySpell() {
		super("satisfy", "Your health and hunger will be satisfied", 300, 20, AspectList.newList(Aspect.BEAST, 60,
				Aspect.LIFE, 30, Aspect.FLESH, 30), 3, SpellElement.NEUTREAL, SpellType.RESTORATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		return CastResult.SUCCESS;
	}

}
