package net.minezrc.zephyrus.spell;

/**
 * Zephyrus - SpellAttributes.java<br>
 * Represents attributes related to the spell
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
		 * A cast success
		 */
		SUCCESS,
		/**
		 * A cast failure
		 */
		FAILURE,
	}

	/**
	 * An identifier for the element of a spell
	 */
	public enum SpellElement {
		AIR, ARCANE, DARKNESS, EARTH, ENDER, FIRE, LIGHT, NEUTREAL, WATER;
	}

	/**
	 * An identifier for the effect of a spell
	 */
	public enum SpellType {
		ATTACK, BUFF, CREATION, ILLUSION, MOBILITY, RESTORATION, WORLD;
	}

	/**
	 * An identifier for the type of target for a targeted spell
	 */
	public enum TargetType {
		ENTITY, BLOCK;
	}

}
