package com.minnymin.zephyrus.core.util;

import java.util.HashSet;

/**
 * Zephyrus - BlockUtils.java
 * 
 * @author minnymin3
 * 
 */

public class BlockUtils {

	private static HashSet<Byte> transparent;
	
	public static HashSet<Byte> getTransparent() {
		if (transparent == null) {
			transparent = new HashSet<Byte>();
			transparent.add((byte) 0); // Air
			transparent.add((byte) 8); // Water
			transparent.add((byte) 9); // Stationary Water
			transparent.add((byte) 20); // Glass
			transparent.add((byte) 27); // Powered Rail
			transparent.add((byte) 28); // Detector Rail
			transparent.add((byte) 30); // Cobweb
			transparent.add((byte) 31); // Grass/Fern
			transparent.add((byte) 32); // Dead Bush
			transparent.add((byte) 37); // Dandelion
			transparent.add((byte) 38); // Flowers
			transparent.add((byte) 39); // Brown Mushroom
			transparent.add((byte) 40); // Red Mushroom
			transparent.add((byte) 65); // Ladder
			transparent.add((byte) 66); // Rail
			transparent.add((byte) 78); // Snow
			transparent.add((byte) 83); // Sugar Cane
			transparent.add((byte) 95); // Stained Glass
			transparent.add((byte) 101); // Iron Bars
			transparent.add((byte) 102); // Glass Pane
			transparent.add((byte) 106); // Vines
			transparent.add((byte) 160); // Stained Glass Pane
			transparent.add((byte) 171); // Carpet
			transparent.add((byte) 157); // Activator Rail
			transparent.add((byte) 175); // Tall Grass/Fern/Flowers
		}
		return transparent;
	}
	
}
