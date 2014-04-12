package net.minezrc.zephyrus.spell.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;

/**
 * Zephyrus - Targeted.java
 * 
 * @author minnymin3
 * 
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Targeted {

	public boolean friendly();
	public TargetType type();
	
}
