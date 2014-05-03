package com.minnymin.zephyrus.spell.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Zephyrus - Bindable.java<br>
 * An annotation to represent a spell that is bindable
 * 
 * @author minnymin3
 * 
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bindable {

}
