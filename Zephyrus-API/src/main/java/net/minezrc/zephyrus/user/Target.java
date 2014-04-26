package net.minezrc.zephyrus.user;

import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

/**
 * Zephyrus - Target.java<br>
 * Represents a user's target
 * 
 * @author minnymin3
 * 
 */

public class Target {

	private TargetType type;
	private Object value;

	public Target(Object value) {
		if (value instanceof LivingEntity) {
			this.type = TargetType.ENTITY;
		} else if (value instanceof Block) {
			this.type = TargetType.BLOCK;
		}
		this.value = value;
	}

	/**
	 * Gets the block target
	 * 
	 * @return Null if not targeting a block
	 */
	public Block getBlock() {
		if (value instanceof Block) {
			return (Block) value;
		}
		return null;
	}

	/**
	 * Gets the entity target
	 * 
	 * @return Null if not targeting an entity or no target was found
	 */
	public LivingEntity getEntity() {
		if (value instanceof LivingEntity) {
			return (LivingEntity) value;
		}
		return null;
	}

	/**
	 * Gets the type of target
	 * 
	 * @return A TargetType target
	 */
	public TargetType getType() {
		return type;
	}

}
