package net.minezrc.zephyrus.core.item;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.spell.SpellTome;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.event.UserCraftSpellEvent;
import net.minezrc.zephyrus.event.UserPostCastEvent;
import net.minezrc.zephyrus.event.UserPreCastEvent;
import net.minezrc.zephyrus.item.ActionItem;
import net.minezrc.zephyrus.item.Item;
import net.minezrc.zephyrus.item.Wand;
import net.minezrc.zephyrus.spell.Prerequisite;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Zephyrus - ItemListener.java
 * 
 * @author minnymin3
 * 
 */

public class ItemListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Item item = Zephyrus.getItemManager().getItem(player.getItemInHand());
		if (item != null) {
			if (item != null && item instanceof ActionItem) {
				ActionItem action = (ActionItem) item;
				if (action.getActions().contains(event.getAction())) {
					action.onInteract(event);
				}
			}
			if (item != null && item instanceof Wand) {
				Wand wand = (Wand) item;
				String bound = wand.getSpell(player.getItemInHand());
				// Spell Crafting
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK
						&& event.getClickedBlock().getType() == Material.BOOKSHELF) {
					Set<ItemStack> items = getItems(event.getClickedBlock().getLocation().add(0, 1, 0));
					Spell spell = Zephyrus.getSpell(items);
					if (spell != null) {
						User user = Zephyrus.getUser(player);
						if (wand.getCraftingAbilityLevel() < spell.getRequiredLevel()) {
							Language.sendError("crafting.requireditemlevel", "Your wand needs to have the ability of level [LEVEL] to craft that spell!", player, "[LEVEL]", spell
									.getRequiredLevel() + "");
							return;
						}
						if (user.isSpellLearned(spell)) {
							Language.sendError("crafting.alreadylearned", "You already know [SPELL]!", player, "[SPELL]", spell
									.getName());
							return;
						}
						if (user.getLevel() < spell.getRequiredLevel()) {
							Language.sendError("crafting.requiredlevel", "You need to have the knowledge of level [LEVEL] to craft [SPELL]!", player, "[SPELL]", spell
									.getName(), "[LEVEL]", spell.getRequiredLevel() + "");
							return;
						}
						if (spell.getClass().isAnnotationPresent(Prerequisite.class)
								&& !user.isSpellLearned(Zephyrus.getSpell(((Prerequisite) spell.getClass()
										.getAnnotation(Prerequisite.class)).requiredSpell()))) {
							Language.sendError("crafting.requiredspell", "You need the knowledge of [REQ] to craft [SPELL]", player, "[REQ]", ((Prerequisite) spell)
									.requiredSpell().getName(), "[SPELL]", spell.getName());
							return;
						}
						UserCraftSpellEvent craftEvent = new UserCraftSpellEvent(player, spell);
						Bukkit.getPluginManager().callEvent(craftEvent);
						if (!craftEvent.isCancelled()) {
							event.getClickedBlock().setType(Material.AIR);
							Location loc = event.getClickedBlock().getLocation().add(0.5, 1, 0.5);
							loc.getWorld().dropItem(loc, SpellTome.createSpellTome(spell))
									.setVelocity(new Vector(0, 0, 0));
							int chance = 1;
							if (user.getLevel() < 7) {
								chance = 1;
							} else if (user.getLevel() < 15) {
								chance = 2;
							} else {
								chance = 3;
							}
							loc.getWorld()
									.dropItemNaturally(loc.add(0, +1, 0), new ItemStack(Material.BOOK,
											new Random().nextInt(chance))).setVelocity(new Vector(0, 0, 0));
							ParticleEffects.sendParticle(Particle.ENCHANTMENT_TABLE, loc, 30);
							player.playSound(loc, Sound.ORB_PICKUP, 3, 12);
						}
					} else {
						Language.sendError("crafting.nospell", "Spell recipe not found!", player);
					}
					// Checking Bound Spell
				} else if (player.isSneaking()
						&& (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
					Language.sendMessage("item.wand.bound", "Current bound spell: " + ChatColor.GOLD + "[SPELL]", player, "[SPELL]", bound != null ? bound
							: "none");
					// Casting Bound Spell
				} else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (bound != null) {
						Spell spell = Zephyrus.getSpell(bound);
						User user = Zephyrus.getUser(player);
						if (spell == null) {
							Language.sendError("item.wand.cast.badspell", "The currently bound spell does not exist! Unbind and rebind it.", player);
							return;
						}
						if (!user.isSpellLearned(Zephyrus.getSpell(bound))) {
							Language.sendError("item.wand.cast.learn", "You have not learned [SPELL]", player, "[SPELL]", bound);
							return;
						}
						if (user.getMana() < spell.getManaCost()) {
							Language.sendError("command.cast.mana", "You do not have enough mana to cast [SPELL] [MANA]", player, "[SPELL]", spell
									.getName(), "[MANA]", ChatColor.RED + "" + user.getMana() + ChatColor.GRAY + "/"
									+ ChatColor.GREEN + spell.getManaCost());
							return;
						}
						UserPreCastEvent preCast = new UserPreCastEvent(user, spell, 1, null);
						Bukkit.getPluginManager().callEvent(preCast);
						if (!preCast.isCancelled()) {
							CastResult result = spell.onCast(user, 1 + wand.getPowerIncrease(spell), null, null);
							if (result == CastResult.NORMAL_SUCCESS) {
								user.drainMana(spell.getManaCost());
								user.addLevelProgress(spell.getXpReward());
								UserPostCastEvent postCast = new UserPostCastEvent(user, spell, 1, null);
								Bukkit.getPluginManager().callEvent(postCast);
							}
						}
					} else {
						Language.sendError("item.wand.nobound", "There is no spell bound to this wand! Bind one with /bind <spell>", player);
					}
				}
			}
		}
	}

	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if (event.getRecipe() != null
				&& event.getRecipe().getResult() != null) {
			Item item = Zephyrus.getItemManager().getItem(event.getRecipe().getResult());
			if (item == null) {
				return;
			}
			for (HumanEntity human : event.getViewers()) {
				if (human instanceof Player) {
					Player player = (Player) human;
					if (Zephyrus.getUser(player).getLevel() < item.getCraftingLevel()) {
						event.getInventory().setResult(null);
						Language.sendError("crafting.item.requiredlevel", "You lack the knowledge of level [LEVEL] required to craft [ITEM]", player, "[LEVEL]", item
								.getCraftingLevel() + "", "[ITEM]", item.getName());
					}
				}
			}
		}
	}

	public Set<ItemStack> getItems(Location loc) {
		Set<ItemStack> set = new HashSet<ItemStack>();
		for (Entity en : loc.getChunk().getEntities()) {
			if (en.getType() == EntityType.DROPPED_ITEM) {
				if (en.getLocation().distance(loc) <= 1) {
					set.add(((org.bukkit.entity.Item) en).getItemStack());
				}
			}
		}
		return set;
	}

}
