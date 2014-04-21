package net.minezrc.zephyrus.core.spell.world;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.ChatColor;
import org.bukkit.World;

/**
 * Zephyrus - Clock.java
 * 
 * @author minnymin3
 * 
 */

public class Clock extends Spell {

	public Clock() {
		super("clock", "Change time at will", 500, 20, AspectList.newList(Aspect.TIME, 100, Aspect.LIGHT, 50,
				Aspect.DARKNESS, 50), 18, SpellElement.NEUTREAL, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		if (args.length == 0) {
			ChatColor color;
			long time = user.getPlayer().getWorld().getTime();
			if (time > 12500) {
				color = ChatColor.DARK_BLUE;
			} else {
				color = ChatColor.GOLD;
			}
			long hours = time / 1000;
			long minutes = Math.round((time - (hours * 1000)) / 16.6666666);
			boolean am = hours < 6 || hours >= 18;
			if (hours >= 18) {
				hours -= 12;
			}
			hours += 6;
			if (hours > 12) {
				hours -= 12;
			}
			if (hours == 0) {
				hours = 12;
			}
			String min = minutes < 10 ? "0" + minutes : minutes + "";
			String s = am ? "AM" : "PM";
			Language.sendMessage("spell.clock.time", "It is currently [TIME]", user.getPlayer(), "[TIME]", color + ""
					+ hours + ":" + min + " " + s);
			return CastResult.FAILURE;
		} else {
			World world = user.getPlayer().getWorld();
			if (args[0].equalsIgnoreCase("day")) {
				world.setTime(1000);
			} else if (args[0].equalsIgnoreCase("night")) {
				world.setTime(14000);
			} else if (args[0].equalsIgnoreCase("noon")) {
				world.setTime(6000);
			} else if (args[0].equalsIgnoreCase("midnight")) {
				world.setTime(18000);
			} else {
				try {
					int time = Integer.parseInt(args[0]);
					world.setTime(time);
				} catch (NumberFormatException ex) {
					Language.sendError("spells.clock.usage", "Expected number or <day|night|noon|midnight>",
							user.getPlayer());
					return CastResult.FAILURE;
				}
			}
			return CastResult.SUCCESS;
		}
	}

}
