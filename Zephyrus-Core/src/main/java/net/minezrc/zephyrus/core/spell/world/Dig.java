package net.minezrc.zephyrus.core.spell.world;

import java.util.List;
import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.spell.annotation.Targeted;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Zephyrus - Dig.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK, range = 20)
public class Dig extends Spell implements ConfigurableSpell {

	private List<String> blacklist;

	public Dig() {
		super("dig", "Diggy diggy hole", 5, 1, AspectList.newList(Aspect.EARTH, 40, Aspect.STONE, 20, Aspect.TOOL, 20),
				2, SpellElement.EARTH, SpellType.WORLD);
	}

	@Override
	@SuppressWarnings("deprecation")
	public CastResult onCast(User user, int power, String[] args) {
		Block block = user.getTarget(this).getBlock();
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
