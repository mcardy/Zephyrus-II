package com.minnymin.zephyrus.core.util;


import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.core.config.ConfigOptions;
import com.minnymin.zephyrus.core.nms.packet.server.PacketParticleEffect;

/**
 * Zephyrus - ParticleEffects.java
 *
 * @author minnymin3
 *
 */
public class ParticleEffects {

	public enum Particle {
		HUGE_EXPLODE("hugeexplosion"),
		LARGE_EXPLODE("largeexplode"),
		FIREWORK_SPARK("fireworksSpark"),
		AIR_BUBBLE("bubble"),
		SUSPEND("suspend"),
		DEPTH_SUSPEND("depthSuspend"),
		TOWN_AURA("townaura"),
		CRITICAL_HIT("crit"),
		MAGIC_CRITICAL_HIT("magicCrit"),
		MOB_SPELL("mobSpell"),
		MOB_SPELL_AMBIENT("mobSpellAmbient"),
		SPELL("spell"),
		INSTANT_SPELL("instantSpell"),
		BLUE_SPARKLE("witchMagic"),
		NOTE_BLOCK("note"),
		ENDER("portal"),
		ENCHANTMENT_TABLE("enchantmenttable"),
		EXPLODE("explode"),
		FIRE("flame"),
		LAVA_SPARK("lava"),
		FOOTSTEP("footstep"),
		SPLASH("splash"),
		LARGE_SMOKE("largesmoke"),
		CLOUD("cloud"),
		REDSTONE_DUST("reddust"),
		SNOWBALL_HIT("snowballpoof"),
		DRIP_WATER("dripWater"),
		DRIP_LAVA("dripLava"),
		SNOW_DIG("snowshovel"),
		SLIME("slime"),
		HEART("heart"),
		ANGRY_VILLAGER("angryVillager"),
		GREEN_SPARKLE("happyVillager"),
		ICONCRACK("iconcrack"),
		TILECRACK("tilecrack");

		private String name;

		Particle(String name) {
			this.name = name;
		}

		/**
		 * Gets the name of the Particle Effect
		 * 
		 * @return The particle effect name
		 */
		String getName() {
			return name;
		}
	}

	/**
	 * Sends the particle effect to the given location
	 * 
	 * @param particle The particle to send
	 * @param loc The location to send it to
	 * @param count The number of particles to send
	 */
	public static void sendParticle(Particle particle, Location loc, int count) {
		sendParticle(particle, loc, 0, 0, 0, 0, count);
	}

	/**
	 * Sends the particle effect to the given location with the given offsets
	 * 
	 * @param particle The particle to send
	 * @param loc The location to send it to
	 * @param offx The offset on the X axis
	 * @param offy The offset on the Y axis
	 * @param offz The offset on the Z axis
	 * @param speed The speed/colour of the particle
	 * @param count The number of particles to send
	 */
	public static void sendParticle(Particle particle, Location loc, float offx, float offy, float offz, float speed,
			int count) {
		if (ConfigOptions.PARTICLE_EFFECTS) {
			PacketParticleEffect packet = new PacketParticleEffect(particle.name, loc, offx, offy, offz, speed, count);
			for (Player player : loc.getWorld().getPlayers()) {
				packet.send(player);
			}
		}
	}

}
