package net.minezrc.zephyrus.core.nms;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.util.reflection.ReflectionUtils;
import net.minezrc.zephyrus.nms.NMSManager;
import net.minezrc.zephyrus.nms.UpgradeTrade;

import org.bukkit.Bukkit;

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
		Class<?> cls = ReflectionUtils.getClass("net.minezrc.zephyrus.nms." + getVersion() + ".NMSUpgradeTrade");
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
