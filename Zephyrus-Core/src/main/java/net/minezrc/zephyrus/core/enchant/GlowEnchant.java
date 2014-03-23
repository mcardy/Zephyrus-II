package net.minezrc.zephyrus.core.enchant;

import org.bukkit.enchantments.Enchantment;

import net.minezrc.zephyrus.enchant.Enchant;
import net.minezrc.zephyrus.enchant.EnchantTarget;

/**
 * Zephyrus - GlowEnchant.java
 * 
 * @author minnymin3
 * 
 */

public class GlowEnchant implements Enchant {

	@Override
	public int getCostPerLevel() {
		return 0;
	}

	@Override
	public int getChance() {
		return 0;
	}

	@Override
	public int getMaxLevel() {
		return 0;
	}

	@Override
	public String getName() {
		return "glow";
	}

	@Override
	public EnchantTarget getTarget() {
		return EnchantTarget.NONE;
	}

	@Override
	public boolean isEnchantmentIncompatible(Enchantment enchantment) {
		return false;
	}

}
