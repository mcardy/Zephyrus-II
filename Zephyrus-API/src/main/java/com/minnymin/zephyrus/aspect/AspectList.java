package com.minnymin.zephyrus.aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Zephyrus - AspectList.java <br>
 * Represents a list of aspects and amounts of aspects
 * 
 * @author minnymin3
 * 
 */

public class AspectList {

	/**
	 * Creates a new AspectList object with given aspects and values
	 * 
	 * @return A new aspect list with the provided aspects
	 */
	public static AspectList newList() {
		return new AspectList();
	}
	/**
	 * Creates a new AspectList object with given aspects and values
	 * 
	 * @param values Aspects followed by values
	 * @return A new aspect list with the provided aspects
	 */
	public static AspectList newList(Object... values) {
		List<Aspect> aspects = new ArrayList<Aspect>();
		List<Integer> amounts = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i += 2) {
			if (values.length <= i + 1 || !(values[i] instanceof Aspect) || !(values[i + 1] instanceof Integer)) {
				continue;
			}
			aspects.add((Aspect) values[i]);
			amounts.add((Integer) values[i + 1]);
		}
		return new AspectList().setAspectLists(aspects, amounts);
	}

	private List<Integer> aspectAmounts;

	private List<Aspect> aspectTypes;

	/**
	 * Gets the value list
	 */
	public List<Integer> getAmountList() {
		return aspectAmounts;
	}

	/**
	 * Gets the aspect map
	 */
	public Map<Aspect, Integer> getAspectMap() {
		Map<Aspect, Integer> map = new HashMap<Aspect, Integer>();
		for (int i = 0; i < aspectTypes.size(); i++) {
			if (i < aspectAmounts.size()) {
				map.put(aspectTypes.get(i), aspectAmounts.get(i));
			}
		}
		return map;
	}

	/**
	 * Gets the aspect list
	 */
	public List<Aspect> getTypeList() {
		return aspectTypes;
	}

	/**
	 * Sets the lists that this AspectList should contain
	 * 
	 * @param types The list of aspects
	 * @param values The list of values
	 */
	public AspectList setAspectLists(List<Aspect> types, List<Integer> values) {
		this.aspectTypes = types;
		this.aspectAmounts = values;
		return this;
	}

	/**
	 * Sets the aspect types in the same order as the aspect values
	 * 
	 * @param aspects The aspects this list should contain
	 */
	public AspectList setAspectTypes(Aspect... aspects) {
		if (aspectTypes != null) {
			throw new IllegalArgumentException("Aspect types already set in aspect list!");
		}
		aspectTypes = new ArrayList<Aspect>();
		for (Aspect aspect : aspects) {
			aspectTypes.add(aspect);
		}
		return this;
	}

	/**
	 * Sets the aspect values in the same order as the aspect types <br>
	 * Ex: setAspectTypes(FIRE, WATER).setAspectValues(4, 1) would have 4 fire
	 * and 1 water
	 * 
	 * @param aspects The aspect values this list should contain
	 */
	public AspectList setAspectValues(int... aspects) {
		if (aspectAmounts != null) {
			throw new IllegalArgumentException("Aspect amounts already set in aspect list!");
		}
		aspectAmounts = new ArrayList<Integer>();
		for (int aspect : aspects) {
			aspectAmounts.add(aspect);
		}
		return this;
	}

}
