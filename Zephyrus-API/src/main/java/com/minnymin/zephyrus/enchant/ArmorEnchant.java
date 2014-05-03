package com.minnymin.zephyrus.enchant;

import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Zephyrus - ArmorEnchant.java <br>
 * Represents an armor enchantment
 * 
 * @author minnymin3
 * 
 */

public interface ArmorEnchant extends Enchant {

	/**
	 * Called when an entity wearing armor with this enchant gets damaged
	 * 
	 * @param level The level of the enchantment
	 * @param event The event that was called
	 */
	public void onDamage(int level, EntityDamageEvent event);

}
