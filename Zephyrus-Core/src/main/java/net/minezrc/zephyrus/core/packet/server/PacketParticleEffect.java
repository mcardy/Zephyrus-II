package net.minezrc.zephyrus.core.packet.server;

import net.minezrc.zephyrus.core.packet.ServerPacket;
import net.minezrc.zephyrus.core.packet.PacketType.OutgoingPacket;

import org.bukkit.Location;

/**
 * Zephyrus - PacketParticleEffect.java
 *
 * @author minnymin3
 *
 */
public class PacketParticleEffect extends ServerPacket {

	/**
	 * Creates a particle effect packet for given location
	 */
	public PacketParticleEffect(String name, Location loc, float offx, float offy, float offz, float speed, int count) {
		this(name, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), offx, offy, offz, speed, count);
	}

	/**
	 * Creates a particle effect packet for given x, y, and z
	 */
	public PacketParticleEffect(String name, float locx, float locy, float locz, float offx, float offy, float offz,
			float speed, int count) {
		super(OutgoingPacket.EFFECT_PARTICLE);
		setValue(String.class, 0, name);
		setValue(float.class, 0, locx);
		setValue(float.class, 1, locy);
		setValue(float.class, 2, locz);
		setValue(float.class, 3, offx);
		setValue(float.class, 4, offy);
		setValue(float.class, 5, offz);
		setValue(float.class, 6, speed);
		setValue(int.class, 0, count);
	}

}
