package com.minnymin.zephyrus.core.nms.packet.server;


import org.bukkit.Location;

import com.minnymin.zephyrus.core.nms.packet.ServerPacket;
import com.minnymin.zephyrus.core.nms.packet.PacketType.OutgoingPacket;

/**
 * Zephyrus - PacketEntityTeleport.java
 *
 * @author minnymin3
 *
 */
public class PacketEntityTeleport extends ServerPacket {

	public PacketEntityTeleport(int id, Location loc) {
		super(OutgoingPacket.ENTITY_TELEPORT);
		setValue(int.class, 0, id);
		setValue(int.class, 1, loc.getBlockX() * 32);
		setValue(int.class, 2, loc.getBlockY() * 32);
		setValue(int.class, 3, loc.getBlockZ() * 32);
		setValue(byte.class, 0, (byte) ((int) loc.getYaw() * 256F / 360F));
		setValue(byte.class, 1, (byte) ((int) loc.getPitch() * 256F / 360F));
	}

}
