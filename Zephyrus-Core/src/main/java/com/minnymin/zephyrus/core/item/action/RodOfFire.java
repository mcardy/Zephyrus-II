package com.minnymin.zephyrus.core.item.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.item.ActionItem;
import com.minnymin.zephyrus.item.ItemRecipe;
import com.minnymin.zephyrus.item.LevelledItem;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.targeted.Targeted;

/**
 * Zephyrus - RodOfFire.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 100)
public class RodOfFire extends ActionItem implements LevelledItem {

	public RodOfFire() {
		super(ChatColor.RED + "Rod of Fire", 1, Material.BLAZE_ROD, DataStructureUtils.createList(ChatColor.GRAY
				+ "Level " + ChatColor.GOLD + "[LEVEL]"));
	}

	@Override
	public int getLevel(List<String> lore) {
		return Integer.valueOf(lore.get(0).split(ChatColor.GOLD.toString())[1]);
	}

	@Override
	public List<String> getLevelledLore(int level) {
		List<String> lore = new ArrayList<String>();
		for (String s : getLore()) {
			lore.add(s.replace("[LEVEL]", String.valueOf(level)));
		}
		return lore;
	}

	@Override
	public Material getMaterialCost() {
		return Material.BLAZE_POWDER;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public Set<Action> getActions() {
		return DataStructureUtils.createSet(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK);
	}

	@Override
	public void onInteract(PlayerInteractEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		User user = Zephyrus.getUser(player);
		Block block = (Block) user.getTarget(this).getTarget();
		int delay = user.getDelay(getInternalName());
		if (delay > 0) {
			Language.sendError("item.delay", player,
					"[SECONDS]", delay + "");
			return;
		}
		if (Zephyrus.getHookManager().canBuild(player, block)) {
			if (getLevel(player.getItemInHand().getItemMeta().getLore()) > 5) {
				player.launchProjectile(Fireball.class);
			} else {
				player.launchProjectile(SmallFireball.class);
			}
			user.setDelay(getName(), levelToDelay(getLevel(player.getItemInHand().getItemMeta().getLore())));
			return;
		}
		Language.sendError("item.rodoffire.failure", player);
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("BCB", "BAB", "BBB");
		recipe.setIngredient('C', Material.DIAMOND);
		recipe.setIngredient('B', Material.BLAZE_POWDER);
		recipe.setIngredient('A', Material.BLAZE_ROD);
		return recipe;
	}
	
	private int levelToDelay(int level) {
		switch (level) {
		case 1:
			return 20;
		case 2:
			return 15;
		case 3:
			return 10;
		case 4:
			return 5;
		case 5:
			return 2;
		}
		return 0;
	}

}
