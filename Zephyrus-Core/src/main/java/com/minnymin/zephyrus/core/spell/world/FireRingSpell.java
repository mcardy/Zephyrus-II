package com.minnymin.zephyrus.core.spell.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - FireRingSpell.java
 * 
 * @author minnymin3
 * 
 */

public class FireRingSpell extends Spell implements ConfigurableSpell {

	private int radius;

	public FireRingSpell() {
		super("firering", "Creates a ring of fire around you", 150, 10, AspectList.newList(Aspect.FIRE, 50,
				Aspect.DESTRUCTION, 25), 4, SpellElement.FIRE, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		final Block block = player.getLocation().getBlock();
		List<Block> blockList = new ArrayList<Block>();
		for (int x = -(radius); x <= radius; x++) {
			for (int y = -(radius); y <= radius; y++) {
				for (int z = -(radius); z <= radius; z++) {
					Block b = block.getRelative(x, y, z);
					if (!Zephyrus.getHookManager().canBuild(player, b)) {
						return CastResult.FAILURE;
					}
					double distance = b.getLocation().distance(block.getLocation());
					if (b.getRelative(BlockFace.DOWN).getType() != Material.AIR && b.getType() == Material.AIR
							&& distance > 2 && distance < radius) {
						blockList.add(b);
					}
				}
			}
		}
		for (Block b : blockList) {
			b.setType(Material.FIRE);
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Radius", 5);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		radius = config.getInt("Radius");
	}

}
