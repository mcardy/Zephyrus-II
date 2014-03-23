package net.minezrc.zephyrus.core.util.nbt;

/**
 * Zephyrus - NBTTag.java
 *
 * @author minnymin3
 *
 */
public interface NBTTag {

	/**
	 * Gets the raw version of the NBT object
	 * 
	 * @return A net.minecraft.server.NBTBase child object
	 */
	public Object getRawTag();
	
}
