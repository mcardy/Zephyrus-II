package com.minnymin.zephyrus.core.enchant.sword;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.minnymin.zephyrus.enchant.EnchantTarget;
import com.minnymin.zephyrus.enchant.SwordEnchant;

/**
 * Zephyrus - LifeSuck.java
 * 
 * @author minnymin3
 * 
 */

public class LifeSuck implements SwordEnchant {

	@Override
	public int getChance() {
		return 3;
	}

	@Override
	public int getCostPerLevel() {
		return 4;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public String getName() {
		return "LifeSuck";
	}

	@Override
	public EnchantTarget getTarget() {
		return EnchantTarget.SWORD;
	}

	@Override
	public boolean isEnchantmentIncompatible(Enchantment enchantment) {
		return false;
	}

	@Override
	public void onDamage(int level, EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity) event.getDamager();
			if (entity.getHealth() + level < entity.getMaxHealth()) {
				entity.setHealth(entity.getHealth() + level);
			} else {
				entity.setHealth(entity.getMaxHealth());
			}
		}
	}

}
