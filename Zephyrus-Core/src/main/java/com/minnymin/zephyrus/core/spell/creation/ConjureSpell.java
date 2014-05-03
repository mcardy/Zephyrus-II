package com.minnymin.zephyrus.core.spell.creation;


import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Conjure.java
 * 
 * @author minnymin3
 * 
 */

public class ConjureSpell extends Spell {

	public ConjureSpell() {
		super("conjure", "Conjures items out of mana", 0, 10, AspectList.newList(Aspect.CONSTRUCT, 100,
				Aspect.MYSTICAL, 50, Aspect.FIRE, 25, Aspect.WATER, 25, Aspect.WIND, 25, Aspect.EARTH, 25,
				Aspect.STONE, 25), 8, SpellElement.ARCANE, SpellType.CREATION);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		if (args.length == 0) {
			Language.sendError("spell.conjure.noitem",
					"Specify an item to conjure! /cast conjure <id>:<data> [amount]", user.getPlayer());
			return CastResult.FAILURE;
		}
		int id = 0;
		byte data = 0;
		int amount = 1;
		if (args[1].contains("\\:")) {
			String[] s = args[0].split("\\:");
			try {
				id = Integer.parseInt(s[0]);
				data = Byte.parseByte(args[1]);
			} catch (NumberFormatException ex) {
				Language.sendError("spell.conjure.badid", "Invalid item!", user.getPlayer());
				return CastResult.FAILURE;
			}
		} else {
			try {
				id = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex) {
				Language.sendError("spell.conjure.badid", "Invalid item!", user.getPlayer());
				return CastResult.FAILURE;
			}
		}
		if (args.length > 2) {
			try {
				amount = Integer.parseInt(args[1]);
			} catch (NumberFormatException ex) {
				Language.sendError("spell.conjure.badamount", "Invalid amount!", user.getPlayer());
				return CastResult.FAILURE;
			}
		}
		int value = getValue(id);
		if (value == -1) {
			Language.sendError("spell.conjure.baditem", "You cannot conjure that item!", user.getPlayer());
			return CastResult.FAILURE;
		}
		int manaCost = value * amount;
		if (user.getMana() < getValue(id) * amount) {
			Language.sendError("spell.conjure.mana", "You do not have enough mana to conjure that item [MANA]",
					user.getPlayer(), "[SPELL]", this.getName(), "[MANA]", ChatColor.RED + "" + user.getMana()
							+ ChatColor.GRAY + "/" + ChatColor.GREEN + manaCost);
			return CastResult.FAILURE;
		}
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.getMaterial(id), amount, data);
		user.getPlayer().getInventory().addItem(item);
		String itemName = WordUtils.capitalizeFully(item.getType().toString().replace("_", " "));
		Language.sendMessage("spell.conjure.complete", "Successfully conjured [AMOUNT] [ITEM]", user.getPlayer(),
				"[AMOUNT]", "" + amount, "[ITEM]", itemName);
		user.drainMana(manaCost);
		return CastResult.SUCCESS;
	}

	public int getValue(int id) {
		switch (id) {
		case 1:
			return 8;
		case 2:
			return 32;
		case 3:
			return 1;
		case 4:
			return 1;
		case 5:
			return 8;
		case 6:
			return 8;
		case 12:
			return 2;
		case 13:
			return 2;
		case 14:
			return 2048;
		case 15:
			return 256;
		case 16:
			return 128;
		case 17:
			return 64;
		case 18:
			return 4;
		case 19:
			return 32;
		case 20:
			return 8;
		case 21:
			return 2048;
		case 24:
			return 4;
		case 27:
			return 1024;
		case 28:
			return 320;
		case 29:
			return 512;
		case 30:
			return 16;
		case 33:
			return 256;
		case 35:
			return 64;
		case 45:
			return 64;
		case 47:
			return 256;
		case 48:
			return 128;
		case 49:
			return 128;
		case 50:
			return 24;
		case 73:
			return 192;
		case 78:
			return 32;
		case 79:
			return 32;
		case 81:
			return 32;
		case 82:
			return 64;
		case 86:
			return 64;
		case 98:
			return 8;
		case 102:
			return 128;
		case 112:
			return 64;
		case 121:
			return 32;
		case 129:
			return 4096;
		case 155:
			return 32;
		}
		return -1;
	}

}
