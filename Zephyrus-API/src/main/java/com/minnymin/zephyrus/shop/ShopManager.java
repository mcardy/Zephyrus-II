package com.minnymin.zephyrus.shop;

import com.minnymin.zephyrus.Manager;

/**
 * Zephyrus - ShopManager.java <br>
 * Represents the manager for Sign Shops
 * 
 * @author minnymin3
 * 
 */

public interface ShopManager extends Manager {

	/**
	 * Adds a shop to the list of registered shops and automatically handle sign
	 * clicks
	 * 
	 * @param shop The shop to register
	 */
	public void registerShop(Shop shop);

}
