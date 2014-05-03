package com.minnymin.zephyrus.core.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.event.UserBindSpellEvent;
import com.minnymin.zephyrus.item.ActionItem;
import com.minnymin.zephyrus.item.ItemRecipe;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - SpellBook.java
 * 
 * @author minnymin3
 * 
 */

public class SpellBook implements ActionItem {

	@Override
	public String getName() {
		return ChatColor.GOLD + "SpellBook";
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("pgp", "pbp", "pgp");
		recipe.setIngredient('p', Material.PAPER);
		recipe.setIngredient('g', Material.GLOWSTONE_DUST);
		recipe.setIngredient('b', Material.BOOK);
		return recipe;
	}

	@Override
	public Material getMaterial() {
		return Material.BOOK;
	}

	@Override
	public List<String> getLore() {
		return DataStructureUtils.createList(ChatColor.GRAY + "Shift right/left click to change spells", ChatColor.GRAY
				+ "Right click to cast spell", ChatColor.GRAY + "Bound Spell: none");
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		Map<Enchantment, Integer> map = new HashMap<Enchantment, Integer>();
		map.put(Enchantment.getByName("glow"), 1);
		return map;
	}

	@Override
	public int getCraftingLevel() {
		return 0;
	}

	public String getBoundName(Spell spell) {
		return getName() + ChatColor.GRAY + " - " + ChatColor.GOLD + spell.getName();
	}

	public List<String> getBoundLore(Spell spell) {
		return DataStructureUtils.createList(ChatColor.GRAY + "Shift right/left click to change spells", ChatColor.GRAY
				+ "Right click to cast spell", ChatColor.GRAY + "Bound Spell: " + spell.getName());
	}

	public String getSpell(ItemStack stack) {
		if (stack.getItemMeta().hasLore() && stack.getItemMeta().getLore().get(2).contains("Bound Spell: ")) {
			return stack.getItemMeta().getLore().get(2).replace(ChatColor.GRAY + "Bound Spell: ", "");
		} else {
			return null;
		}
	}

	@Override
	public Set<Action> getActions() {
		return DataStructureUtils
				.createSet(Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK);
	}

	@Override
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		User user = Zephyrus.getUser(player);
		ItemStack stack = player.getItemInHand();
		String bound = getSpell(stack);
		if (player.isSneaking()) {
			List<String> spells = new ArrayList<String>();
			for (String s : user.getLearnedSpells()) {
				if (Zephyrus.getSpell(s).getClass().isAnnotationPresent(Bindable.class)) {
					spells.add(s);
				}
			}
			if (bound == null) {
				bind(player, stack, Zephyrus.getSpell(spells.get(0)));
			} else {
				int index = spells.indexOf(bound);
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					index++;
					if (index == spells.size()) {
						index = 0;
					}
					Spell spell = Zephyrus.getSpell(spells.get(index));
					bind(player, stack, spell);
				} else {
					index--;
					if (index < 0) {
						index = spells.size() - 1;
					}
					Spell spell = Zephyrus.getSpell(spells.get(index));
					bind(player, stack, spell);
				}
			}
		} else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (user.isCastingSpell()) {
				user.stopCasting();
			} else if (bound != null) {
				Spell spell = Zephyrus.getSpell(bound);
				user.castSpell(spell, 1, null);
			} else {
				Language.sendError("item.wand.nobound", "There is no spell bound to this wand! Bind one with /bind <spell>", player);
			}
		}
	}

	private void bind(Player player, ItemStack item, Spell spell) {
		UserBindSpellEvent event = new UserBindSpellEvent(player, spell, null);
		Bukkit.getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(getBoundName(spell));
			meta.setLore(getBoundLore(spell));
			item.setItemMeta(meta);
			Language.sendMessage("command.bind.complete", "Successfully bound [SPELL] to your wand", player, "[SPELL]", ChatColor.GOLD
					+ spell.getName() + ChatColor.WHITE);
			player.setItemInHand(item);
		}
	}

}
