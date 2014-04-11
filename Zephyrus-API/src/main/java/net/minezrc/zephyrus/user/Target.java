package net.minezrc.zephyrus.user;

import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

/**
 * Zephyrus - Target.java
 * 
 * @author minnymin3
 * @param <T>
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
	
	public TargetType getType() {
		return type;
	}
	
	public Block getBlock() {
		if (value instanceof Block) {
			return (Block) value;
		}
		return null;
	}
	
	public LivingEntity getEntity() {
		if (value instanceof LivingEntity) {
			return (LivingEntity) value;
		}
		return null;
	}

}
