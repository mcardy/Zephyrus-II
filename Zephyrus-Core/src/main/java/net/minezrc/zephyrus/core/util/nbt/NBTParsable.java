package net.minezrc.zephyrus.core.util.nbt;

/**
 * Zephyrus - NBTParsable.java
 *
 * @author minnymin3
 *
 */
public interface NBTParsable {

	/**
	 * Serializes the object to NBT
	 * 
	 * @return An NBTCompound with the objects data
	 */
	public NBTCompound toNBT();
	
	/**
	 * Loads the object from NBT
	 * 
	 * @param compound The compound that the object would have been written from
	 */
	public void fromNBT(NBTCompound compound);
	
}
