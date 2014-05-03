package com.minnymin.zephyrus.core.packet.server;


import org.bukkit.entity.Player;

import com.minnymin.zephyrus.core.packet.ServerPacket;
import com.minnymin.zephyrus.core.packet.PacketType.OutgoingPacket;

/**
 * Zephyrus - PacketPlayerExperience.java
 *
 * @author minnymin3
 *
 */
public class PacketPlayerExperience extends ServerPacket {

	/**
	 * Creates a new experience packet to set the player's experience bar client side
	 */
	public PacketPlayerExperience(Player player, int level, int progress) {
		this(player, level, progress, 0);
	}
	
	/**
	 * Creates a new experience packet to set the player's experience bar client side
	 */
	public PacketPlayerExperience(Player player, int level, int progress, int total) {
		super(OutgoingPacket.PLAYER_EXPERIENCE);
		setValue(float.class, 0, progress/100F);
		setValue(int.class, 0, total);
		setValue(int.class, 1, level);
	}

}
