package net.minezrc.zephyrus.spell;

/**
 * Zephyrus - SpellRequirements.java
 * 
 * @author minnymin3
 *  
 */

public @interface Prerequisite {

	public Class<? extends Spell> requiredSpell();
	
}
