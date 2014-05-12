package com.minnymin.zephyrus.user;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Target.java<br>
 * Represents a user's target
 * 
 * @author minnymin3
 * 
 */

public class Target {

	/**
	 * An identifier for the type of target for a targeted spell
	 */
	public enum TargetType {
		BLOCK, ENTITY, PLAYER;
	}

	private TargetType type;
	private Object value;

	public Target(Object value, TargetType type) {
		this.type = type;
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
	 * Gets the player target
	 * 
	 * @return Null if not targeting a player or no target was found
	 */
	public Player getPlayer() {
		if (value instanceof Player) {
			return (Player) value;
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
