package com.minnymin.zephyrus.core.util;

import org.bukkit.plugin.Plugin;

import com.minnymin.zephyrus.YmlConfigFile;

/**
 * Zephyrus - VersionInfo.java
 * 
 * @author minnymin3
 * 
 */

public class VersionInfo {

	public static void load(Plugin plugin) {
		YmlConfigFile file = new YmlConfigFile("version.yml");
		if (plugin.getDescription().getVersion().contains("B:")) {
			try {
				int version = Integer.parseInt(plugin.getDescription().getVersion().split("B:")[1]);
				if (file.getConfig().getInt("version") < version) {
					plugin.getLogger()
							.warning(
									"You have upgraded from one development version of Zephyrus to the next."
											+ " Things may have changed and it is recommended that you delete spells.yml and locale.yml.");
					file.getConfig().set("version", version);
					file.saveConfig();
				}
			} catch (Exception ex) {
			}
		} else {
			if (file.getConfig().getString("release") != plugin.getDescription().getVersion()) {
				String previous = file.getConfig().getString("release");
				file.getConfig().set("release", plugin.getDescription().getVersion());
				file.saveConfig();
				updateLanguage(previous);
			}
		}
	}
	
	/**
	 * If language items have changed they will be updated here when the plugin updates
	 */
	private static void updateLanguage(String previousVersion) {
		if (previousVersion == "2.0.0") {
			//Language.remove("");
		}
	}
}
