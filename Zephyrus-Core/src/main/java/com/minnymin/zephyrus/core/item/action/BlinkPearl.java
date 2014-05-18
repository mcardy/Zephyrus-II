package com.minnymin.zephyrus.core.item.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.item.ActionItem;
import com.minnymin.zephyrus.item.ItemRecipe;
import com.minnymin.zephyrus.item.LevelledItem;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.target.Target.TargetType;
import com.minnymin.zephyrus.user.target.Targeted;

/**
 * Zephyrus - BlinkPearl.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.BLOCK, range = 30)
public class BlinkPearl implements ActionItem, LevelledItem {

	@Override
	public String getName() {
		return ChatColor.DARK_BLUE + "Blink Pearl";
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("CCC", "BAB", "CCC");
		recipe.setIngredient('C', Material.ENDER_PEARL);
		recipe.setIngredient('B', Material.BLAZE_POWDER);
		recipe.setIngredient('A', Material.EYE_OF_ENDER);
		return recipe;
	}

	@Override
	public Material getMaterial() {
		return Material.ENDER_PEARL;
	}

	@Override
	public List<String> getLore() {
		return null;
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")),
				DataStructureUtils.createList(1));
	}

	@Override
	public int getCraftingLevel() {
		return 1;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public Material getMaterialCost() {
		return Material.EYE_OF_ENDER;
	}

	@Override
	public List<String> getLevelledLore(int level) {
		return DataStructureUtils.createList(ChatColor.GRAY + "Current Level: " + level);
	}

	@Override
	public int getLevel(List<String> lore) {
		try {
			return Integer.parseInt(lore.get(0).replace(ChatColor.GRAY + "Current Level: ", ""));
		} catch (Exception ex) {
			return 1;
		}
	}

	@Override
	public Set<Action> getActions() {
		return DataStructureUtils.createSet(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onInteract(PlayerInteractEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		player.updateInventory();
		User user = Zephyrus.getUser(player);
		Block block = (Block) user.getTarget(this).getTarget();
		int delay = user.getDelay(getName());
		if (delay > 0) {
			Language.sendError("item.delay", "You still need to wait [SECONDS] seconds to use this item", player,
					"[SECONDS]", delay + "");
			return;
		}
		if (block.getType() != Material.AIR) {
			Location bottom = block.getLocation().add(0, 1, 0);
			Location top = block.getLocation().add(0, 2, 0);
			Block bottomBlock = bottom.getBlock();
			Block topBlock = top.getBlock();
			if (bottomBlock.getType() == Material.AIR && topBlock.getType() == Material.AIR) {
				Location previous = player.getLocation();
				Location teleport = block.getLocation().add(0.5, 1.25, 0.5);
				teleport.setPitch(previous.getPitch());
				teleport.setYaw(previous.getYaw());
				ParticleEffects.sendParticle(Particle.ENDER, previous, 1, 1, 1, 1, 16);
				player.teleport(teleport);
				player.getWorld().playEffect(teleport, Effect.ENDER_SIGNAL, 0);
				user.setDelay(getName(), levelToDelay(getLevel(player.getItemInHand().getItemMeta().getLore())));
				return;
			}
		}
		Language.sendError("spell.blink.failure", "You can't blink there!", player);
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
