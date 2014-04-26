package net.minezrc.zephyrus.core.state;

import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.state.State;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

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
		Language.sendMessage("spell.shield.applied", "A shield of magical energy surrounds you", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.shield.applied", "Your magic shield dissapates", user.getPlayer());
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
				((LivingEntity) e).damage(2);
			}
		}
	}

	@Override
	public void onWarning(User user) {
	}
	
}
