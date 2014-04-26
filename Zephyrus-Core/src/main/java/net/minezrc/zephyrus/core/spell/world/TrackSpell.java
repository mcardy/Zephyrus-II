package net.minezrc.zephyrus.core.spell.world;

import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * Zephyrus - Track.java
 * 
 * @author minnymin3
 * 
 */

public class TrackSpell extends Spell implements ConfigurableSpell {

	private int radius;

	public TrackSpell() {
		super("track", "Track animals in the wild", 50, 5, AspectList.newList(Aspect.BEAST, 30, Aspect.MOVEMENT, 15, Aspect.LIFE, 15), 1, SpellElement.NEUTREAL,
				SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		if (args.length == 0) {
			Language.sendError("spell.track.notarget", "Specify a target entity", user.getPlayer());
			return CastResult.FAILURE;
		}
		EntityType type;
		try {
			type = EntityType.valueOf(args[0].toUpperCase());
		} catch (Exception ex) {
			Language.sendError("spell.track.badtarget", "That is not an entity", user.getPlayer());
			return CastResult.FAILURE;
		}
		for (Entity entity : user.getPlayer().getNearbyEntities(radius, radius, radius)) {
			if (entity.getType() == type) {
				Location entityLoc = entity.getLocation();
				Location playerLoc = user.getPlayer().getLocation();
				double deltaX = playerLoc.getX() - entityLoc.getX();
				double deltaY = playerLoc.getY() - entityLoc.getY();
				double deltaZ = playerLoc.getZ() - entityLoc.getZ();
				Language.sendMessage("spell.track.title", ChatColor.GRAY + "There is a [ENTITY] nearby",
						user.getPlayer(), "[ENTITY]", type.toString().toLowerCase());
				String locX = ChatColor.BLUE
						+ (deltaX > 0 ? (int) deltaX + " blocks west" : (int) -deltaX + " blocks east");
				String locY = ChatColor.RED
						+ (deltaY > 0 ? (int) deltaY + " blocks down" : (int) -deltaY + " blocks up");
				String locZ = ChatColor.GREEN
						+ (deltaZ > 0 ? (int) deltaZ + " blocks north" : (int) -deltaZ + " blocks south");
				Language.sendMessage("spell.track.location", ChatColor.GRAY + "Travel [X], [Y], [Z]", user.getPlayer(),
						"[X]", locX, "[Y]", locY, "[Z]", locZ);
				return CastResult.SUCCESS;
			}
		}
		Language.sendError("spell.track.none", "There are no entities of that type nearby", user.getPlayer());
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Radius", 100);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("Radius");
	}

}
