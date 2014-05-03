package com.minnymin.zephyrus.core.chat;

/**
 * Zephyrus - MessageEvent.java
 *
 * @author minnymin3
 *
 */
public class MessageEvent {

	public enum MessageClickEvent {
		RUN_COMMAND("run_command"), SUGGEST_COMMAND("suggest_command"), OPEN_URL("open_url");

		private final String type;

		MessageClickEvent(String type) {
			this.type = type;
		}

		public String getTypeName() {
			return type;
		}
	}

	public enum MessageHoverEvent {
		TEXT("show_text"), ITEM("show_item"), ACHIEVEMENT("show_achievement");

		private final String type;

		MessageHoverEvent(String type) {
			this.type = type;
		}

		public String getTypeName() {
			return type;
		}
	}

}
