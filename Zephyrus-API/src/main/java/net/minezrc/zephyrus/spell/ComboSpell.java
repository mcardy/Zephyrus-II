package net.minezrc.zephyrus.spell;

import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - ComboSpell.java
 * 
 * @author minnymin3
 * 
 */

public interface ComboSpell {

	public CastPriority getPriority();
	public CastResult onComboCast(User user, int power, String[] args, Spell combo);

}
