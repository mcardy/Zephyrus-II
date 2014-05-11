package com.minnymin.zephyrus.core.state;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ZephyrState.java
 * 
 * @author minnymin3
 * 
 */

public class ZephyrState implements State {

	private boolean blockAll;
	
	public ZephyrState() {
		blockAll = Zephyrus.getSpellConfig().getConfig().getBoolean("BlockAll");
	}
	
	@Override
	public int getTickTime() {
		return 2;
	}

	@Override
	public void onApplied(User user) {
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.zephyr.removed", "The air becomes still around you", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
	}

	@Override
	public void onTick(User user) {
		Player player = user.getPlayer();
		Location loc = player.getLocation();
		loc.setY(player.getLocation().getY() + 1);
		ParticleEffects.sendParticle(Particle.CLOUD, loc, (float) 0.5, (float) 0.5, (float) 0.5, 0, 10);
		for (Entity e : player.getNearbyEntities(3, 3, 3)) {
			if (this.blockAll) {
				Vector unitVector = e.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
				unitVector.setY(0.4);
				e.setVelocity(unitVector.multiply(0.4));
			} else if (e instanceof LivingEntity) {
				Vector unitVector = e.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
				unitVector.setY(0.4);
				e.setVelocity(unitVector.multiply(0.4));
			}
		}
	}

	@Override
	public void onWarning(User user) {
		Language.sendMessage("spell.zephyr.warning", "The air begins to slow down around you", user.getPlayer());
	}

}
