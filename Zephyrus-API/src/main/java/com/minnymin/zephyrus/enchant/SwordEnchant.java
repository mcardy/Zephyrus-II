package com.minnymin.zephyrus.enchant;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Zephyrus - SwordEnchant.java <br>
 * Represents an enchant that can be applied to a sword
 * 
 * @author minnymin3
 * 
 */

public interface SwordEnchant extends Enchant {

	/**
	 * 
	 * @param level
	 * @param event
	 */
	public void onDamage(int level, EntityDamageByEntityEvent event);

}
