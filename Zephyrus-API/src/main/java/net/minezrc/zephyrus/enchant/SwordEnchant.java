package net.minezrc.zephyrus.enchant;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Zephyrus - SwordEnchant.java
 * 
 * @author minnymin3
 * 
 */

public interface SwordEnchant extends Enchant {

	public void onDamage(int level, EntityDamageByEntityEvent event);
	
}
