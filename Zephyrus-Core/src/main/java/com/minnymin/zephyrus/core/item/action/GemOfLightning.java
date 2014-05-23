package com.minnymin.zephyrus.core.item.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
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
 * Zephyrus - GemOfLightning.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 50)
public class GemOfLightning extends ActionItem implements LevelledItem {

	public GemOfLightning() {
		super(ChatColor.AQUA + "Gem of Lightning", 1, Material.EMERALD, DataStructureUtils
				.createList(ChatColor.GRAY + "Level " + ChatColor.GOLD + "[LEVEL]"));
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")),
				DataStructureUtils.createList(1));
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
			Language.sendError("item.delay", "You still need to wait [SECONDS] seconds to use this item", player,
					"[SECONDS]", delay + "");
			return;
		}
		if (Zephyrus.getHookManager().canBuild(player, block)) {
			block.getWorld().strikeLightning(block.getLocation());
			user.setDelay(getName(), levelToDelay(getLevel(player.getItemInHand().getItemMeta().getLore())));
			return;
		}
		Language.sendError("item.gemoflightning.failure", "You can't strike lightning there!", player);
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape(" B ", "BAB", " B ");
		recipe.setIngredient('B', Material.FLINT_AND_STEEL);
		recipe.setIngredient('A', Material.EMERALD);
		return recipe;
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
		return Material.EMERALD;
	}

	@Override
	public int getMaxLevel() {
		return 5;
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
