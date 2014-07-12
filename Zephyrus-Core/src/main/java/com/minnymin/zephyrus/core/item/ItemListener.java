package com.minnymin.zephyrus.core.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.config.ConfigOptions;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.core.util.InventoryGUI;
import com.minnymin.zephyrus.core.util.InventoryGUI.InventoryButton;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.ParticleEffects;
import com.minnymin.zephyrus.core.util.ParticleEffects.Particle;
import com.minnymin.zephyrus.event.UserCraftSpellEvent;
import com.minnymin.zephyrus.item.ActionItem;
import com.minnymin.zephyrus.item.Item;
import com.minnymin.zephyrus.item.LevelledItem;
import com.minnymin.zephyrus.item.Wand;
import com.minnymin.zephyrus.nms.UpgradeTrade;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Targeted;

/**
 * Zephyrus - ItemListener.java
 * 
 * @author minnymin3
 * 
 */

public class ItemListener implements Listener {

	private Map<String, UpgradeTrade> traders;
	private Set<Material> actionMaterials;

	protected ItemListener() {
		traders = new HashMap<String, UpgradeTrade>();
		actionMaterials = DataStructureUtils.createSet(Material.ANVIL, Material.BED_BLOCK, Material.CHEST,
				Material.FURNACE, Material.BURNING_FURNACE, Material.WOOD_DOOR, Material.WOODEN_DOOR, Material.LEVER,
				Material.IRON_DOOR_BLOCK, Material.STONE_BUTTON, Material.JUKEBOX, Material.ENCHANTMENT_TABLE,
				Material.BREWING_STAND, Material.ENDER_CHEST, Material.COMMAND, Material.BEACON,
				Material.TRAPPED_CHEST, Material.HOPPER, Material.TRAP_DOOR, Material.DISPENSER, Material.DROPPER);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack stack = player.getItemInHand();
		Item item = Zephyrus.getItemManager().getItem(player.getItemInHand());
		User user = Zephyrus.getUser(player);
		boolean didCast = false;
		if (item != null
				&& !(event.getClickedBlock() != null && actionMaterials.contains(event.getClickedBlock().getType()))) {
			event.setCancelled(true);
			if (item != null && item instanceof LevelledItem && event.getAction() == Action.RIGHT_CLICK_BLOCK
					&& event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE
					&& event.getClickedBlock().getData() == 10) {
				LevelledItem levelled = (LevelledItem) item;
				int level = levelled.getLevel(stack.getItemMeta().getLore());
				if (level < levelled.getMaxLevel()) {
					level++;
					UpgradeTrade trade = Zephyrus.getNMSManager().getItemUpgradeTrade();
					ItemStack input = stack.clone();
					input.setAmount(1);
					ItemStack output = input.clone();
					ItemMeta meta = output.getItemMeta();
					meta.setLore(levelled.getLevelledLore(level));
					output.setItemMeta(meta);
					trade.setOffer(input, new ItemStack(levelled.getMaterialCost(), level), output);
					trade.openTrade(player);
					this.traders.put(player.getName(), trade);
				} else {
					Language.sendError("item.arcane.max", player);
				}
			} else if (item != null && item instanceof ActionItem) {
				ActionItem action = (ActionItem) item;
				if (action.getActions().contains(event.getAction())) {
					if (action.getClass().isAnnotationPresent(Targeted.class)) {
						Targeted targeted = action.getClass().getAnnotation(Targeted.class);
						user.setTarget(item, targeted.type(), targeted.range(), targeted.friendly());
					}
					action.onInteract(event);
				}
			} else if (item != null && item instanceof Wand) {
				Wand wand = (Wand) item;
				String bound = wand.getSpell(player.getItemInHand());
				// Spell Crafting
				if (event.getAction() == Action.RIGHT_CLICK_BLOCK
						&& event.getClickedBlock().getType() == Material.BOOKSHELF
						&& !ConfigOptions.DISABLE_SPELL_CRAFTING) {
					List<ItemStack> items;
					BukkitRunnable removalTask;
					if (event.getClickedBlock().getRelative(BlockFace.UP).getType() != Material.CHEST) {
						final Set<org.bukkit.entity.Item> itemEntity = getEntities(event.getClickedBlock()
								.getLocation().add(0.5, 1.5, 0.5));
						items = getItems(itemEntity);
						removalTask = new BukkitRunnable() {

							@Override
							public void run() {
								for (org.bukkit.entity.Item item : itemEntity) {
									item.remove();
								}
							}

						};
					} else {
						final Chest chest = (Chest) event.getClickedBlock().getRelative(BlockFace.UP).getState();
						items = new ArrayList<ItemStack>();
						for (ItemStack i : chest.getInventory().getContents()) {
							if (i != null) {
								items.add(i);
							}
						}
						removalTask = new BukkitRunnable() {

							@Override
							public void run() {
								for (int i = 0; i < chest.getInventory().getSize(); i++) {
									ItemStack item = chest.getInventory().getItem(i);
									if (item != null) {
										item.setAmount(0);
										chest.getInventory().setItem(i, item);
									}
								}
								chest.update(true);
							}

						};
					}
					List<Spell> possibleSpells = Zephyrus.getSpell(items);
					List<Spell> spells = new ArrayList<Spell>();
					for (Spell spell : possibleSpells) {
						if (wand.getCraftingAbilityLevel() < spell.getRequiredLevel()) {
							Language.sendError("crafting.reqwandlevel", player, "[SPELL]", spell.getName());
							continue;
						}
						if (user.getLevel() < spell.getRequiredLevel()) {
							Language.sendError("crafting.reqplayerlevel", player, "[SPELL]", spell.getName());
							continue;
						}
						spells.add(spell);
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
							removalTask.run();
							loc.getWorld().dropItem(loc, new ItemStack(Material.BOOK, amount))
									.setVelocity(new Vector(0, 0, 0));
							ParticleEffects.sendParticle(Particle.ENCHANTMENT_TABLE, loc, 0.25F, 0.1F, 0.25F, 0, 50);
							player.playSound(loc, Sound.ORB_PICKUP, 3, 12);
						}
					} else if (spells.size() > 1) {
						InventoryGUI gui = new InventoryGUI(
								Language.get("crafting.selectionname", "Craft which spell?"));
						for (int i = 0; i < spells.size(); i++) {
							gui.setSlot(i + 1, SpellTome.createSpellTome(spells.get(i)),
									getButton(spells.get(i), event.getClickedBlock(), removalTask));
						}
						gui.open(player);
					} else {
						Language.sendError("crafting.nospell", player);
					}
					// Checking Bound Spell
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK
						&& event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
					event.getClickedBlock().setData((byte) 10);
					Language.sendMessage("item.arcane.create", player);
					return;
				} else if (player.isSneaking()
						&& (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
					Language.sendMessage("item.wand.bound", player, "[SPELL]", bound != null ? bound : "none");
					// Casting Bound Spell
				} else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (user.isCastingSpell()) {
						user.stopCasting();
					} else if (bound != null) {
						Spell spell = Zephyrus.getSpell(bound);
						didCast = true;
						user.castSpell(spell, 1 + wand.getPowerIncrease(spell), null);
					} else {
						Language.sendError("item.wand.nobound", player);
					}
				}
			}
		}
		if (user.isCastingSpell() && !didCast) {
			user.stopCasting();
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		if (this.traders.containsKey(e.getPlayer().getName())) {
			this.traders.remove(e.getPlayer().getName());
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (this.traders.containsKey(e.getWhoClicked().getName())) {
			if (e.getInventory().getType() == InventoryType.MERCHANT) {
				UpgradeTrade m = this.traders.get(e.getWhoClicked().getName());
				ItemStack i = e.getCurrentItem();
				ItemStack i2 = e.getCursor();
				ItemStack mi = m.getFirstInput();
				ItemStack m2 = m.getOutput();
				if (e.getRawSlot() != 0 && e.getRawSlot() != 1 && i != null && i2 != null && e.getRawSlot() != 2
						&& !i.equals(mi) && !i.equals(m2) && !i2.equals(mi) && !i2.equals(m2)
						&& i.getType() != m.getSecondInput().getType() && i2.getType() != m.getSecondInput().getType()) {
					e.setCancelled(true);
				}
				if (i != null && i.getType() == m.getSecondInput().getType() || i != null
						&& i2.getType() == m.getSecondInput().getType()) {
					if ((i.hasItemMeta() || i2.hasItemMeta()) && (!i.equals(mi) && !i2.equals(mi))
							&& (!i.equals(m2) && !i2.equals(m2))) {
						e.setCancelled(true);
					}
				}
				if (i2 != null && i2.equals(m2)) {
					new CloseInv(e.getViewers().get(0)).runTaskLater(Zephyrus.getPlugin(), 2);
				}
			}
		}
	}

	private class CloseInv extends BukkitRunnable {
		HumanEntity e;

		CloseInv(HumanEntity e) {
			this.e = e;
		}

		@Override
		public void run() {
			e.closeInventory();
		}
	}

	public InventoryButton getButton(final Spell spell, final Block block, final BukkitRunnable removalTask) {
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
					if (user.getLevel() < 2) {
						amount = 1;
					} else if (user.getLevel() < 7) {
						amount = 2;
					} else {
						amount = 3;
					}
					block.setType(Material.AIR);
					removalTask.run();
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
					if (!player.hasPermission("zephyrus.craft." + item.getInternalName())) {
						event.getInventory().setResult(null);
						Language.sendError("crafting.item.nopermission", player, "[ITEM]", item.getName());
					} else if (Zephyrus.getUser(player).getLevel() < item.getCraftingLevel()) {
						event.getInventory().setResult(null);
						Language.sendError("crafting.item.requiredlevel", player, "[LEVEL]", item.getCraftingLevel()
								+ "", "[ITEM]", item.getName());
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

	public List<ItemStack> getItems(Set<org.bukkit.entity.Item> entities) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		for (org.bukkit.entity.Item en : entities) {
			list.add((en).getItemStack());
		}
		return list;
	}

}
