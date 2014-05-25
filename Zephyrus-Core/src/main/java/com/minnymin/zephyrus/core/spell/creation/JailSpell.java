package com.minnymin.zephyrus.core.spell.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - JailSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY, range = 30, friendly = false)
public class JailSpell extends Spell implements Listener, Configurable {

	private int duration;
	private Map<Location, Set<BlockState>> jails;

	public JailSpell() {
		super("jail", "Lock up 'em bandits in a solid steel jail", 500, 50, AspectList.newList(Aspect.METAL, 150,
				Aspect.MACHINE, 75, Aspect.DEFENSE, 75), 8, SpellElement.NEUTREAL, SpellType.CREATION);
		jails = new HashMap<Location, Set<BlockState>>();
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		Entity target = (Entity) user.getTarget(this).getTarget();
		Location loc = target.getLocation();
		List<Block> bars = new ArrayList<Block>();
		List<Block> walls = new ArrayList<Block>();
		Set<BlockState> states = new HashSet<BlockState>();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 3; y++) {
				for (int z = -1; z < 2; z++) {
					if (!(x == 0 && z == 0 && y < 2 && y > -1)) {
						Block block = loc.clone().add(x, y, z).getBlock();
						if (!Zephyrus.getHookManager().canBuild(player, block)) {
							return CastResult.FAILURE;
						}
						states.add(block.getState());
						if (y == -1 || y == 2) {
							walls.add(block);
						} else {
							bars.add(block);
						}
					}
				}
			}
		}
		for (Block b : bars) {
			b.setType(Material.IRON_FENCE);
			b.setMetadata("jailblock", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		}
		for (Block b : walls) {
			b.setType(Material.IRON_BLOCK);
			b.setMetadata("jailblock", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		}
		jails.put(loc, states);
		new JailReset(loc, states).runTaskLater(Zephyrus.getPlugin(), duration * 20 * power);
		player.getWorld().playSound(loc, Sound.ANVIL_LAND, 1.0F, 0.0F);
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Duration", 30);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		duration = config.getInt("Duration");
	}

	@Override
	public void onDisable() {
		for (Set<BlockState> states : jails.values()) {
			for (BlockState state : states) {
				state.update(true);
			}
		}
	}

	@EventHandler
	public void onBreakJail(BlockBreakEvent event) {
		if (event.getBlock().hasMetadata("jailblock")) {
			event.setCancelled(true);
			Language.sendError("spell.jail.break", "You cannot break jail blocks!", event.getPlayer());
		}
	}

	@EventHandler
	public void onPistonMove(BlockPistonRetractEvent event) {
		if (event.isSticky() && event.getRetractLocation().getBlock().hasMetadata("jailblock")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPistonExtend(BlockPistonExtendEvent event) {
		for (Block block : event.getBlocks()) {
			if (block.hasMetadata("jailblock")) {
				event.setCancelled(true);
				return;
			}
		}
	}

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		List<Block> toRemove = new ArrayList<Block>();
		for (Block block : event.blockList()) {
			if (block.hasMetadata("jailblock")) {
				toRemove.add(block);
			}
		}
		for (Block block : toRemove) {
			event.blockList().remove(block);
		}
	}

	private class JailReset extends BukkitRunnable {

		Set<BlockState> states;
		Location loc;

		JailReset(Location loc, Set<BlockState> states) {
			this.states = states;
			this.loc = loc;
		}

		@Override
		public void run() {
			for (BlockState state : this.states) {
				state.update(true);
				state.getWorld().playEffect(state.getLocation(), Effect.STEP_SOUND, Material.IRON_BLOCK);
			}
			jails.remove(loc);
		}
	}

}
