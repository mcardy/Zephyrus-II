package net.minezrc.zephyrus.spell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Zephyrus - SpellRequirements.java
 * 
 * @author minnymin3
 *  
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Prerequisite {

	public Class<? extends Spell> requiredSpell();
	
}
