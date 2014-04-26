package net.minezrc.zephyrus.core.spell.restoration;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;

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
			Language.sendError("spell.feed.full", "You are already full!", player);
			return CastResult.FAILURE;
		}
	}

}
