package com.minnymin.zephyrus.core.nms.packet.server;

import com.minnymin.zephyrus.core.chat.Message;
import com.minnymin.zephyrus.core.nms.packet.ServerPacket;
import com.minnymin.zephyrus.core.nms.packet.PacketType.OutgoingPacket;
import com.minnymin.zephyrus.core.util.reflection.NMSUtils;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;

/**
 * Zephyrus - PacketChat.java
 *
 * @author minnymin3
 *
 */
public class PacketChat extends ServerPacket {

	public PacketChat(Message message) {
		this(message.getMessage());
	}

	@SuppressWarnings("deprecation")
	public PacketChat(String message) {
		super(OutgoingPacket.CHAT);
		Object msg = ReflectionUtils.invokeStaticMethod(NMSUtils.getNMSClass("ChatSerializer"), "a",
				new Class<?>[] { String.class }, message);
		setValue(msg, 0);
	}
}
