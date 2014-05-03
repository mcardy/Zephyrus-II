package com.minnymin.zephyrus.core.user;

import java.util.HashMap;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.core.packet.server.PacketEntityDestroy;
import com.minnymin.zephyrus.core.packet.server.PacketEntityLivingSpawn;
import com.minnymin.zephyrus.core.packet.server.PacketEntityMetadata;
import com.minnymin.zephyrus.core.packet.server.PacketEntityTeleport;
import com.minnymin.zephyrus.core.util.reflection.NMSUtils;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;
import com.minnymin.zephyrus.user.BarDisplay;

/**
 * 
 * Zephyrus - UserBarDisplay.java
 * 
 * @author minnymin3
 *
 */
public class UserBarDisplay implements BarDisplay {

	private Map<String, BossEntity> playerMap;

	public UserBarDisplay() {
		playerMap = new HashMap<String, BossEntity>();
	}

	/**
	 * Remove the boss bar for the given player
	 */
	public void removeBar(Player player) {
		if (playerMap.containsKey(player.getName())) {
			BossEntity boss = playerMap.get(player.getName());
			boss.sendDestroy();
			playerMap.remove(player.getName());
		}
	}

	/**
	 * Set the boss bar for the given player
	 * @param name The name that appears above the bar
	 * @param health A number from 0 - 200 displaying the health
	 */
	public void setBar(Player player, String name, int health) {
		BossEntity en;
		if (playerMap.containsKey(player.getName())) {
			en = playerMap.get(player.getName());
		} else {
			en = new BossEntity(player, name, health);
			en.sendSpawn();
		}
		if (name.equalsIgnoreCase("")) {
			removeBar(player);
		} else {
			en.setName(name);
			en.setHealth(health);
			en.sendMeta();
			en.sendTeleport();
		}
	}

	private class BossEntity {

		private String name;
		private int health;
		private EnderDragon dragon;
		private Player player;

		BossEntity(Player player, String name, int health) {
			this.player = player;
			this.health = health;
			this.name = name;
		}

		private void setHealth(int health) {
			this.health = health;
		}

		private void setName(String name) {
			this.name = name;
		}

		private void sendSpawn() {
			int x = player.getLocation().getBlockX();
			int y = player.getLocation().add(0, -400, 0).getBlockY();
			int z = player.getLocation().getBlockZ();

			Class<?> nmsDragonClass = NMSUtils.getNMSClass("EntityEnderDragon");
			Class<?> craftServerClass = NMSUtils.getOBCClass("CraftServer");
			Class<?> craftDragonClass = NMSUtils.getOBCClass("entity.CraftEnderDragon");
			Object nmsdragon = ReflectionUtils.newInstance(nmsDragonClass,
					new Class<?>[] { NMSUtils.getNMSClass("World") },
					NMSUtils.getHandle(player.getLocation().getWorld()));

			ReflectionUtils.invokeMethod(nmsdragon, "setLocation", new Class<?>[] { double.class, double.class,
					double.class, float.class, float.class }, x, y, z, 0, 0);
			ReflectionUtils.invokeMethod(nmsdragon, "setInvisible", new Class<?>[] { boolean.class }, false);
			ReflectionUtils.invokeMethod(nmsdragon, "setCustomName", new Class<?>[] { String.class }, name);
			ReflectionUtils.invokeMethod(nmsdragon, "setHealth", new Class<?>[] { float.class }, health);

			this.dragon = (EnderDragon) ReflectionUtils.newInstance(craftDragonClass, new Class<?>[] {
					craftServerClass, nmsDragonClass }, Bukkit.getServer(), nmsdragon);
			PacketEntityLivingSpawn packet = new PacketEntityLivingSpawn(dragon);
			packet.send(player);
		}

		private void sendDestroy() {
			new PacketEntityDestroy(dragon.getEntityId()).send(player);
		}

		private void sendMeta() {
			new PacketEntityMetadata(dragon.getEntityId(), getWatcher()).send(player);
		}

		private void sendTeleport() {
			new PacketEntityTeleport(dragon.getEntityId(), player.getLocation().add(0, -400, 0)).send(player);
		}

		private Object getWatcher() {
			Class<?> entity = NMSUtils.getNMSClass("Entity");
			Class<?> dataWatcher = NMSUtils.getNMSClass("DataWatcher");
			Object watcher = ReflectionUtils.newInstance(dataWatcher, new Class<?>[] { entity },
					NMSUtils.getHandle(dragon));
			return watcher;
		}

	}
}
