package net.minezrc.zephyrus.core.packet.server;

import net.minezrc.zephyrus.core.packet.ServerPacket;
import net.minezrc.zephyrus.core.packet.PacketType.OutgoingPacket;

import org.bukkit.entity.Player;

/**
 * Zephyrus - PacketPlayerAnimation.java
 *
 * @author minnymin3
 *
 */
public class PacketPlayerAnimation extends ServerPacket {

	public PacketPlayerAnimation(Player player, PlayerAnimation animation) {
		super(OutgoingPacket.PLAYER_ANIMATION);
		setValue(int.class, 0, player.getEntityId());
		setValue(int.class, 1, (int) animation.getId());
	}

	public enum PlayerAnimation {

		SWING_ARM(0), DAMAGE(1), LEAVE_BED(2), EAT(3), CRIT(4), MAGIC_CRIT(5), UNKNOWN(102), CROUCH(104), UNCROUCH(105);

		private byte id;

		PlayerAnimation(int id) {
			this.id = (byte) id;
		}

		public byte getId() {
			return id;
		}
	}

}
