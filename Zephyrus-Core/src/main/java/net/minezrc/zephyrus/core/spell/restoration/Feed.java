package net.minezrc.zephyrus.core.spell.restoration;

import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastPriority;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellRecipe;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - Feed.java
 * 
 * @author minnymin3
 * 
 */

public class Feed implements Spell {

	@Override
	public String getName() {
		return "feed";
	}

	@Override
	public String getDescription() {
		return "Makes you slightly less hungry";
	}

	@Override
	public int getRequiredLevel() {
		return 1;
	}

	@Override
	public int getManaCost() {
		return 25;
	}

	@Override
	public int getXpReward() {
		return 2;
	}

	@Override
	public SpellRecipe getRecipe() {
		return new SpellRecipe(new ItemStack(Material.COOKED_BEEF), new ItemStack(Material.COOKED_CHICKEN),
				new ItemStack(Material.COOKED_FISH));
	}

	@Override
	public SpellElement getElement() {
		return SpellElement.PASSIVE;
	}

	@Override
	public SpellType getType() {
		return SpellType.RESTORATION;
	}

	@Override
	public CastPriority getPriority() {
		return CastPriority.LOW;
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
	}

	@Override
	public CastResult onCast(User user, int power, Spell combo, String[] args) {
		Player player = user.getPlayer();
		if (player.getFoodLevel() < 20) {
			player.setFoodLevel(player.getFoodLevel() + 1);
			return CastResult.NORMAL_SUCCESS;
		} else {
			Language.sendError("spell.feed.full", "You are already full!", player);
			return CastResult.NORMAL_FAIL;
		}
	}

}
