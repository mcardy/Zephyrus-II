package net.minezrc.zephyrus.core.util;

import net.minezrc.zephyrus.YmlConfigFile;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Zephyrus - Language.java
 * 
 * @author minnymin3
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
		config.saveConfig();
	}

	/**
	 * Gets the specified section from the language configurations and
	 * translates all color codes
	 * 
	 * @param key The object's key
	 * @return The String found at that location
	 */
	public static String get(String key) {
		if (config == null)
			config = new YmlConfigFile("locale.yml");
		return ChatColor.translateAlternateColorCodes('$', config.getConfig().getString(key));
	}

	/**
	 * Gets the specified section from the language configurations and
	 * translates all color codes
	 * 
	 * @param key The object's key
	 * @param def The default text
	 * @return The String found at that location
	 */
	public static String get(String key, String def) {
		if (config == null)
			config = new YmlConfigFile("locale.yml");
		add(key, def);
		return ChatColor.translateAlternateColorCodes('$', config.getConfig().getString(key));
	}

	/**
	 * Sends an error message to the specified CommandSender
	 * 
	 * @param key The key to get
	 * @param sender The CommandSender to send the message to
	 */
	public static void sendError(String key, CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_RED + get(key));
	}

	/**
	 * Sends an error message to the specified CommandSender with default text
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param sender The CommandSender to send the message to
	 */
	public static void sendError(String key, String def, CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_RED + get(key, def));
	}
	
	/**
	 * Sends an error message to the specified CommandSender with replacements
	 * 
	 * @param key The key to get
	 * @param sender The CommandSender to send the message to
	 * @param replace The group of replacements (what to replace followed by replacement)
	 */
	public static void sendError(String key, CommandSender sender, String... replace) {
		String msg = get(key);
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
		sender.sendMessage(ChatColor.DARK_RED + msg);
	}
	
	/**
	 * Sends an error message to the specified CommandSender with default text and replacements
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param sender The CommandSender to send the message to
	 * @param replace The group of replacements (what to replace followed by replacement)
	 */
	public static void sendError(String key, String def, CommandSender sender, String... replace) {
		String msg = get(key, def);
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
		sender.sendMessage(ChatColor.DARK_RED + msg);
	}

	/**
	 * Sends a message to the specified CommandSender
	 * 
	 * @param key The key to get
	 * @param sender The CommandSender to send the message to
	 */
	public static void sendMessage(String key, CommandSender sender) {
		sender.sendMessage(get(key));
	}

	/**
	 * Sends a message to the specified CommandSender with default text
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param sender The CommandSender to send the message to
	 */
	public static void sendMessage(String key, String def, CommandSender sender) {
		sender.sendMessage(get(key, def));
	}
	
	/**
	 * Sends a message to the specified CommandSender with replacements
	 * 
	 * @param key The key to get
	 * @param sender The CommandSender to send the message to
	 * @param replace The group of replacements (what to replace followed by replacement)
	 */
	public static void sendMessage(String key, CommandSender sender, String... replace) {
		String msg = get(key);
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
		sender.sendMessage(msg);
	}
	
	/**
	 * Sends a message to the specified CommandSender with default text and replacements
	 * 
	 * @param key The key to get
	 * @param def The default text
	 * @param sender The CommandSender to send the message to
	 * @param replace The group of replacements (what to replace followed by replacement)
	 */
	public static void sendMessage(String key, String def, CommandSender sender, String... replace) {
		String msg = get(key, def);
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
		sender.sendMessage(msg);
	}

}
