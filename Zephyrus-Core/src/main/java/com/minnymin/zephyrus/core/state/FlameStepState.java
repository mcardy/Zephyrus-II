package com.minnymin.zephyrus.core.state;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - FlameStepState.java
 * 
 * @author minnymin3
 * 
 */

public class FlameStepState implements State {
	
	private int radius;

	public FlameStepState() {
		radius = Zephyrus.getSpellConfig().getConfig().getInt("flamestep.Radius");
	}

	@Override
	public int getTickTime() {
		return 2;
	}

	@Override
	public void onApplied(User user) {
		Language.sendMessage("spell.flamestep.applied", user.getPlayer());
	}

	@Override
	public void onRemoved(User user) {
		Language.sendMessage("spell.flamestep.removed", user.getPlayer());
		user.getPlayer().setFireTicks(0);
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
		player.setFireTicks(4);
	}

	@Override
	public void onWarning(User user) {
		Language.sendMessage("spell.flamestep.warning", user.getPlayer());
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (Zephyrus.getUser(event.getPlayer()).isStateApplied(this)) {
			final Block block = event.getPlayer().getLocation().getBlock();
			for (int x = -(radius); x <= radius; x++) {
				for (int y = -(radius); y <= radius; y++) {
					for (int z = -(radius); z <= radius; z++) {
						Block b = block.getRelative(x, y, z);
						if (b.getType() == Material.SAND || b.getType() == Material.COBBLESTONE) {
							if (Zephyrus.getHookManager().canBuild(event.getPlayer(), block)) {
								if (b.getType() == Material.SAND) {
									b.setType(Material.GLASS);
								}
								if (b.getType() == Material.COBBLESTONE) {
									b.setType(Material.STONE);
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player && e.getCause() == DamageCause.FIRE_TICK) {
			if (Zephyrus.getUser((Player) e.getEntity()).isStateApplied(this)) {

			}
		}
	}

}
