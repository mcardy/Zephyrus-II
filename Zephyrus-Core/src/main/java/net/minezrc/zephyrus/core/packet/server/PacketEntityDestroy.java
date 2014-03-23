package net.minezrc.zephyrus.core.packet.server;

import net.minezrc.zephyrus.core.packet.ServerPacket;
import net.minezrc.zephyrus.core.packet.PacketType.OutgoingPacket;

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
