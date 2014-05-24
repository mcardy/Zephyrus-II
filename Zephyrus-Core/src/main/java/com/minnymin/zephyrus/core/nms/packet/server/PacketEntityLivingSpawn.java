package com.minnymin.zephyrus.core.nms.packet.server;

import com.minnymin.zephyrus.core.nms.packet.PacketType.OutgoingPacket;
import com.minnymin.zephyrus.core.nms.packet.ServerPacket;
import com.minnymin.zephyrus.core.util.reflection.NMSUtils;

/**
 * Zephyrus - PacketEntityLivingSpawn.java
 *
 * @author minnymin3
 *
 */
public class PacketEntityLivingSpawn extends ServerPacket {

	public PacketEntityLivingSpawn(Object en) {
		super(OutgoingPacket.SPAWN_ENTITY_LIVING);
		try {
			Class<?> entityClass = NMSUtils.getNMSClass("EntityLiving");
			this.packet = NMSUtils.getNMSClass(OutgoingPacket.SPAWN_ENTITY_LIVING.getPacketName())
					.getConstructor(entityClass).newInstance(en);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
