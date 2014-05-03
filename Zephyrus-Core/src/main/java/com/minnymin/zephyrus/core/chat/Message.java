package com.minnymin.zephyrus.core.chat;


import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.minnymin.zephyrus.core.chat.MessageForm.MessageColor;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageFormatting;
import com.minnymin.zephyrus.core.packet.server.PacketChat;
import com.minnymin.zephyrus.core.util.Language;

/**
 * Zephyrus - Message.java
 *
 * @author minnymin3
 *
 */
public class Message {

	private JSONObject message;
	private MessageColor color;
	private MessageFormatting[] formats;

	/**
	 * Create a new message
	 * 
	 * @param key The key of the language message
	 * @param def The default text
	 * @param color The colour of the message
	 * @param formats The formatting for the message
	 */
	public Message(String key, String def, MessageColor color, MessageFormatting... formats) {
		this.message = new JSONObject();
		this.color = color;
		add("text", Language.get(key, def));
		if (color != null) {
			add("color", color.getColor());
		}
		for (MessageFormatting format : formats) {
			if (format != MessageFormatting.NONE) {
				add(format.getFormat(), true);
			}
		}
	}

	/**
	 * Adds a MessageComponent to this message. Appends message onto base text
	 * 
	 * @return The message object with the component added (used for easy
	 *         construction of messages)
	 */
	@SuppressWarnings("unchecked")
	public Message addComponent(MessageComponent component) {
		if (!message.containsKey("extra")) {
			add("extra", new JSONArray());
		}
		JSONArray extras = (JSONArray) message.get("extra");
		extras.add(component.getJSON());
		add("extra", extras);
		return this;
	}

	/**
	 * Appends a string onto the message with the original color
	 * 
	 * @param s The string to append
	 * @return The message object with the component added (used for easy
	 *         construction of messages)
	 */
	public Message append(String s) {
		append(s, this.color, this.formats);
		return this;
	}

	/**
	 * Appends a string onto the message
	 * 
	 * @param s The string to append
	 * @param color The color to apply to the section
	 * @param formats Optional formatting for the section
	 * @return The message object with the component added (used for easy
	 *         construction of messages)
	 */
	public Message append(String s, MessageColor color, MessageFormatting... formats) {
		addComponent(new MessageComponent(s, color, formats));
		return this;
	}

	/**
	 * Sends the message to the player
	 */
	public void sendMessage(Player player) {
		new PacketChat(this).send(player);
	}

	/**
	 * Gets the json raw message
	 */
	public String getMessage() {
		return message.toJSONString();
	}

	/**
	 * Gets the json object
	 */
	public JSONObject getJSON() {
		return message;
	}

	@SuppressWarnings("unchecked")
	private void add(String key, Object value) {
		message.put(key, value);
	}

}
