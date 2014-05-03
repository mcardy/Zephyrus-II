package com.minnymin.zephyrus.core.util;

import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;

/**
 * Zephyrus - VersionInfo.java
 * 
 * @author minnymin3
 * 
 */

public class VersionInfo {

	public static void load() {
		if (Zephyrus.getPlugin().getDescription().getVersion().contains("B:")) {
			YmlConfigFile file = new YmlConfigFile("version.yml");
			try {
				int version = Integer.parseInt(Zephyrus.getPlugin().getDescription().getVersion().split("B:")[1]);
				if (file.getConfig().getInt("version") < version) {
					Zephyrus.getPlugin()
							.getLogger()
							.warning(
									"You have upgraded from one development version of Zephyrus to the next."
											+ " Things may have changed and it is recommended that you delete spells.yml and locale.yml.");
					file.getConfig().set("version", version);
					file.saveConfig();
				}
			} catch (Exception ex) {
			}
		}
	}
}
