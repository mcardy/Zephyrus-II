package net.minezrc.zephyrus.core.chat;

/**
 * Zephyrus - MessageForm.java
 *
 * @author minnymin3
 *
 */
public class MessageForm {

	public enum MessageColor {
		WHITE("white"), YELLOW("yellow"), LIGHT_PURPLE("light_purple"), RED("red"), AQUA("aqua"), GREEN("green"), BLUE(
				"blue"), DARK_GRAY("dark_gray"), GRAY("gray"), GOLD("gold"), DARK_PURPLE("dark_purple"), DARK_RED(
				"dark_red"), DARK_AQUA("dark_aqua"), DARK_GREEN("dark_green"), DARK_BLUE("dark_blue"), BLACK("black");

		private final String color;

		MessageColor(String color) {
			this.color = color;
		}

		public String getColor() {
			return color;
		}
	}

	public enum MessageFormatting {
		BOLD("bold"), UNDERLINED("underlined"), STRIKETHROUGH("strikethrough"), ITALIC("italic"), OBFUSCATED(
				"obfuscated"), NONE("");

		private final String format;

		MessageFormatting(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}

}
