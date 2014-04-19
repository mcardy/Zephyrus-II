package net.minezrc.zephyrus.spell;

import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - ComboSpell.java<br>
 * Represents a spell that can be cast in conjunction with another spell
 * 
 * @author minnymin3
 * 
 */

public interface ComboSpell {

	/**
	 * The priority of the spell determining which spell is being cast and which
	 * spell will be the modifier
	 * 
	 * @return A CastPriority constant
	 */
	public CastPriority getPriority();

	/**
	 * The method called when the spell is cast with another spell
	 * 
	 * @param user The user who cast the spell
	 * @param power The power of the spell
	 * @param args The arguments cast with the spell, null if bound
	 * @param combo The spell cast in conjunction with this spell
	 * @return A CastResult representing the result of this cast operation
	 */
	public CastResult onComboCast(User user, int power, String[] args, Spell combo);

}
