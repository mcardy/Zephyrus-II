package net.minezrc.zephyrus.core.util.reflection;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class NMSUtils {

	private static String version = getVersion();
	
	/**
	 * Gets the NMS version of a Bukkit itemstack
	 */
	public static Object getNMSStack(ItemStack stack) {
		try {
			Class<?> clazz = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
			Method m = ReflectionUtils.getMethod(clazz, "asNMSCopy", new Class<?>[] { ItemStack.class });
			return m.invoke(null, stack);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets an object from net.minecraft.server package
	 * @param name The name of the class
	 * @return A new instance of the class
	 */
	public static Object getNMSObject(String name) {
		try {
			return getNMSClass(name).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a class from the net.minecraft.server package
	 * @param name The name of the class
	 * @return The class of the given name
	 */
	public static Class<?> getNMSClass(String name) {
		String className = "net.minecraft.server." + version  + "." + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	/**
	 * Gets a class form the org.bukkit.craftbukkit package
	 * @param name The name of the class
	 * @return The class of the given name
	 */
	public static Class<?> getOBCClass(String name) {
		String classname = "org.bukkit.craftbukkit." + version + "." + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}

	/**
	 * Gets the NMS version of a Bukkit entity
	 */
	public static Object getHandle(Object obj) {
		Object handle = null;
		Method getHandle = ReflectionUtils.getMethod(obj.getClass(), "getHandle");
		try {
			handle = getHandle.invoke(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return handle;
	}

	/**
	 * Gets the volatile version (NMS package)
	 * @return Something similar to v1_7_R1
	 */
	public static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
	}

}
