package net.minezrc.zephyrus.enchant;

import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Zephyrus - BowEnchant.java
 * 
 * @author minnymin3
 * 
 */

public interface BowEnchant extends Enchant {

	public void onBowShoot(int level, EntityShootBowEvent event);
	
}
