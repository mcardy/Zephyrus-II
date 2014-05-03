package com.minnymin.zephyrus.core.util.nbt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;


import org.bukkit.Bukkit;

import com.minnymin.zephyrus.core.util.reflection.NMSUtils;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;

/**
 * Zephyrus - NBTCompound.java
 * 
 * @author minnymin3
 *
 */
public class NBTCompound implements NBTTag {

	private Object tag;

	/**
	 * Creates a new blank NBT compound
	 */
	public NBTCompound() {
		this.tag = NMSUtils.getNMSObject("NBTTagCompound");
	}

	/**
	 * Loads an NBT compound form an NMS NBTTagCompound object
	 * 
	 * @param tag A net.minecraft.server.NBTTagCompound object
	 */
	public NBTCompound(Object tag) {
		if (tag.getClass().isAssignableFrom(NMSUtils.getNMSClass("NBTTagCompound"))) {
			this.tag = tag;
		} else {
			this.tag = NMSUtils.getNMSObject("NBTTagCompound");
		}
	}

	/**
	 * Gets a byte from the compound
	 * 
	 * @param path The path of the byte
	 * @return The byte located at the given path or 0 if there is no such path
	 */
	public byte getByte(String path) {
		return (Byte) ReflectionUtils.invokeMethod(tag, "getByte", path);
	}

	/**
	 * Gets a short from the compound
	 * 
	 * @param path The path of the short
	 * @return The short located at the given path or 0 if there is no such path
	 */
	public short getShort(String path) {
		return (Short) ReflectionUtils.invokeMethod(tag, "getShort", path);
	}

	/**
	 * Gets a int from the compound
	 * 
	 * @param path The path of the int
	 * @return The int located at the given path or 0 if there is no such path
	 */
	public int getInt(String path) {
		return (Integer) ReflectionUtils.invokeMethod(tag, "getInt", path);
	}

	/**
	 * Gets a long from the compound
	 * 
	 * @param path The path of the long
	 * @return The long located at the given path or 0 if there is no such path
	 */
	public long getLong(String path) {
		return (Long) ReflectionUtils.invokeMethod(tag, "getLong", path);
	}

	/**
	 * Gets a float from the compound
	 * 
	 * @param path The path of the float
	 * @return The float located at the given path or 0 if there is no such path
	 */
	public float getFloat(String path) {
		return (Float) ReflectionUtils.invokeMethod(tag, "getFloat", path);
	}

	/**
	 * Gets a double from the compound
	 * 
	 * @param path The path of the double
	 * @return The double located at the given path or 0 if there is no such
	 *         path
	 */
	public double getDouble(String path) {
		return (Double) ReflectionUtils.invokeMethod(tag, "getDouble", path);
	}

	/**
	 * Gets a byte array from the compound
	 * 
	 * @param path The path of the byte array
	 * @return The byte array located at the given path or an empty array if
	 *         there is no such path
	 */
	public byte[] getByteArray(String path) {
		return (byte[]) ReflectionUtils.invokeMethod(tag, "getByteArray", path);
	}

	/**
	 * Gets a int array from the compound
	 * 
	 * @param path The path of the int array
	 * @return The int array located at the given path or an empty array if
	 *         there is no such path
	 */
	public int[] getIntArray(String path) {
		return (int[]) ReflectionUtils.invokeMethod(tag, "getIntArray", path);
	}

	/**
	 * Gets a String from the compound
	 * 
	 * @param path The path of the String
	 * @return The String located at the given path or null if there is no such
	 *         path
	 */
	public String getString(String path) {
		return (String) ReflectionUtils.invokeMethod(tag, "getString", path);
	}

	/**
	 * Gets a boolean from the compound
	 * 
	 * @param path The path of the boolean
	 * @return The boolean located at the given path or false if there is no
	 *         such path
	 */
	public boolean getBoolean(String path) {
		return (Boolean) ReflectionUtils.invokeMethod(tag, "getBoolean", path);
	}

	/**
	 * Gets a list from the compound
	 * 
	 * @param path The path of the list
	 * @return The NBTList located at the given path or a blank list if there is
	 *         no such path
	 */
	public NBTList getList(String path) {
		return new NBTList(ReflectionUtils.invokeMethod(tag, "get", path));
	}

	/**
	 * Gets a compound form the compound
	 * 
	 * @param path The path of the compound
	 * @return The NBTCompound located at the given path or a blank compound if
	 *         there is no such path
	 */
	public NBTCompound getCompound(String path) {
		return new NBTCompound(ReflectionUtils.invokeMethod(tag, "getCompound", path));
	}

	/**
	 * Checks if the path exists
	 * @param path
	 * @return
	 */
	public boolean contains(String path) {
		return (Boolean) ReflectionUtils.invokeMethod(tag, "hasKey", path);
	}
	
	/**
	 * Remove the object at the given path
	 * 
	 * @param path The path to remove
	 */
	public void remove(String path) {
		ReflectionUtils.invokeMethod(tag, "remove", path);
	}

