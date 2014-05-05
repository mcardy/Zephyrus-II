package com.minnymin.zephyrus.core.nms.packet.server;

import java.util.List;

import com.minnymin.zephyrus.core.nms.packet.ServerPacket;
import com.minnymin.zephyrus.core.nms.packet.PacketType.OutgoingPacket;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;


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
