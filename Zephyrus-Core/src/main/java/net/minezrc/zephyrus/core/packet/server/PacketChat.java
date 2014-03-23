package net.minezrc.zephyrus.core.packet.server;

import net.minezrc.zephyrus.core.chat.Message;
import net.minezrc.zephyrus.core.packet.ServerPacket;
import net.minezrc.zephyrus.core.packet.PacketType.OutgoingPacket;
import net.minezrc.zephyrus.core.util.reflection.NMSUtils;
import net.minezrc.zephyrus.core.util.reflection.ReflectionUtils;

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
