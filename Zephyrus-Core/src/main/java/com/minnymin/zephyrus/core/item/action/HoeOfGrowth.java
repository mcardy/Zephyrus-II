package com.minnymin.zephyrus.core.item.action;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.minnymin.zephyrus.core.item.action.plant.PlantRegistry;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.item.ActionItem;
import com.minnymin.zephyrus.item.ItemRecipe;

/**
 * Zephyrus - HoeOfGrowth.java
 * 
 * @author minnymin3
 * 
 */

public class HoeOfGrowth implements ActionItem {

	@Override
	public int getCraftingLevel() {
		return 1;
	}

	@Override
	public Map<Enchantment, Integer> getEnchantments() {
		return DataStructureUtils.createMap(DataStructureUtils.createList(Enchantment.getByName("glow")),
				DataStructureUtils.createList(1));
	}

	@Override
	public List<String> getLore() {
		return DataStructureUtils.createList(ChatColor.GRAY + "Works on all things living");
	}

	@Override
	public Material getMaterial() {
		return Material.GOLD_HOE;
	}

	@Override
	public String getName() {
		return ChatColor.GREEN + "Hoe of Growth";
	}

	@Override
	public ItemRecipe getRecipe() {
		ItemRecipe recipe = new ItemRecipe();
		recipe.setShape("CBC", "BAB", "CBC");
		recipe.setIngredient('C', Material.SAPLING);
		recipe.setIngredient('B', Material.BONE);
		recipe.setIngredient('A', Material.GOLD_HOE);
		return recipe;
	}

	@Override
	public Set<Action> getActions() {
		return DataStructureUtils.createSet(Action.RIGHT_CLICK_BLOCK);
	}

	@Override
	public void onInteract(PlayerInteractEvent event) {
		Block target = event.getClickedBlock();
		if (PlantRegistry.grow(target)) {
			Location loc = target.getLocation();
			loc.setX(loc.getX() + 0.6);
			loc.setZ(loc.getZ() + 0.6);
			loc.setY(loc.getY() + 0.3);
			ParticleEffects.sendParticle(Particle.GREEN_SPARKLE, loc, (float) 0.25, (float) 0.1, (float) 0.25, 100, 20);
			event.getItem().setDurability((short) (event.getItem().getDurability() + 1));
		}
	}

}
