package net.minezrc.zephyrus.aspect;

import java.util.ArrayList;
import java.util.List;

/**
 * Zephyrus - ReagentList.java
 * 
 * @author minnymin3
 * 
 */

public class AspectList {

	private List<Aspect> aspectTypes;
	private List<Integer> aspectAmounts;
	
	public static AspectList newList() {
		return new AspectList();
	}
	
	public static AspectList newList(Aspect aspect, int value) {
		return newList().setAspectTypes(aspect).setAspectValues(value);
	}
	
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
