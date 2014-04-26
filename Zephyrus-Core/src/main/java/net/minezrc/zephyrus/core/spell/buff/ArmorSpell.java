package net.minezrc.zephyrus.core.spell.buff;

import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.state.StateList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

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
