package com.minnymin.zephyrus.core.nms;


import org.bukkit.Bukkit;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;
import com.minnymin.zephyrus.nms.NMSManager;
import com.minnymin.zephyrus.nms.UpgradeTrade;

/**
 * Zephyrus - NMSManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleNMSManager implements NMSManager {

	private String version;
	
	public SimpleNMSManager() {
	}
	
	@Override
	public void load() {
		this.version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		Zephyrus.getPlugin().getLogger().info("[Version Dependency] Found Bukkit version " + getVersion());
		if (getItemUpgradeTrade() != null) {
			Zephyrus.getPlugin().getLogger().info("[Version Dependency] Loaded compatibility for version " + getVersion());
		} else {
			Zephyrus.getPlugin().getLogger().warning("[Version Dependency] Zephyrus is not fully compatible with this version of Bukkit! Some features may not be available!");
		}
	}

	@Override
	public void unload() {
	}

	@Override
	public UpgradeTrade getItemUpgradeTrade() {
		Class<?> cls = ReflectionUtils.getClass("com.minnymin.zephyrus.nms." + getVersion() + ".NMSUpgradeTrade");
		if (cls != null) {
			return (UpgradeTrade) ReflectionUtils.newInstance(cls);
		} else {
			return null;
		}
	}

	@Override
	public String getVersion() {
		return version;
	}

}
