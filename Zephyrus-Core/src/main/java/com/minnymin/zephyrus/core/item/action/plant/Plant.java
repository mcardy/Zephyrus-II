package com.minnymin.zephyrus.core.item.action.plant;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Zephyrus
 * 
 * @author minnymin3
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */

public class Plant {

	private Material mat;
	
	public Plant(Material mat) {
		this.mat = mat;
	}
	
	/**
	 * Grows the plant to its full grown state
	 * @param block The block to grow
	 * @return True only if the block was grown successfully
	 */
	@SuppressWarnings("deprecation")
	public boolean grow(Block block) {
		if (block.getType() != this.mat || block.getData() == CropState.RIPE.getData()) {
			return false;
		}
		block.setData(CropState.RIPE.getData());
		return true;
	}
	
	
}
