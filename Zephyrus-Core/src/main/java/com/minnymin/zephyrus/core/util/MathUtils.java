package com.minnymin.zephyrus.core.util;

/**
 * Zephyrus - MathUtils.java
 * 
 * @author minnymin3
 * 
 */

public class MathUtils {

	private static double[][] circlePos;
	
	static {
		circlePos = new double[45][2];
		for (int i = 1; i <= 45; i++) {
			circlePos[i - 1][0] = Math.cos(i * 8);
			circlePos[i - 1][1] = Math.sin(i * 8);
		}
	}
	
	public static double[][] getCircleMap() {
		return circlePos;
	}
	
}
