package com.minnymin.zephyrus.core.chat;


import org.json.simple.JSONObject;

import com.minnymin.zephyrus.core.chat.MessageEvent.MessageClickEvent;
import com.minnymin.zephyrus.core.chat.MessageEvent.MessageHoverEvent;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageColor;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageFormatting;

/**
 * Zephyrus - MessageComponent.java
 *
 * @author minnymin3
 *
 */
public class MessageComponent {

	private JSONObject component;

	/**
	 * Creates a new MessageComponent
	 * 
	 * @param text The text of the message
	 * @param color The colour of the message
	 * @param formats The formatting for the message
	 */
	public MessageComponent(String text, MessageColor color, MessageFormatting... formats) {
		component = new JSONObject();
		put("text", text);
		if (color != null) {
			put("color", color.getColor());
		}
		for (MessageFormatting format : MessageFormatting.values()) {
			put(format.getFormat(), false);
		}
		for (MessageFormatting format : formats) {
			put(format.getFormat(), true);
		}
	}

	/**
	 * Sets the click event for the section of text
	 * 
	 * @param type The operation to perform on click
	 * @param value The value of the operation (command, url, etc.)
	 * @return The message component with the event set (used for easy
	 *         construction of messages)
	 */
	@SuppressWarnings("unchecked")
	public MessageComponent setClickEvent(MessageClickEvent type, String value) {
		JSONObject event = new JSONObject();
		event.put("action", type.getTypeName());
		event.put("value", value);
		put("clickEvent", event);
		return this;
	}

	/**
	 * Sets the hover event for the section of text
	 * 
	 * @param type The operation to perform on hover
	 * @param value The value of the operation (message, achievement, etc.)
	 * @return The message component with the event set (used for easy
	 *         construction of messages)
	 */
	@SuppressWarnings("unchecked")
	public MessageComponent setHoverEvent(MessageHoverEvent type, String value) {
		JSONObject event = new JSONObject();
		event.put("action", type.getTypeName());
		event.put("value", value);
		put("hoverEvent", event);
		return this;
	}

	/**
	 * Gets the json object
	 */
	public JSONObject getJSON() {
		return component;
	}

	@SuppressWarnings("unchecked")
	private void put(String key, Object value) {
		component.put(key, value);
	}

	/**
	 * A list of all the achievements available for use with MessageHoverEvent.ACHIEVEMENT
	 */
	public enum MessageAchievement {

		OPEN_INVENTORY("achievement.openInventory"),
		MINE_WOOD("achievement.mineWood"),
		CRAFT_WORKBENCH("achievement.buildWorkbench"),
		CRAFT_PICKAXE("achievement.buildPickaxe"),
		CRAFT_FURNACE("achievement.buildFurnace"),
		ACQUIRE_IRON("achievement.aquireIron"),
		CRAFT_HOE("achievement.buildHoe"),
		MAKE_BREAD("achievement.makeBread"),
		BAKE_CAKE("achievement.bakeCake"),
		CRAFT_BETTER_PICKAXE("achievement.buildBetterPickaxe"),
		COOK_FISH("achievement.cookFish"),
		ON_A_RAIL("achievement.onARail"),
		CRAFT_SWORD("achievement.buildSword"),
		KILL_ENEMY("achievement.killEnemy"),
		KILL_COW("achievement.killCow"),
		FLY_PIG("achievement.flyPig"),
		SNIPE_SKELETON("achievement.snipeSkeleton"),
		GET_DIAMONDS("achievement.getDiamonds"),
		NETHER_PORTAL("achievement.netherPortal"),
		GHAST_RETURN("achievement.ghastReturn"),
		GET_BLAZE_ROD("achievement.getBlazeRod"),
		BREW_POTION("achievement.brewPotion"),
		END_PORTAL("achievement.endPortal"),
		THE_END("achievement.theEnd"),
		ENCHANTMENTS("achievement.enchantments"),
		OVERKILL("achievement.overkill"),
		BOOKCASE("achievement.bookcase"),
		BREED_COW("achievement.breedCow"),
		SPAWN_WITHER("achievement.spawnWither"),
		KILL_WITHER("achievement.killWither"),
		FULL_BEACON("achievement.fullBeacon"),
		EXPLORE_ALL_BIOMES("achievement.exploreAllBiomes"),
		DIAMONDS_TO_YOU("achievement.diamondsToYou");

		private String name;

		MessageAchievement(String name) {
			this.name = name;
		}

		public String getValue() {
			return name;
		}

	}

}
