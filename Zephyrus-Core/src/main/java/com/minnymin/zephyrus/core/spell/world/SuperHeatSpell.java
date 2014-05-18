package com.minnymin.zephyrus.core.spell.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - SuperHeatSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK, range = 7)
public class SuperHeatSpell extends Spell implements ConfigurableSpell {

	Map<Integer, Integer> idMap;

	public SuperHeatSpell() {
		super("superheat", "Superheat the block you are looking at. Ouch thats hot...", 50, 5, AspectList.newList(), 5,
				SpellElement.FIRE, SpellType.WORLD);
	}

	@Override
	@SuppressWarnings("deprecation")
	public CastResult onCast(User user, int power, String[] args) {
		Block block = (Block) user.getTarget(this).getTarget();
		Material material = block.getType();
		if (idMap.containsKey(material.getId())) {
			int change = idMap.get(block.getTypeId());
			Location loc = block.getLocation().add(0.5, 0.5, 0.5);
			if (change < 256) {
				block.setType(Material.getMaterial(change));
			} else {
				block.setType(Material.AIR);
				loc.getWorld().dropItem(loc, new ItemStack(Material.getMaterial(change)));
			}
			ParticleEffects.sendParticle(Particle.FIRE, loc, 0.6F, 0.6F, 0.6F, 0, 20);
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.superheat.fail", "That block can't be superheated", user.getPlayer());
			return CastResult.FAILURE;
		}
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add("4-1");
		list.add("15-265");
		list.add("14-266");
		list.add("12-20");
		map.put("Ids", list);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		idMap = new HashMap<Integer, Integer>();
		List<String> ids = config.getStringList("Ids");
		for (String s : ids) {
			String[] id = s.split("-");
			try {
				idMap.put(Integer.parseInt(id[0]), Integer.parseInt(id[1]));
			} catch (Exception ex) {
				Zephyrus.getPlugin().getLogger().warning("Error in SuperHeat configuration: Check line '" + s + "'");
			}
		}
	}

}
