package com.minnymin.zephyrus.core.enchant;


import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.enchant.Enchant;

/**
 * Zephyrus - RegisteredEnchantment.java
 * 
 * @author minnymin3
 * 
 */

public class RegisteredEnchant extends Enchantment {

	private Enchant enchant;
	
	public RegisteredEnchant(int id, Enchant enchant) {
		super(id);
		this.enchant = enchant;
	}

	@Override
	public boolean canEnchantItem(ItemStack item) {
		return enchant.getTarget().isTypeCompatible(item);
	}

	@Override
	public boolean conflictsWith(Enchantment other) {
		return enchant.isEnchantmentIncompatible(other);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public int getMaxLevel() {
		return enchant.getMaxLevel();
	}

	@Override
	public String getName() {
		return enchant.getName();
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

}
