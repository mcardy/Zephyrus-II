package net.minezrc.zephyrus.spell;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
	
}
