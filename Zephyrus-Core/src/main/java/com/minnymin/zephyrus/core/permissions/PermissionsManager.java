package com.minnymin.zephyrus.core.permissions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

/**
 * Zephyrus - PermissionsManager.java
 * 
 * @author minnymin3
 * 
 */

public class PermissionsManager {

	private static List<Permission> permissions = new ArrayList<Permission>();
	
	public static void addPermission(Permission perm) {
		permissions.add(perm);
	}
	
	public static List<Permission> getPermissions() {
		return permissions;
	}
	
	public static void registerPermissions() {
		addPermission(new Permission("zephyrus.command.spelltome", "Permission to use the '/spelltome' command", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.command.teach", "Permission to use the '/teach' command", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.command.aspects", "Permission to use the '/aspects' command", PermissionDefault.TRUE));
		addPermission(new Permission("zephyrus.command.bind", "Permission to use the '/bind' command", PermissionDefault.TRUE));
		addPermission(new Permission("zephyrus.command.book", "Permission to use the '/book' command", PermissionDefault.TRUE));
		addPermission(new Permission("zephyrus.command.cast", "Permission to use the '/cast' command", PermissionDefault.TRUE));
		addPermission(new Permission("zephyrus.command.level", "Permission to check level with '/level'", PermissionDefault.TRUE));
		addPermission(new Permission("zephyrus.command.level.add", "Permission to increase level with '/level add'", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.command.level.other", "Permission to check other player's level with '/level <player>'", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.command.mana", "Permission to check mana with '/mana'", PermissionDefault.TRUE));
		addPermission(new Permission("zephyrus.command.mana.restore", "Permission to restore mana with '/mana restore'", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.command.mana.other", "Permission to check other player's mana with '/mana <player>'", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.command.book.bypass", "Permission to ignore the book limit of '/book'", PermissionDefault.FALSE));
		addPermission(new Permission("zephyrus.shop.create", "Allows user to create shops", PermissionDefault.OP));
		addPermission(new Permission("zephyrus.shop.use", "Allows user to use shops", PermissionDefault.TRUE));
		for (Permission permission : permissions) {
			Bukkit.getPluginManager().addPermission(permission);
		}
	}

}
