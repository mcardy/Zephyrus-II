package com.minnymin.zephyrus.core.permissions;

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

	public static void registerPermissions() {
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.spelltome", PermissionDefault.OP));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.teach", PermissionDefault.OP));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.aspects", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.bind", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.book", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.cast", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.level", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.level.add", PermissionDefault.OP));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.level.other", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.mana", PermissionDefault.TRUE));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.mana.restore", PermissionDefault.OP));
		Bukkit.getPluginManager().addPermission(new Permission("zephyrus.command.mana.other", PermissionDefault.TRUE));
	}
	
}
