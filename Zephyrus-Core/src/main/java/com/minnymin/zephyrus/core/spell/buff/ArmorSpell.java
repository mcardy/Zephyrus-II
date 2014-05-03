package com.minnymin.zephyrus.core.spell.buff;

import java.util.Map;


import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.state.StateList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Armour.java
 * 
 * @author minnymin3
 * 
 */

public class ArmorSpell extends Spell implements ConfigurableSpell {

	private int duration;

	public ArmorSpell() {
		super("armor", "Equips arcane armor that absorbs most damage", 300, 20, AspectList.newList(Aspect.VALUE, 100,
				Aspect.DEFENSE, 50, Aspect.MYSTICAL, 50), 6, SpellElement.ARCANE, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		int duration = this.duration * power;
		if (player.getInventory().getHelmet() == null && player.getInventory().getChestplate() == null
				&& player.getInventory().getLeggings() == null && player.getInventory().getBoots() == null) {
			user.addState(StateList.ARMOR, duration);
			Language.sendMessage("spell.armor.applied", ChatColor.GOLD + "Your skin is hardened with magic and gold",
					player);
			return CastResult.SUCCESS;
		} else if (user.isStateApplied(StateList.ARMOR)) {
			user.addState(StateList.ARMOR, duration);
			Language.sendMessage("spell.armor.applied", ChatColor.GOLD + "Your skin is hardened with magic and gold",
					player);
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.armor.fail", "You cannot be waring armour when casting this spell", player);
			return CastResult.FAILURE;
		}
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Duration", 120);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.duration = config.getInt("Duration");
	}

}
