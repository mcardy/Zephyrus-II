package com.minnymin.zephyrus.core.spell.restoration;


import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Feed.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class FeedSpell extends Spell {
	
	public FeedSpell() {
		super("feed", "Feeds you scraps of magic food", 25, 2, AspectList.newList(Aspect.FLESH, 30, Aspect.LIFE, 15,
				Aspect.BEAST, 15), 1, SpellElement.NEUTREAL, SpellType.RESTORATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		if (player.getFoodLevel() < 20) {
			player.setFoodLevel(player.getFoodLevel() + 1);
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.feed.full", player);
			return CastResult.FAILURE;
		}
	}

}
