package com.minnymin.zephyrus.core.packet;


import org.bukkit.entity.Player;

import com.minnymin.zephyrus.core.packet.PacketType.OutgoingPacket;
import com.minnymin.zephyrus.core.util.reflection.NMSUtils;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;

/**
 * Zephyrus - ServerPacket.java
 *
 * @author minnymin3
 *
 */
public class ServerPacket extends Packet {

	/**
	 * Creates a new packet from the given object
	 */
	public ServerPacket(Object packet) {
		this.packet = packet;
	}
	
	/**
	 * Creates a new packet with given type
	 * 
	 * @param type
	 */
	public ServerPacket(OutgoingPacket type) {
		this.packet = type.getPacketObj();
	}
	
	/**
	 * Sends the packet to the given player
	 */
	public void send(Player player) {
		try {
			if (PacketType.lookupServer(packet) != null) {
				Object nmsPlayer = NMSUtils.getHandle(player);
				Object connection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
				ReflectionUtils.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
