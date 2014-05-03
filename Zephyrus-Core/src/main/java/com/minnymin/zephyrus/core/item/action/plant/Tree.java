package com.minnymin.zephyrus.core.item.action.plant;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;

/**
 * Zephyrus
 * 
 * @author minnymin3
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */

public class Tree extends Plant {

	public Tree() {
		super(Material.SAPLING);
	}

	@Override
	public boolean grow(Block block) {
		if (block.getType() != Material.SAPLING && block.getType() != Material.BROWN_MUSHROOM
				&& block.getType() != Material.RED_MUSHROOM)
			return false;
		TreeType type = getTree(block);
		Material prevMat = block.getType();
		block.setType(Material.AIR);
		if (block.getWorld().generateTree(block.getLocation(), type)) {
			return true;
		}
		block.setType(prevMat);
		return false;
	}

	@SuppressWarnings("deprecation")
	private TreeType getTree(Block block) {
		if (block.getType() == Material.SAPLING) {
			switch (block.getData()) {
			case 0:
				return TreeType.TREE;
			case 1:
				return TreeType.REDWOOD;
			case 2:
				return TreeType.BIRCH;
			case 3:
				return TreeType.SMALL_JUNGLE;
			case 4:
				return TreeType.ACACIA;
			case 5:
				return TreeType.DARK_OAK;
			}
			return TreeType.TREE;
		} else {
			if (block.getType() == Material.BROWN_MUSHROOM) {
				return TreeType.BROWN_MUSHROOM;
			} else {
				return TreeType.RED_MUSHROOM;
			}
		}
	}

}
