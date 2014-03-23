package net.minezrc.zephyrus.enchant;

import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Zephyrus - ArmorEnchant.java
 * 
 * @author minnymin3
 * 
 */

public interface ArmorEnchant {

	public void onDamage(int level, EntityDamageEvent event);
	
}
