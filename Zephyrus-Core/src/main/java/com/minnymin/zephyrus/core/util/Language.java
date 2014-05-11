package com.minnymin.zephyrus.core.util;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.minnymin.zephyrus.YmlConfigFile;

/**
 * Zephyrus - Language.java
 * 
 * @author minnymin3
 * TODO Remove default text params and add strings with add()
 * 
 */

public class Language {

	private static YmlConfigFile config;

	/**
	 * Adds a section to the language configurations if it does not exist.
	 * Setting description to null will remove it.
	 * 
	 * @param key The key to set
	 * @param desc The text to add
	 */
	public static void add(String key, String desc) {
		if (config == null)
			config = new YmlConfigFile("locale.yml");
		config.addDefaults(key, desc.replace(ChatColor.COLOR_CHAR + "", "$"));
	}
	

	/**
	 * Gets the specified section from the language configurations and
	 * translates all color codes with default and replacement
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param replace The group of replacements (what to replace followed by
	 *            replacement)
	 */
	public static String get(String key, String def, String... replace) {
		if (config == null)
			config = new YmlConfigFile("locale.yml");
		add(key, def);
		String msg = ChatColor.translateAlternateColorCodes('$', config.getConfig().getString(key));
		int i = 0;
		while (i < replace.length) {
			String from = replace[i];
			i++;
			String to = replace[i];
			i++;
			if (from != null && to != null) {
				msg = msg.replace(from, to);
			}
		}
		return msg;
	}

	/**
	 * Sends an error message to the specified CommandSender with default text
	 * and replacements
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param sender The CommandSender to send the message to
	 * @param replace The group of replacements (what to replace followed by
	 *            replacement)
	 */
	public static void sendError(String key, String def, CommandSender sender, String... replace) {
		sender.sendMessage(ChatColor.DARK_RED + get(key, def, replace));
	}

	/**
	 * Sends a message to the specified CommandSender with default text and
	 * replacements
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param sender The CommandSender to send the message to
	 * @param replace The group of replacements (what to replace followed by
	 *            replacement)
	 */
	public static void sendMessage(String key, String def, CommandSender sender, String... replace) {
		sender.sendMessage(get(key, def, replace));
	}

}
