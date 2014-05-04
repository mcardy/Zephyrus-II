package com.minnymin.zephyrus.core.item.action.plant;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Zephyrus
 * 
 * @author minnymin3
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */

public class PlantRegistry {

	private static Set<Plant> plantSet = new HashSet<Plant>();
	private static boolean init;

	private static void init() {
		init = true;
		add(new Plant(Material.MELON_STEM));
		add(new Plant(Material.CROPS));
		add(new Plant(Material.PUMPKIN_STEM));
		add(new Plant(Material.CARROT));
		add(new Plant(Material.POTATO));
		add(new Tree());
		add(new Plant(Material.DIRT) {
			@Override
			public boolean grow(Block block) {
				if (block.getType() == Material.DIRT) {
					block.setType(Material.GRASS);
					return true;
				}
				return false;
			}
		});
		add(new Plant(Material.SUGAR_CANE_BLOCK) {
			@Override
			public boolean grow(Block block) {
				if (block.getType() == Material.SUGAR_CANE_BLOCK) {
					Block block1 = block.getWorld().getBlockAt(block.getX(), block.getY() + 1, block.getZ());
					Block block2 = block.getWorld().getBlockAt(block.getX(), block.getY() + 1, block.getZ());
					if (block1.getType() == Material.AIR && block2.getType() == Material.AIR) {
						block1.setType(Material.SUGAR_CANE_BLOCK);
						block2.setType(Material.SUGAR_CANE_BLOCK);
						return true;
					}
				}
				return false;
			}
		});
	}

	public static void add(Plant plant) {
		plantSet.add(plant);
	}

	public static boolean grow(Block block) {
		if (!init) {
			init();
		}
		for (Plant plant : plantSet) {
			if (plant.grow(block)) {
				return true;
			}
		}
		return false;
	}

}
