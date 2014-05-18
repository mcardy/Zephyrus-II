package com.minnymin.zephyrus.user.targeted;


/**
 * Zephyrus - Target.java<br>
 * Represents a user's target
 * 
 * @author minnymin3
 * @param <E>
 * 
 */

public class Target<E> {

	/**
	 * An identifier for the type of target for a targeted spell
	 */
	public enum TargetType {
		BLOCK, ENTITY, PLAYER;
	}

	private TargetType type;
	private E value;
	
	public Target(E value, TargetType type) {
		this.type = type;
		this.value = value;
	}

	/**
	 * Gets the target contained in this object
	 * 
	 * @return The target type
	 */
	public E getTarget() {
		return value;
	}

	/**
	 * Returns the type of target this object represents
	 * 
	 * @return A TargetType of this target
	 */
	public TargetType getType() {
		return type;
	}

}
