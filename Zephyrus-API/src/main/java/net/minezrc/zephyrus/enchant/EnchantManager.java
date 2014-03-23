package net.minezrc.zephyrus.enchant;

import net.minezrc.zephyrus.Manager;

/**
 * Zephyrus - EnchantManager.java
 * 
 * An interface used to handle all enchantment registering
 * 
 * @author minnymin3
 * 
 */

public interface EnchantManager extends Manager {

	public Enchant getEnchant(int id);
	public void registerEnchantment(Enchant enchantment);
	
}
