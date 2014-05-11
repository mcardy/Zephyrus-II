package com.minnymin.zephyrus.core.state;

import org.bukkit.Location;
import org.bukkit.Sound;
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
 * Zephyrus - FireShieldState.java
 * 
 * @author minnymin3
 * 
 */

public class FireShieldState implements State {

	@Override
	public int getTickTime() {
		return 2;
	}

	@Override
	public void onApplied(User user) {
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.fireshield.removed", "Your shield fades to cold air", user.getPlayer());
	}

	@Override
	public void onStartup(User user) {
	}

	@Override
	public void onTick(User user) {
		Player player = user.getPlayer();
		Location loc = player.getLocation();
		loc.setY(player.getLocation().getY() + 1);
		ParticleEffects.sendParticle(Particle.REDSTONE_DUST, loc, 1, 1, 1, 0, 5);
		player.getWorld().playSound(loc, Sound.FIRE, 1.0F, 0.4F);
		for (Entity e : player.getNearbyEntities(2, 2, 2)) {
			if (e instanceof LivingEntity) {
				if (!Zephyrus.getHookManager().canTarget(player, (LivingEntity) e, false)) {
					continue;
				}
				((LivingEntity) e).setFireTicks(10);
			}
		}
	}

	@Override
	public void onWarning(User user) {
		Language.sendMessage("spell.fireshield.warning", "Your shield begins to fade", user.getPlayer());
	}

}
