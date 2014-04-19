package net.minezrc.zephyrus.enchant;

import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Zephyrus - BowEnchant.java <br>
 * Represents a bow enchantment
 * 
 * @author minnymin3
 * 
 */

public interface BowEnchant extends Enchant {

	/**
	 * Called when a bow that has this enchant is shot
	 * 
	 * @param level The level of the enchant
	 * @param event The event that was called
	 */
	public void onBowShoot(int level, EntityShootBowEvent event);

}
