package com.minnymin.zephyrus.core.enchant.sword;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.minnymin.zephyrus.enchant.EnchantTarget;
import com.minnymin.zephyrus.enchant.SwordEnchant;

/**
 * Zephyrus - ToxicStrike.java
 * 
 * @author minnymin3
 * 
 */

public class ToxicStrike implements SwordEnchant {

	@Override
	public int getChance() {
		return 5;
	}

	@Override
	public int getCostPerLevel() {
		return 8;
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public String getName() {
		return "ToxicStrike";
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
		Entity en = event.getEntity();
		if (en instanceof LivingEntity) {
			((LivingEntity)en).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60 * level, level));
		}
	}

}
