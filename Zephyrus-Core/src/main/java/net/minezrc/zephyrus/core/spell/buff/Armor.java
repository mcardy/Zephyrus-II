package net.minezrc.zephyrus.core.spell.buff;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.state.StateList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Armour.java
 * 
 * @author minnymin3
 * 
 */

public class Armor implements Spell {

	@Override
	public String getDescription() {
		return "Equips arcane armour that protects you from most damage";
	}

	@Override
	public SpellElement getElement() {
		return SpellElement.ARCANE;
	}

	@Override
	public int getManaCost() {
		return 300;
	}

	@Override
	public String getName() {
		return "armor";
	}

	@Override
	public CastPriority getPriority() {
		return CastPriority.HIGH;
	}

	@Override
	public AspectList getRecipe() {
		return AspectList.newList().setAspectTypes(Aspect.ARMOR, Aspect.VALUE, Aspect.MAGIC).setAspectValues(24, 48, 4);
	}

	@Override
	public int getRequiredLevel() {
		return 8;
	}

	@Override
	public SpellType getType() {
		return SpellType.BUFF;
	}

	@Override
	public int getXpReward() {
		return 20;
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		if (player.getInventory().getHelmet() == null && player.getInventory().getChestplate() == null
				&& player.getInventory().getLeggings() == null && player.getInventory().getBoots() == null) {
			user.addState(StateList.ARMOR, 120);
			Language.sendMessage("spell.armor.applied", ChatColor.GOLD + "Your skin is hardened with magic and gold", player);
			return CastResult.NORMAL_SUCCESS;
		} else if (user.isStateApplied(StateList.ARMOR)) {
			user.addState(StateList.ARMOR, 120);
			Language.sendMessage("spell.armor.applied", ChatColor.GOLD + "Your skin is hardened with magic and gold", player);
			return CastResult.NORMAL_SUCCESS;
		} else {
			Language.sendError("spell.armor.fail", "You cannot be waring armour when casting this spell", player);
			return CastResult.NORMAL_FAIL;
		}
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
	}

}
