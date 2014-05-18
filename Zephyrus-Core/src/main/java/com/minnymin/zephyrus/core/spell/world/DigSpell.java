package com.minnymin.zephyrus.core.spell.world;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.target.Targeted;
import com.minnymin.zephyrus.user.target.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Dig.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK, range = 20)
public class DigSpell extends Spell implements ConfigurableSpell {

	private List<String> blacklist;

	public DigSpell() {
		super("dig", "Diggy diggy hole", 5, 1, AspectList.newList(Aspect.EARTH, 40, Aspect.STONE, 20, Aspect.TOOL, 20),
				2, SpellElement.EARTH, SpellType.WORLD);
	}

	@Override
	@SuppressWarnings("deprecation")
	public CastResult onCast(User user, int power, String[] args) {
		Block block = (Block) user.getTarget(this).getTarget();
		if (blacklist.contains(block.getTypeId()) || block.getType() == Material.AIR) {
			Language.sendError("spell.dig.blacklist", "You cannot break that block", user.getPlayer());
			return CastResult.FAILURE;
		}
		block.breakNaturally();
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Blacklist", DataStructureUtils.createList(7));
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		blacklist = config.getStringList("Blacklist");
	}

}
