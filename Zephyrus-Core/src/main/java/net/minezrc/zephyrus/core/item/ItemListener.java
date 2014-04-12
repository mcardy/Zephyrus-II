package net.minezrc.zephyrus.core.item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.util.InventoryGUI;
import net.minezrc.zephyrus.core.util.InventoryGUI.InventoryButton;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.ParticleEffects;
import net.minezrc.zephyrus.core.util.ParticleEffects.Particle;
import net.minezrc.zephyrus.event.UserCraftSpellEvent;
import net.minezrc.zephyrus.item.ActionItem;
import net.minezrc.zephyrus.item.Item;
import net.minezrc.zephyrus.item.Wand;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.annotation.Prerequisite;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
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
		User user = Zephyrus.getUser(player);
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
					Set<org.bukkit.entity.Item> itemEntity = getEntities(event.getClickedBlock().getLocation()
							.add(0.5, 1.5, 0.5));
					Set<ItemStack> items = getItems(itemEntity);
					List<Spell> possibleSpells = Zephyrus.getSpell(items);
					List<Spell> spells = new ArrayList<Spell>();
					for (Spell spell : possibleSpells) {
						if (wand.getCraftingAbilityLevel() >= spell.getRequiredLevel()
								&& user.getLevel() >= spell.getRequiredLevel()
								&& !(spell.getClass()
										.isAnnotationPresent(Prerequisite.class) && !user.isSpellLearned(Zephyrus
										.getSpell(((Prerequisite) spell.getClass()
												.getAnnotation(Prerequisite.class)).requiredSpell())))) {
							spells.add(spell);
						}
					}
					if (spells.size() == 1) {
						Spell spell = spells.get(0);
						UserCraftSpellEvent craftEvent = new UserCraftSpellEvent(player, spell);
						Bukkit.getPluginManager().callEvent(craftEvent);
						if (!craftEvent.isCancelled()) {
							Location loc = event.getClickedBlock().getLocation().add(0.5, 1.5, 0.5);
							loc.getWorld().dropItem(loc, SpellTome.createSpellTome(spell))
									.setVelocity(new Vector(0, 0, 0));
							int amount = 1;
							if (user.getLevel() < 7) {
								amount = 1;
							} else if (user.getLevel() < 15) {
								amount = 2;
							} else {
								amount = 3;
							}
							event.getClickedBlock().setType(Material.AIR);
							for (org.bukkit.entity.Item i : itemEntity) {
								i.remove();
							}
							loc.getWorld().dropItem(loc, new ItemStack(Material.BOOK, amount))
									.setVelocity(new Vector(0, 0, 0));
							ParticleEffects.sendParticle(Particle.ENCHANTMENT_TABLE, loc, 0.25F, 0.1F, 0.25F, 0, 50);
							player.playSound(loc, Sound.ORB_PICKUP, 3, 12);
						}
					} else if (spells.size() > 1) {
						InventoryGUI gui = new InventoryGUI(
								Language.get("crafting.selectionname", "Craft which spell?"));
						for (int i = 0; i < spells.size(); i++) {
							gui.setSlot(i + 1, SpellTome.createSpellTome(spells.get(i)), getButton(spells.get(i), event
									.getClickedBlock(), itemEntity));
						}
						gui.open(player);
					} else {
						Language.sendError("crafting.nospell", "You cannot craft any spells with those aspects. Consult the SpellBook for more information.", player);
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
						user.castSpell(spell, wand.getPowerIncrease(spell), null);
					} else {
						Language.sendError("item.wand.nobound", "There is no spell bound to this wand! Bind one with /bind <spell>", player);
					}
				}
			}
		}
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (user.isCastingSpell()) {
				user.stopCasting();
			}
		}
	}

	public InventoryButton getButton(final Spell spell, final Block block, final Set<org.bukkit.entity.Item> entities) {
		return new InventoryButton() {
			@Override
			public void onClick(Player player, int slot) {
				User user = Zephyrus.getUser(player);
				UserCraftSpellEvent craftEvent = new UserCraftSpellEvent(player, spell);
				Bukkit.getPluginManager().callEvent(craftEvent);
				if (!craftEvent.isCancelled()) {
					Location loc = block.getLocation().add(0.5, 1.5, 0.5);
					loc.getWorld().dropItem(loc, SpellTome.createSpellTome(spell)).setVelocity(new Vector(0, 0, 0));
					int amount = 1;
					if (user.getLevel() < 7) {
						amount = 1;
					} else if (user.getLevel() < 15) {
						amount = 2;
					} else {
						amount = 3;
					}
					block.setType(Material.AIR);
					for (org.bukkit.entity.Item i : entities) {
						i.remove();
					}
					loc.getWorld().dropItem(loc, new ItemStack(Material.BOOK, amount)).setVelocity(new Vector(0, 0, 0));
					ParticleEffects.sendParticle(Particle.ENCHANTMENT_TABLE, loc, 0.25F, 0.1F, 0.25F, 0, 50);
					player.playSound(loc, Sound.ORB_PICKUP, 3, 12);
				}
				player.closeInventory();
			}
		};
	}

	@EventHandler
	public void onCraft(PrepareItemCraftEvent event) {
		if (event.getRecipe() != null && event.getRecipe().getResult() != null) {
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

	public Set<org.bukkit.entity.Item> getEntities(Location loc) {
		Set<org.bukkit.entity.Item> set = new HashSet<org.bukkit.entity.Item>();
		for (Entity en : loc.getChunk().getEntities()) {
			if (en.getType() == EntityType.DROPPED_ITEM) {
				if (en.getLocation().distance(loc) <= 1) {
					set.add((org.bukkit.entity.Item) en);
				}
			}
		}
		return set;
	}

	public Set<ItemStack> getItems(Set<org.bukkit.entity.Item> entities) {
		Set<ItemStack> set = new HashSet<ItemStack>();
		for (Entity en : entities) {
			set.add(((org.bukkit.entity.Item) en).getItemStack());
		}
		return set;
	}

}
