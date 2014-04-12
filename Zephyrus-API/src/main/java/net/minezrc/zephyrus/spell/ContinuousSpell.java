package net.minezrc.zephyrus.spell;

import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - ContinuousSpell.java
 * 
 * @author minnymin3
 * 
 */

public abstract class ContinuousSpell extends Spell {

	private int manaCostTick;
	
	public ContinuousSpell(String name, String description, int manaCost, int xpReward, AspectList recipe,
			int requiredLevel, SpellElement element, SpellType type, int manaCostTick) {
		super(name, description, manaCost, xpReward, recipe, requiredLevel, element, type);
		this.manaCostTick = manaCostTick;
	}
	
	public int getManaCostPerTick() {
		return manaCostTick;
	}
	
	public abstract CastResult onCastTick(User user, int power, String[] args);
	
}
