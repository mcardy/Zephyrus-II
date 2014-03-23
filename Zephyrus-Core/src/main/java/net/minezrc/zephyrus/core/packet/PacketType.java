package net.minezrc.zephyrus.core.packet;

import java.util.HashMap;
import java.util.Map;

import net.minezrc.zephyrus.core.packet.server.PacketChat;
import net.minezrc.zephyrus.core.packet.server.PacketEntityDestroy;
import net.minezrc.zephyrus.core.packet.server.PacketEntityLivingSpawn;
import net.minezrc.zephyrus.core.packet.server.PacketEntityMetadata;
import net.minezrc.zephyrus.core.packet.server.PacketEntityTeleport;
import net.minezrc.zephyrus.core.packet.server.PacketPlayerAnimation;
import net.minezrc.zephyrus.core.packet.server.PacketPlayerBed;
import net.minezrc.zephyrus.core.util.reflection.NMSUtils;

/**
 * Zephyrus - PacketType.java
 *
 * @author minnymin3
 *
 */
public class PacketType {

	private static Map<String, OutgoingPacket> serverLookup = new HashMap<String, OutgoingPacket>();

	public enum OutgoingPacket {
		/**
		 * Frequently sent out to make sure client has connection
		 */
		KEEP_ALIVE("PacketPlayOutKeepAlive"),
		/**
		 * Login information (gamemode, dimension, etc)
		 */
		LOGIN("PacketPlayOutLogin"),
		/**
		 * Sent for chat messages
		 * 
		 * @see PacketChat
		 */
		CHAT("PacketPlayOutChat"),
		/**
		 * Sent to update the world time
		 */
		WORLD_TIME("PacketPlayOutUpdateTime"),
		/**
		 * Sends the currently equipped armour of an entity
		 */
		ENTITY_EQUIPMENT("PacketPlayOutEntityEquipment"),
		/**
		 * Sent to set the spawn location. Can be used to update the location
		 * that the compass points
		 */
		PLAYER_SPAWN_POSITION("PacketPlayOutSpawnPosition"),
		/**
		 * Sent to update the player's health, food and saturation
		 */
		PLAYER_HEALTH("PacketPlayOutUpdateHealth"),
		/**
		 * Sent to change a player's dimension
		 */
		PLAYER_RESPAWN("PacketPlayOutRespawn"),
		/**
		 * Sent to update the player's position and orientation
		 */
		PLAYER_POSITION("PacketPlayOutPosition"),
		/**
		 * Sent to change the player's currently selected item
		 */
		PLAYER_HELD_ITEM("PacketPlayOutHeldItemSlot"),
		/**
		 * Sent to put the player in the bed position
		 * 
		 * @see PacketPlayerBed
		 */
		PLAYER_BED("PacketPlayOutBed"),
		/**
		 * Sent to execute a player animation
		 * 
		 * @see PacketPlayerAnimation
		 */
		PLAYER_ANIMATION("PacketPlayOutAnimation"),
		/**
		 * Sent to other players when a player comes into visible range
		 */
		SPAWN_PLAYER("PacketPlayOutNamedEntitySpawn"),
		/**
		 * Sent to display an item pickup animation
		 */
		ITEM_COLLECT("PacketPlayOutCollect"),
		/**
		 * Sent to spawn base entities (minecarts, boats, etc.)
		 */
		SPAWN_ENTITY("PacketPlayOutSpawnEntity"),
		/**
		 * Sent to spawn a living entity
		 * 
		 * @see PacketEntityLivingSpawn
		 */
		SPAWN_ENTITY_LIVING("PacketPlayOutSpawnEntityLiving"),
		/**
		 * Sent to spawn a painting (location, name, type)
		 */
		SPAWN_ENTITY_PAINTING("PacketPlayOutSpawnEntityPainting"),
		/**
		 * Sent to spawn experience orbs
		 */
		SPAWN_EXPERIENCE_ORB("PacketPlayOutSpawnEntityExperienceOrb"),
		/**
		 * Sent to set an entity's velocity
		 */
		ENTITY_VELOCITY("PacketPlayOutEntityVelocity"),
		/**
		 * Sent to remove an entity from the world
		 * 
		 * @see PacketEntityDestroy
		 */
		ENTITY_KILL("PacketPlayOutEntityDestroy"),
		/**
		 * Basic entity packet. Initializes entry client side
		 */
		ENTITY("PacketPlayOutEntity"),
		/**
		 * Sent when entity moves less than 4 blocks. Any more than 4 blocks use
		 * entity teleport packet
		 */
		ENTITY_MOVE("PacketPlayOutRelEntityMove"),
		/**
		 * Sent to update an entity's orientation
		 */
		ENTITY_LOOK("PacketPlayOutEntityLook"),
		/**
		 * Sent when an entity moves and orients. Combination of entity move and
		 * entity look
		 */
		ENTITY_MOVE_LOOK("PacketPlayOutRelEntityMoveLook"),
		/**
		 * Sent to teleport an entity (entity moves more than 4 blocks)
		 * 
		 * @see PacketEntityTeleport
		 */
		ENTITY_TELEPORT("PacketPlayOutEntityTeleport"),
		/**
		 * Sent to change an entity's head rotation
		 */
		ENTITY_HEAD_ROTATION("PacketPlayOutEntityHeadRotation"),
		/**
		 * Sent when an entity's status changes (wolf tamed, sheep eating grass,
		 * etc)
		 */
		ENTITY_STATUS("PacketPlayOutEntityStatus"),
		/**
		 * Sent when an entity mounts another entity (entering a minecart/boat)
		 */
		ENTITY_MOUNT("PacketPlayOutAttachEntity"),
		/**
		 * Sent to update an entity's metadata
		 * 
		 * @see PacketEntityMetadata
		 */
		ENTITY_METADATA("PacketPlayOutEntityMetadata"),
		/**
		 * Sent to add a potion effect to an entity
		 */
		ENTITY_EFFECT("PacketPlayOutEntityEffect"),
		/**
		 * Sent to remove a potion effect from an entity
		 */
		ENTITY_EFFECT_REMOVE("PacketPlayOutRemoveEntityEffect"),
		/**
		 * Sent to update a player's experience
		 */
		PLAYER_EXPERIENCE("PacketPlayOutExperience"),
		/**
		 * Sent to update entity attributes
		 */
		ENTITY_DATA("PacketPlayOutUpdateAttributes"),
		/**
		 * Sent to load/unload/update a chunk
		 */
		CHUNK("PacketPlayOutMapChunk"),
		/**
		 * Sent to change multiple blocks at once
		 */
		BLOCK_CHANGE_MULTI("PacketPlayOutMultiBlockChange"),
		/**
		 * Sent to change a block
		 */
		BLOCK_CHANGE("PacketPlayOutBlockChange"),
		/**
		 * Sent to activate a block (chest open, piston extending, etc)
		 */
		BLOCK_ACTION("PacketPlayOutBlockAction"),
		/**
		 * Sent when a block is broken to display the animation
		 */
		BLOCK_BREAK("PacketPlayOutBlockBreakAnimation"),
		/**
		 * Sent when multiple chunks update
		 */
		CHUNK_BULK("PacketPlayOutMapChunkBulk"),
		/**
		 * Sent when an explosion takes place
		 */
		EXPLOSION("PacketPlayOutExplosion"),
		/**
		 * Sent when an effect is played
		 * 
		 * @see player.playEffect()
		 */
		EFFECT_WORLD("PacketPlayOutWorldEvent"),
		/**
		 * Sent when a sound is played
		 * 
		 * @see player.playSound()
		 */
		EFFECT_SOUND("PacketPlayOutNamedSoundEffect"),
		/**
		 * Sent when a particle effect is created
		 * 
		 * @see ParticleEffects
		 */
		EFFECT_PARTICLE("PacketPlayOutWorldParticles"),
		/**
		 * Sent when a bed can't be used as a spawn point and when the rain
		 * state changes
		 */
		GAMESTATE("PacketPlayOutGameStateChange"),
		/**
		 * Sent when a lightning bolt strikes
		 */
		WORLD_LIGHTNING("PacketPlayOutSpawnEntityWeather"),
		/**
		 * Sent to open a window (crafting, furnace, etc)
		 */
		INVENTORY_OPEN("PacketPlayOutOpenWindow"),
		/**
		 * Sent to close a window
		 */
		INVENTORY_CLOSE("PacketPlayOutCloseWindow"),
		/**
		 * Sent to update a single slot
		 */
		INVENTORY_SLOT("PacketPlayOutSetSlot"),
		/**
		 * Sent to update multiple slots
		 */
		INVENTORY_SLOT_BULK("PacketPlayOutWindowItems"),
		/**
		 * Sent to update a furnace progress bar
		 */
		INVENTORY_PROPERTY("PacketPlayOutCraftProgressBar"),
		/**
		 * Sent to confirm a villager trade
		 */
		INVENTORY_TRANSACTION("PacketPlayOutTransaction"),
		/**
		 * Sent to update a sign's text
		 */
		SIGN_UPDATE("PacketPlayOutUpdateSign"),
		/**
		 * Sent for map item image
		 */
		MAP_DATA("PacketPlayOutMap"),
		/**
		 * Sent to update tile entities
		 */
		BLOCK_TILE_DATA("PacketPlayOutTileEntityData"),
		/**
		 * Sent to open the sign editor
		 */
		SIGN_EDIT("PacketPlayOutOpenSignEditor"),
		/**
		 * Sent to give a player a statistic
		 */
		STATISTIC("PacketPlayOutStatistic"),
		/**
		 * Sent when a player presses tab in game to display the playerlist
		 */
		PLAYER_LIST("PacketPlayOutPlayerInfo"),
		/**
		 * Sent to update player walk/fly speed
		 */
		PLAYER_ABILITIES("PacketPlayOutAbilities"),
		/**
		 * Sent when a player presses tab in chat
		 */
		CHAT_TAB_COMPLETE("PacketPlayOutTabComplete"),
		/**
		 * Sent to create/remove a scoreboard objective
		 */
		SCOREBOARD_OBJECTIVE("PacketPlayOutScoreboardObjective"),
		/**
		 * Sent to update the score on a scoreboard
		 */
		SCOREBOARD_SCORE("PacketPlayOutScoreboardScore"),
		/**
		 * Sent to set the display slot of an objective
		 */
		SCOREBOARD_DISPLAY_OBJECTIVE("PacketPlayOutScoreboardDisplayObjective"),
		/**
		 * Sent to create and update teams
		 */
		SCOREBOARD_TEAM("PacketPlayOutScoreboardTeam"),
		/**
		 * A custom packet used for modded server-client communication
		 */
		CUSTOM_PAYLOAD("PacketPlayOutCustomPayload"),
		/**
		 * Sent to the client before disconnection
		 */
		PLAYER_DISCONNECT("PacketPlayOutKickDisconnect");

		private String name;

		OutgoingPacket(String name) {
			this.name = name;
			serverLookup.put(name, this);
		}

		/**
		 * Gets the name of the packet
		 */
		public String getPacketName() {
			return this.name;
		}

		public Class<?> getPacketClass() {
			return NMSUtils.getNMSClass(this.name);
		}

		/**
		 * Gets the packet object for the given packet type
		 * 
		 * @return
		 */
		public Object getPacketObj() {
			return NMSUtils.getNMSObject(this.name);
		}
	}

	public static OutgoingPacket lookupServer(Object packet) {
		return serverLookup.get(packet.getClass().getSimpleName());
	}

}
