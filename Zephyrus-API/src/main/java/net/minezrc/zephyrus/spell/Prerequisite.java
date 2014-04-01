package net.minezrc.zephyrus.spell;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Zephyrus - SpellRequirements.java
 * 
 * @author minnymin3
 *  
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Prerequisite {

	public Class<? extends Spell> requiredSpell();
	
}
