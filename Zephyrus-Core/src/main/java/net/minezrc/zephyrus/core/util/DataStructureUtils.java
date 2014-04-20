package net.minezrc.zephyrus.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Zephyrus - DataStructureUtils.java
 * 
 * @author minnymin3
 * 
 */

public class DataStructureUtils {

	public static <A> Set<A> createSet(A... values) {
		Set<A> set = new HashSet<A>();
		for (A value : values) {
			set.add(value);
		}
		return set;
	}
	
	public static <A> List<A> createList(A... values) {
		List<A> list = new ArrayList<A>();
		for (A value : values) {
			list.add(value);
		}
		return list;
	}
	
	public static <A, B> Map<A, B> createMap(List<A> keys, List<B> values) {
		Map<A, B> map = new HashMap<A, B>();
		for (int i = 0; i < keys.size(); i++) {
			if (i == values.size()) {
				break;
			}
			map.put(keys.get(i), values.get(i));
		}
		return map;
	}
	
	public static Map<String, Object> createConfigurationMap() {
		return new HashMap<String, Object>();
	}
	
}
