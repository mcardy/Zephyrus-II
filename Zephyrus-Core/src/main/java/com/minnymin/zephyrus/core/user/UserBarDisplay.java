package com.minnymin.zephyrus.core.user;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityDestroy;
import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityLivingSpawn;
import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityMetadata;
import com.minnymin.zephyrus.core.nms.packet.server.PacketEntityTeleport;
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
	 * 
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
		playerMap.put(player.getName(), en);
	}

	private class BossEntity {

		private String name;
		private int health;
		private Object nmsDragon;
		private Player player;
		private int id;

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
			Location loc = player.getLocation();
			Class<?> nmsDragonClass = NMSUtils.getNMSClass("EntityEnderDragon");
			nmsDragon = ReflectionUtils.newInstance(nmsDragonClass, new Class<?>[] { NMSUtils.getNMSClass("World") },
					NMSUtils.getHandle(player.getLocation().getWorld()));

			ReflectionUtils.invokeMethod(nmsDragon, "setLocation", new Class<?>[] { double.class, double.class,
					double.class, float.class, float.class }, loc.getX(), loc.getY(), loc.getZ(), 0, 0);
			ReflectionUtils.invokeMethod(nmsDragon, "setInvisible", new Class<?>[] { boolean.class }, false);
			ReflectionUtils.invokeMethod(nmsDragon, "setCustomName", new Class<?>[] { String.class }, name);
			ReflectionUtils.invokeMethod(nmsDragon, "setHealth", new Class<?>[] { float.class }, health);
			id = (Integer) ReflectionUtils.getDeepField(nmsDragon, "id");

			PacketEntityLivingSpawn packet = new PacketEntityLivingSpawn(nmsDragon);
			packet.send(player);
		}

		private void sendDestroy() {
			new PacketEntityDestroy(id).send(player);
		}

		private void sendMeta() {
			new PacketEntityMetadata(id, getWatcher()).send(player);
		}

		private void sendTeleport() {
			new PacketEntityTeleport(id, player.getLocation().add(0, -400, 0)).send(player);
		}

		private Object getWatcher() {
			Class<?> entity = NMSUtils.getNMSClass("Entity");
			Class<?> dataWatcher = NMSUtils.getNMSClass("DataWatcher");
			Object watcher = ReflectionUtils.newInstance(dataWatcher, new Class<?>[] { entity }, (Object) null);
			ReflectionUtils.invokeMethod(watcher, "a", new Class<?>[]{int.class, Object.class}, 0, (Byte) (byte) 0x20);
			ReflectionUtils.invokeMethod(watcher, "a", new Class<?>[]{int.class, Object.class}, 6, (Float) (float) health);
			ReflectionUtils.invokeMethod(watcher, "a", new Class<?>[]{int.class, Object.class}, 10, (String) name);
			ReflectionUtils.invokeMethod(watcher, "a", new Class<?>[]{int.class, Object.class}, 11, (Integer) (int) health);
			return watcher;
		}

	}
}
