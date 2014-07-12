package com.minnymin.zephyrus.core.spell.restoration;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - RepairSpell.java
 * 
 * @author minnymin3
 * 
 */

public class RepairSpell extends Spell implements Configurable {
	
	private int amount;

	public RepairSpell() {
		super("repair", "Fix up your tools", 100, 2, AspectList.newList(Aspect.TOOL, 40, Aspect.METAL, 20, Aspect.WEAPON, 20, Aspect.CONSTRUCT, 15), 2, SpellElement.NEUTREAL, SpellType.RESTORATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		if (player.getItemInHand() != null && player.getItemInHand().getType().getMaxDurability() != 0) {
			int repair = amount * power;
			ItemStack i = player.getItemInHand();
			if (i.getDurability() < i.getType().getMaxDurability() + repair) {
				player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability() - repair));
			} else {
				player.getItemInHand().setDurability(player.getItemInHand().getType().getMaxDurability());
			}
			Language.sendMessage("spell.repair.success", player);
			player.getWorld().playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 0.0F);
			return CastResult.SUCCESS;
		} else {
			Language.sendError("spell.repair.fail", player);
			return CastResult.FAILURE;
		}
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Amount", 30);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		amount = config.getInt("Amount");
	}

}
