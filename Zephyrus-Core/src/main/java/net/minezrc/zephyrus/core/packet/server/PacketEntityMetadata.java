package net.minezrc.zephyrus.core.packet.server;

import java.util.List;

import net.minezrc.zephyrus.core.packet.ServerPacket;
import net.minezrc.zephyrus.core.packet.PacketType.OutgoingPacket;
import net.minezrc.zephyrus.core.util.reflection.ReflectionUtils;

/**
 * Zephyrus - PacketEntityMetadata.java
 *
 * @author minnymin3
 *
 */
public class PacketEntityMetadata extends ServerPacket {

	public PacketEntityMetadata(int id, Object watcher) {
		super(OutgoingPacket.ENTITY_METADATA);
		setValue(int.class, 0, id);
		@SuppressWarnings("rawtypes")
		List meta = (List) ReflectionUtils.invokeMethod(watcher, "c");
		setValue(List.class, 0, meta);
	}

}
