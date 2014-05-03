package com.minnymin.zephyrus.user;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.minnymin.zephyrus.spell.SpellAttributes.TargetType;


/**
 * Zephyrus - Targeted.java<br>
 * Represents a targeted spell
 * 
 * @author minnymin3
 * 
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Targeted {

	/**
	 * Whether or not the spell is beneficial. Defaulted to false
	 * 
	 * @return A boolean
	 */
	public boolean friendly() default false;

	/**
	 * Determines the range of the target
	 * 
	 * @return An int
	 */
	public int range() default 10;

	/**
	 * The type of target (BLOCK or ENTITY)
	 * 
	 * @return TargetType enum constant
	 */
	public TargetType type();

}
