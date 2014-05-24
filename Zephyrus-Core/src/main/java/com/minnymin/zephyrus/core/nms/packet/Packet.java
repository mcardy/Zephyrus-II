package com.minnymin.zephyrus.core.nms.packet;

import java.lang.reflect.Field;

import com.minnymin.zephyrus.core.util.reflection.InjectionUtils;


/**
 * Zephyrus - Packet.java
 *
 * @author minnymin3
 *
 */
public abstract class Packet {

	protected Object packet;

	/**
	 * Sets the value of the field in the packet object
	 */
	public void setValue(Class<?> fieldType, int index, Object value) {
		InjectionUtils.setField(packet.getClass(), fieldType, index, packet, value);
	}

	/**
	 * Sets the value of the field in the packet object <br>
	 * Use setValue(Class<T> fieldType, int index, T value) to avoid problems
	 * 
	 * @param value The value to set
	 * @param index The index of the value
	 * @deprecated Used as a work around for objects who's classes may not be
	 *             apparent. May cause unexpected results. Use with caution.
	 */
	@Deprecated
	public void setValue(Object value, int index) {
		Class<?> superClass = value.getClass();
		while (superClass != null) {
			try {
				Field f = InjectionUtils.getField(packet.getClass(), superClass, index);
				if (f != null) {
					try {
						f.set(packet, value);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
			} catch (IllegalArgumentException ex) {
				superClass = superClass.getSuperclass();
			}
		}
	}

	/**
	 * Gets the value of the field in the packet object
	 */
	public <T> T getValue(Class<T> fieldType, int index) {
		return InjectionUtils.getFieldAccessor(packet.getClass(), fieldType, index).apply(packet);
	}

	/**
	 * Gets the packet object
	 */
	public Object getPacket() {
		return packet;
	}

}