	/**
	 * Sets the byte at the given path
	 * 
	 * @param path The path to set
	 * @param value The byte to set it to
	 */
	public void setByte(String path, byte value) {
		ReflectionUtils.invokeMethod(tag, "setByte", path, value);
	}

	/**
	 * Sets the short at the given path
	 * 
	 * @param path The path to set
	 * @param value The short to set it to
	 */
	public void setShort(String path, short value) {
		ReflectionUtils.invokeMethod(tag, "setShort", path, value);
	}

	/**
	 * Sets the int at the given path
	 * 
	 * @param path The path to set
	 * @param value The int to set it to
	 */
	public void setInt(String path, int value) {
		ReflectionUtils.invokeMethod(tag, "setInt", path, value);
	}

	/**
	 * Sets the long at the given path
	 * 
	 * @param path The path to set
	 * @param value The long to set it to
	 */
	public void setLong(String path, long value) {
		ReflectionUtils.invokeMethod(tag, "setLong", path, value);
	}

	/**
	 * Sets the float at the given path
	 * 
	 * @param path The path to set
	 * @param value The float to set it to
	 */
	public void setFloat(String path, float value) {
		ReflectionUtils.invokeMethod(tag, "setFloat", path, value);
	}

	/**
	 * Sets the double at the given path
	 * 
	 * @param path The path to set
	 * @param value The double to set it to
	 */
	public void setDouble(String path, double value) {
		ReflectionUtils.invokeMethod(tag, "setDouble", path, value);
	}

	/**
	 * Sets the byte array at the given path
	 * 
	 * @param path The path to set
	 * @param value The byte array to set it to
	 */
	public void setByteArray(String path, byte[] value) {
		ReflectionUtils.invokeMethod(tag, "setByteArray", path, value);
	}

	/**
	 * Sets the int array at the given path
	 * 
	 * @param path The path to set
	 * @param value The int array to set it to
	 */
	public void setIntArary(String path, int[] value) {
		ReflectionUtils.invokeMethod(tag, "setIntArray", path, value);
	}

	/**
	 * Sets the String at the given path
	 * 
	 * @param path The path to set
	 * @param value The String to set it to
	 */
	public void setString(String path, String value) {
		ReflectionUtils.invokeMethod(tag, "setString", path, value);
	}

	/**
	 * Sets the boolean at the given path
	 * 
	 * @param path The path to set
	 * @param value The boolean to set it to
	 */
	public void setBoolean(String path, boolean value) {
		ReflectionUtils.invokeMethod(tag, "setBoolean", path, value);
	}

	/**
	 * Sets the list at the given path
	 * 
	 * @param path The path to set
	 * @param value The list to set it to
	 */
	public void setList(String path, NBTList value) {
		ReflectionUtils.invokeMethod(tag, "set", path, value.getRawTag());
	}

	/**
	 * Sets the compound at the given path
	 * 
	 * @param path The path to set
	 * @param value The compound to set it to
	 */
	public void setCompound(String path, NBTCompound value) {
		ReflectionUtils.invokeMethod(tag, "set", path, value.getRawTag());
	}
	
	/**
	 * Sets the NBTParsable compound at the given path
	 * 
	 * @param path The path to set
	 * @param value The NBTParsable object to set it to
	 */
	public void setObject(String path, NBTParsable value) {
		ReflectionUtils.invokeMethod(tag, "set", path, value.toNBT().getRawTag());
	}
	
	/**
	 * Sets the NBTTag at the given path
	 * 
	 * @param path The path to set
	 * @param value The NBTTag object to set it to
	 */
	public void set(String path, NBTTag value) {
		ReflectionUtils.invokeMethod(tag, "set", path, value.getRawTag());
	}

	@Override
	public Object getRawTag() {
		return tag;
	}
	
	/**
	 * Gets the raw version of the NBTCompound's values
	 * 
	 * @return A Map object
	 */
	@SuppressWarnings("rawtypes")
	public Map getRawMap() {
		return (Map) ReflectionUtils.getField(tag, "map");
	}

	/**
	 * Loads the compound form the given file
	 * 
	 * @param file The file to load from
	 */
	public void load(File file) {
		try {
			InputStream in = new FileInputStream(file);
			tag = ReflectionUtils.invokeStaticMethod(NMSUtils.getNMSClass("NBTCompressedStreamTools"), "a",
					new Class<?>[] { InputStream.class }, in);
		} catch (Exception ex) {
			Bukkit.getLogger()
					.warning("Failed to load compound form file '" + file.getName() + "'. " + ex.getMessage());
		}
	}

	/**
	 * Saves the compound to the given file
	 * 
	 * @param file The file to save to
	 */
	public void save(File file) {
		try {
			if (!file.exists())
				file.createNewFile();
			OutputStream out = new FileOutputStream(file);
			ReflectionUtils.invokeStaticMethod(NMSUtils.getNMSClass("NBTCompressedStreamTools"), "a", new Class<?>[] {
					tag.getClass(), OutputStream.class }, tag, out);
		} catch (Exception ex) {
			Bukkit.getLogger().warning("Failed to save compound to file '" + file.getName() + "'. " + ex.getMessage());
		}
	}
}
