package net.minezrc.zephyrus.core.packet.server;

import net.minezrc.zephyrus.core.packet.ServerPacket;
import net.minezrc.zephyrus.core.packet.PacketType.OutgoingPacket;
import net.minezrc.zephyrus.core.util.reflection.NMSUtils;

import org.bukkit.entity.Entity;

/**
 * Zephyrus - PacketEntityLivingSpawn.java
 *
 * @author minnymin3
 *
 */
public class PacketEntityLivingSpawn extends ServerPacket {

	public PacketEntityLivingSpawn(Entity en) {
		super(OutgoingPacket.SPAWN_ENTITY_LIVING);
		try {
			Class<?> entityClass = NMSUtils.getNMSClass("EntityLiving");
			this.packet = NMSUtils.getNMSClass(OutgoingPacket.SPAWN_ENTITY_LIVING.getPacketName())
					.getConstructor(entityClass).newInstance(NMSUtils.getHandle(en));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
