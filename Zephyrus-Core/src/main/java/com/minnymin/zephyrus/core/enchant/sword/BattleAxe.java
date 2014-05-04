package com.minnymin.zephyrus.core.enchant.sword;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.minnymin.zephyrus.enchant.EnchantTarget;
import com.minnymin.zephyrus.enchant.SwordEnchant;

/**
 * Zephyrus - BattleAxe.java
 * 
 * @author minnymin3
 * 
 */

public class BattleAxe implements SwordEnchant {

	@Override
	public int getChance() {
		return 4;
	}

	@Override
	public int getCostPerLevel() {
		return 5;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "BattleAxe";
	}

	@Override
	public EnchantTarget getTarget() {
		return EnchantTarget.AXE;
	}

	@Override
	public boolean isEnchantmentIncompatible(Enchantment enchantment) {
		return false;
	}

	@Override
	public void onDamage(int level, EntityDamageByEntityEvent event) {
		event.setDamage(event.getDamage() + level);
	}

}
