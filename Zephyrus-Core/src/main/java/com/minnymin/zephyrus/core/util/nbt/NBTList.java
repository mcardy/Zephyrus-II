package com.minnymin.zephyrus.core.util.nbt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.minnymin.zephyrus.core.util.reflection.NMSUtils;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;


/**
 * Zephyrus - NBTList.java
 *
 * @author minnymin3
 *
 */
public class NBTList implements NBTTag, Iterable<NBTCompound> {

	private Object list;

	/**
	 * Creates a new blank NBT List
	 */
	public NBTList() {
		this.list = NMSUtils.getNMSObject("NBTTagList");
	}

	/**
	 * Loads an NBT list form an NMS NBTTagList object
	 * 
	 * @param list A net.minecraft.server.NBTTagList object
	 */
	public NBTList(Object list) {
		if (list.getClass().equals(NMSUtils.getNMSClass("NBTTagList"))) {
			this.list = list;
		} else {
			this.list = NMSUtils.getNMSObject("NBTTagList");
		}
	}

	/**
	 * Adds the NBTCompound to the list
	 * 
	 * @param compound The compound to add
	 */
	public void add(NBTCompound compound) {
		ReflectionUtils.invokeMethod(list, "add", compound.getRawTag());
	}

	/**
	 * Gets the NBTCompound at the given index
	 * 
	 * @param index The index
	 * @return A new NBTCompound if there is no such index
	 */
	public NBTCompound get(int index) {
		return new NBTCompound(ReflectionUtils.invokeMethod(list, "get", index));
	}

	/**
	 * Removes the NBTCompound at the given index
	 * 
	 * @param index The index
	 */
	public void remove(int index) {

	}

	/**
	 * Gets the list of raw NBTTagCompounds
	 */
	public List<?> getRawList() {
		return (List<?>) ReflectionUtils.getField(list, "list");
	}

	/**
	 * Creates a list of NBTCompounds from the raw list
	 * 
	 * @return A blank array list if the raw list isn't NBTTagCompounds
	 */
	public List<NBTCompound> getList() {
		List<NBTCompound> list = new ArrayList<NBTCompound>();
		for (Object obj : getRawList()) {
			if (obj.getClass().equals(NMSUtils.getNMSClass("NBTTagCompound"))) {
				list.add(new NBTCompound(obj));
			}
		}
		return list;
	}

	/**
	 * Gets the size of the list
	 * 
	 * @return The size of the raw list
	 */
	public int getSize() {
		return getRawList().size();
	}

	@Override
	public Object getRawTag() {
		return list;
	}

	@Override
	public Iterator<NBTCompound> iterator() {
		return new NBTListIterator(this);
	}

}
