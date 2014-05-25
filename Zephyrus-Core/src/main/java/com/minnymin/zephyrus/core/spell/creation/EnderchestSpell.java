package com.minnymin.zephyrus.core.spell.creation;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Enderchest.java
 * 
 * @author minnymin3
 * 
 */

public class EnderchestSpell extends Spell {

	public EnderchestSpell() {
		super("enderchest", "Summons the chests of ender", 100, 5, AspectList
				.newList(Aspect.ENDER, 50, Aspect.VOID, 25), 3, SpellElement.ENDER, SpellType.CREATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().openInventory(user.getPlayer().getEnderChest());
		return CastResult.SUCCESS;
	}

}
