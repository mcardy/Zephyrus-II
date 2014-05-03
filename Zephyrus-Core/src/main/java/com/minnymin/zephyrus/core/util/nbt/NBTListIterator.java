package com.minnymin.zephyrus.core.util.nbt;

import java.util.Iterator;

/**
 * Zephyrus - NBTListIterator.java
 *
 * @author minnymin3
 *
 */
public class NBTListIterator implements Iterator<NBTCompound> {
	
	private int index = 0;
	private int lastIndex = 0;
	private NBTCompound current = null;
	private NBTList list = null;
	
	public NBTListIterator(NBTList list) {
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		return this.index < this.list.getSize();
	}

	@Override
	public NBTCompound next() {
		this.current = this.list.get(index);
		this.lastIndex = index;
		index++;
		return this.current;
	}

	@Override
	public void remove() {
		this.list.remove(lastIndex);
	}
	
}
