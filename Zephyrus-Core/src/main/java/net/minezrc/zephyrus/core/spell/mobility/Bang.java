package net.minezrc.zephyrus.core.spell.mobility;

import java.util.HashSet;
import java.util.Map;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.spell.Bindable;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Zephyrus - Bang.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Bang implements Spell, ConfigurableSpell {

	private double[][] particles;
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
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		particles = new double[90][2];
		for (int i = 0; i < 90; i++) {
			particles[i][0] = 2 * Math.cos(i * 4);
			particles[i][1] = 2 * Math.sin(i * 4);
		}
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		@SuppressWarnings("deprecation")
		Location loc = user.getPlayer().getTargetBlock(null, 1000).getLocation().add(-0.5, 1, -0.5);
		for (Entity entity : getNearbyEntities(loc, radius)) {
			if (entity != user.getPlayer()) {
				entity.setVelocity(entity.getLocation().toVector().subtract(loc.toVector()).normalize().setY(0.4)
						.multiply(power * 2));
			}
		}
		playParticleEffect(loc);
		return CastResult.SUCCESS;
	}

	private void playParticleEffect(final Location loc) {
		for (int i = 0; i < particles.length; i++) {
			final double[] particle = particles[i];
			Bukkit.getScheduler().runTaskLater(Zephyrus.getPlugin(), new BukkitRunnable() {
				@Override
				public void run() {
					Location pos = loc.clone().add(particle[0], 0, particle[1]);
					ParticleEffects.sendParticle(Particle.INSTANT_SPELL, pos, 1);
				}
			}, i / 4);
		}
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
