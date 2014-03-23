package net.minezrc.zephyrus.spell;

/**
 * Zephyrus - SpellAttributes.java
 * 
 * @author minnymin3
 * 
 */

public class SpellAttributes {

	/**
	 * The cast priority for combo spells
	 */
	public enum CastPriority {
		/**
		 * High priority, cast first
		 */
		HIGH,
		/**
		 * Medium priority, cast before low but after high
		 */
		MEDIUM,
		/**
		 * Low priority, cast last
		 */
		LOW;
	}

	/**
	 * The result of the cast method
	 */
	public enum CastResult {
		/**
		 * A normal cast success
		 */
		NORMAL_SUCCESS,
		/**
		 * A normal cast failure
		 */
		NORMAL_FAIL,
		/**
		 * A combo cast success which cancels the other spell's cast
		 */
		COMBO_SUCCESS,
		/**
		 * A combo cast failure which will allow the other spell to cast
		 */
		COMBO_FAILURE;
	}

	/**
	 * An identifier for the element of a spell
	 */
	public enum SpellElement {
		AIR, ARCANE, DARKNESS, EARTH, ENDER, FIRE, LIGHT, PASSIVE, WATER;
	}

	/**
	 * An identifier for the effect of a spell
	 */
	public enum SpellType {
		ATTACK, BUFF, CREATION, ILLUSION, MOBILITY, RESTORATION, WORLD;
	}

}
