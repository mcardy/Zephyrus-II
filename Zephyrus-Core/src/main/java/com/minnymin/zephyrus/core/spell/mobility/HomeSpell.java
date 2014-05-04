package com.minnymin.zephyrus.core.spell.mobility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - HomeSpell.java
 * 
 * @author minnymin3
 * 
 */

public class HomeSpell extends Spell {

	public HomeSpell() {
		super("home", "Lets you go home", 100, 10, AspectList.newList(Aspect.MOVEMENT, 50, Aspect.ENDER, 25,
				Aspect.KNOWLEDGE, 25), 4, SpellElement.ENDER, SpellType.MOBILITY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CastResult onCast(User user, int power, String[] args) {
		if (user.getData(getDefaultName()) == null && (args.length == 0 || !args[0].equalsIgnoreCase("set"))) {
			Language.sendMessage("spell.home.needset", "You need to set your home with '/cast home set'", user.getPlayer());
			return CastResult.FAILURE;
		}
		if (args.length > 0 && args[0].equalsIgnoreCase("set")) {
			Location loc = user.getPlayer().getLocation();
			JSONObject obj = new JSONObject();
			obj.put("world", loc.getWorld().getName());
			obj.put("x", loc.getX());
			obj.put("y", loc.getY());
			obj.put("z", loc.getZ());
			obj.put("yaw", loc.getYaw());
			obj.put("pitch", loc.getPitch());
			user.setData(getDefaultName(), obj.toJSONString());
			Language.sendMessage("spell.home.set", "Home successfully set!", user.getPlayer());
			return CastResult.SUCCESS;
		}
		JSONObject obj = null;
		try {
			obj = (JSONObject) new JSONParser().parse(user.getData(getDefaultName()));
		} catch (ParseException e) {
			e.printStackTrace();
			return CastResult.FAILURE;
		}
		double x = (Double) obj.get("x");
		double y = (Double) obj.get("y");
		double z = (Double) obj.get("z");
		float yaw = ((Double) obj.get("yaw")).floatValue();
		float pitch = ((Double) obj.get("pitch")).floatValue();
		String world = (String) obj.get("world");
		user.getPlayer().teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
		Language.sendMessage("spell.home.success", "Going home...", user.getPlayer());
		return CastResult.SUCCESS;
	}

}
