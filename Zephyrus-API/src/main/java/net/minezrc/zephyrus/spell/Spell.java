package net.minezrc.zephyrus.spell;

import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Spell.java
 * 
 * @author minnymin3
 * 
 */

public interface Spell {

	public String getName();
	public String getDescription();
	public int getManaCost();
	public int getXpReward();
	public SpellRecipe getRecipe();
	public int getRequiredLevel();

	public SpellElement getElement();
	public SpellType getType();
	public CastPriority getPriority();
	
	public void onDisable();
	public void onEnable();
	
	public CastResult onCast(User user, int power, Spell combo, String[] args);
	
}
