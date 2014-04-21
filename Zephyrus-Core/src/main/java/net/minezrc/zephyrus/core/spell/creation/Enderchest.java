package net.minezrc.zephyrus.core.spell.creation;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Enderchest.java
 * 
 * @author minnymin3
 * 
 */

public class Enderchest extends Spell {

	public Enderchest() {
		super("enderchest", "Summons the chests of ender", 100, 5, AspectList
				.newList(Aspect.ENDER, 32, Aspect.VOID, 32), 2, SpellElement.ENDER, SpellType.CREATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().openInventory(user.getPlayer().getEnderChest());
		return CastResult.SUCCESS;
	}

}
