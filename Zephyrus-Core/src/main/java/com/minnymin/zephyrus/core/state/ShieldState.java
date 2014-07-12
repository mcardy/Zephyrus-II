package com.minnymin.zephyrus.core.state;


import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ShieldState.java
 * 
 * @author minnymin3
 * 
 */

public class ShieldState implements State {

	@Override
	public int getTickTime() {
		return 10;
	}

	@Override
	public void onApplied(User user) {
		Language.sendMessage("spell.shield.applied", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.shield.applied", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
	}

	@Override
	public void onTick(User user) {
		Player player = user.getPlayer();
		Location loc = player.getLocation();
		loc.setY(player.getLocation().getY() + 1);
		ParticleEffects.sendParticle(Particle.BLUE_SPARKLE, loc, 0.5F, 1, 0.5F, 100, 12);
		for (Entity e : player.getNearbyEntities(2, 2, 2)) {
			if (e instanceof LivingEntity) {
				if (!Zephyrus.getHookManager().canTarget(player, (LivingEntity) e, false)) {
					continue;
				}
				((LivingEntity) e).damage(2);
			}
		}
	}

	@Override
	public void onWarning(User user) {
	}
	
}
