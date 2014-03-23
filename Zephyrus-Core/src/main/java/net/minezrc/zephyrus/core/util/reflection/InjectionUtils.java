package net.minezrc.zephyrus.core.util.reflection;

import java.lang.reflect.Field;

import javax.annotation.Nullable;

import com.google.common.base.Function;

public class InjectionUtils {

	/**
	 * Retrieve a field accessor for a specific field type and index.
	 * 
	 * @param target - the target type.
	 * @param fieldType - the field type.
	 * @param index - the index.
	 * @return The field accessor.
	 */
	public static <T> Function<Object, T> getFieldAccessor(Class<?> target, Class<T> fieldType, int index) {
		for (Field field : target.getDeclaredFields()) {
			if (fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
				final Field targetField = field;
				field.setAccessible(true);
				return new Function<Object, T>() {
					@SuppressWarnings("unchecked")
					@Override
					public T apply(@Nullable Object instance) {
						try {
							return (T) targetField.get(instance);
						} catch (IllegalAccessException e) {
							throw new RuntimeException("Cannot access reflection.", e);
						}
					}
				};
			}
		}
		if (target.getSuperclass() != null)
			return getFieldAccessor(target.getSuperclass(), fieldType, index);
		throw new IllegalArgumentException("Cannot find field with type " + fieldType);
	}

	public static void setField(Class<?> target, Class<?> fieldType, int index, Object obj, Object value) {
		try {
			getField(target, fieldType, index).set(obj, value);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Retrieve a field for the specific field type and index.
	 * 
	 * @param target - the target type.
	 * @param fieldType - the field type.
	 * @param index - the index.
	 * @return The field
	 */
	public static Field getField(Class<?> target, Class<?> fieldType, int index) {
		for (Field field : target.getDeclaredFields()) {
			if (fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
				field.setAccessible(true);
				return field;
			}
		}
		if (target.getSuperclass() != null)
			return getField(target.getSuperclass(), fieldType, index);
		throw new IllegalArgumentException("Cannot find field with type " + fieldType);
	}

}
