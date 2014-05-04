package com.minnymin.zephyrus.core.enchant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.enchant.pickaxe.InstaMine;
import com.minnymin.zephyrus.core.enchant.sword.BattleAxe;
import com.minnymin.zephyrus.core.enchant.sword.LifeSuck;
import com.minnymin.zephyrus.core.enchant.sword.ToxicStrike;
import com.minnymin.zephyrus.core.util.reflection.ReflectionUtils;
import com.minnymin.zephyrus.enchant.ArmorEnchant;
import com.minnymin.zephyrus.enchant.BowEnchant;
import com.minnymin.zephyrus.enchant.Enchant;
import com.minnymin.zephyrus.enchant.EnchantManager;
import com.minnymin.zephyrus.enchant.PickaxeEnchant;
import com.minnymin.zephyrus.enchant.SwordEnchant;

/**
 * Zephyrus - SimpleEnchantmentManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleEnchantManager implements EnchantManager, Listener {

	private Map<Integer, Enchant> enchantmentMap;
	private int id;
	private boolean accepting;

	public SimpleEnchantManager() {
		enchantmentMap = new HashMap<Integer, Enchant>();
		id = 140;
	}

	@Override
	public Enchant getEnchant(int id) {
		return enchantmentMap.get(id);
	}

	@Override
	public int getEnchant(String name) {
		for (Entry<Integer, Enchant> entry : enchantmentMap.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(name)) {
				return entry.getKey();
			}
		}
		return 0;
	}

	@Override
	public void load() {
		Bukkit.getPluginManager().registerEvents(this, Zephyrus.getPlugin());
		setAccepting(true);
		registerEnchantment(new GlowEnchant(), 120);
		registerEnchantment(new InstaMine(), 123);
		registerEnchantment(new LifeSuck(), 124);
		registerEnchantment(new ToxicStrike(), 125);
		registerEnchantment(new BattleAxe(), 126);
		setAccepting(false);
		// TODO Add: Decapatator
	}

	@Override
	public void unload() {
	}

	@Override
	public int registerEnchantment(Enchant enchantment) {
		return registerEnchantment(enchantment, id++);
	}

	@SuppressWarnings("deprecation")
	private int registerEnchantment(Enchant enchantment, int id) {
		if (Enchantment.getById(id) == null) {
			enchantmentMap.put(id, enchantment);
			if (accepting) {
				Enchantment.registerEnchantment(new RegisteredEnchant(id, enchantment));
			} else {
				setAccepting(true);
				Enchantment.registerEnchantment(new RegisteredEnchant(id, enchantment));
				setAccepting(false);
			}
			if (enchantment instanceof Listener) {
				Bukkit.getPluginManager().registerEvents((Listener) enchantment, Zephyrus.getPlugin());
			}
		}
		return id;
	}

	private void setAccepting(boolean value) {
		ReflectionUtils.setStaticField(Enchantment.class, value, "acceptingNew");
		accepting = value;
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			for (ItemStack item : player.getInventory().getArmorContents()) {
				for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
					if (enchantmentMap.containsKey(entry.getKey().getId())) {
						Enchant ench = getEnchant(entry.getKey().getId());
						if (ench instanceof ArmorEnchant) {
							((ArmorEnchant) ench).onDamage(entry.getValue(), event);
						}
					}
				}
			}
		}
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBowShoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			for (Entry<Enchantment, Integer> entry : event.getBow().getEnchantments().entrySet()) {
				if (enchantmentMap.containsKey(entry.getKey().getId())) {
					Enchant ench = getEnchant(entry.getKey().getId());
					if (ench instanceof BowEnchant) {
						((BowEnchant) ench).onBowShoot(entry.getValue(), event);
					}
				}
			}
		}
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBlockBreak(BlockBreakEvent event) {
		ItemStack item = event.getPlayer().getItemInHand();
		if (item != null) {
			for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
				if (enchantmentMap.containsKey(entry.getKey().getId())) {
					Enchant ench = getEnchant(entry.getKey().getId());
					if (ench instanceof PickaxeEnchant) {
						((PickaxeEnchant) ench).onBlockBreak(entry.getValue(), event);
					}
				}
			}
		}
	}

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onAttack(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			ItemStack item = player.getItemInHand();
			if (item != null) {
				for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
					if (enchantmentMap.containsKey(entry.getKey().getId())) {
						Enchant ench = getEnchant(entry.getKey().getId());
						if (ench instanceof SwordEnchant) {
							((SwordEnchant) ench).onDamage(entry.getValue(), event);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEnchant(EnchantItemEvent event) {
		for (Enchant ench : enchantmentMap.values()) {
			if (ench.getTarget().isTypeCompatible(event.getItem())) {
				int chance = new Random().nextInt(ench.getChance());
				if (chance == 0) {
					int level = event.getExpLevelCost() / ench.getCostPerLevel();
					level = level <= ench.getMaxLevel() ? level : ench.getMaxLevel();
					event.getEnchantsToAdd().put(Enchantment.getById(120), level);
					ItemMeta meta = event.getItem().getItemMeta();
					List<String> lore = meta.getLore() != null ? meta.getLore() : new ArrayList<String>();
					lore.add(ChatColor.GRAY + ench.getName());
					meta.setLore(lore);
					event.getItem().setItemMeta(meta);
				}
			}
		}
	}

}
