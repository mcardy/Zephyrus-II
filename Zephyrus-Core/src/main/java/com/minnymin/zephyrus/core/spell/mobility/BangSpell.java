package com.minnymin.zephyrus.core.spell.mobility;

import java.util.HashSet;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.SpellAttributes.TargetType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.Targeted;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Bang.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK)
public class BangSpell extends Spell implements ConfigurableSpell {

	private double[][] particles;
	private int radius;

	public BangSpell() {
		super("bang", "Creates a shockwave knocking back all entities", 200, 10, AspectList.newList(Aspect.WIND, 60,
				Aspect.ENERGY, 30, Aspect.EARTH, 30), 5, SpellElement.AIR, SpellType.MOBILITY);
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
		Location loc = user.getTarget(this.getDefaultName()).getBlock().getLocation();
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
		return DataStructureUtils.createMap(DataStructureUtils.createList("radius"),
				DataStructureUtils.createList((Object) 6));
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("radius");
	}

}
