package net.minezrc.zephyrus.core.spell.mobility;

import java.util.HashSet;
import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.spell.Bindable;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;

/**
 * Zephyrus - Bang.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Bang implements Spell, ConfigurableSpell {

	private int radius;

	@Override
	public String getName() {
		return "Bang";
	}

	@Override
	public String getDescription() {
		return "Creates a shockwave knocking back all entities";
	}

	@Override
	public int getManaCost() {
		return 200;
	}

	@Override
	public int getXpReward() {
		return 10;
	}

	@Override
	public AspectList getRecipe() {
		return AspectList.newList(Aspect.WIND, 8);
	}

	@Override
	public int getRequiredLevel() {
		return 5;
	}

	@Override
	public SpellElement getElement() {
		return SpellElement.AIR;
	}

	@Override
	public SpellType getType() {
		return SpellType.MOBILITY;
	}

	@Override
	public CastPriority getPriority() {
		return CastPriority.LOW;
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		@SuppressWarnings("deprecation")
		Location loc = user.getPlayer().getTargetBlock(null, 1000).getLocation();
		loc.setX(loc.getX() + 0.5);
		loc.setZ(loc.getZ() + 0.5);
		Location ploc = loc;
		ploc.setY(ploc.getY() + 2);
		for (Entity entity : getNearbyEntities(loc, radius)) {
			if (entity != user.getPlayer()) {
				entity.setVelocity(entity.getLocation().toVector().subtract(loc.toVector()).normalize().setY(0.4)
						.multiply(power));
			}
		}
		return CastResult.NORMAL_SUCCESS;
	}

	private Entity[] getNearbyEntities(Location l, int radius) {
		int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
		HashSet<Entity> radiusEntities = new HashSet<Entity>();
		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
			for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
				int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();
				for (Entity e : new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()) {
					if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock()) {
						radiusEntities.add(e);
					}
				}
			}
		}
		return radiusEntities.toArray(new Entity[radiusEntities.size()]);
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		return DataStructureUtils.createMap(DataStructureUtils.createList("radius"), DataStructureUtils
				.createList((Object) 6));
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("radius");
	}

}
