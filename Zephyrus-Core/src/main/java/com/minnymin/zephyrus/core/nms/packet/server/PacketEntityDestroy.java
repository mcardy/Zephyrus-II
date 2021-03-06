package com.minnymin.zephyrus.core.nms.packet.server;

import com.minnymin.zephyrus.core.nms.packet.ServerPacket;
import com.minnymin.zephyrus.core.nms.packet.PacketType.OutgoingPacket;

/**
 * Zephyrus - PacketEntityDestroy.java
 *
 * @author minnymin3
 *
 */
public class PacketEntityDestroy extends ServerPacket {

	public PacketEntityDestroy(int id) {
		super(OutgoingPacket.ENTITY_KILL);
		setValue(int[].class, 0, new int[] {id});
	}

}
