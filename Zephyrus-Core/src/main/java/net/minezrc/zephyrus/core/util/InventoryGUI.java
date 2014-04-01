package net.minezrc.zephyrus.core.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.minezrc.zephyrus.Zephyrus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryGUI implements Listener {

	private Inventory inv;
	private Map<Integer, InventoryButton> buttonmap;
	private Set<String> playerlist;

	public InventoryGUI() {
		inv = Bukkit.createInventory(null, 9);
		playerlist = new HashSet<String>();
		buttonmap = new HashMap<Integer, InventoryButton>();
		Bukkit.getPluginManager().registerEvents(this, Zephyrus.getPlugin());
	}

	public InventoryGUI(String name) {
		inv = Bukkit.createInventory(null, 9, name);
		playerlist = new HashSet<String>();
		buttonmap = new HashMap<Integer, InventoryButton>();
		Bukkit.getPluginManager().registerEvents(this, Zephyrus.getPlugin());
	}
	
	/**
	 * Opens the inventory
	 * 
	 * @param player
	 */
	public void open(Player player) {
		player.openInventory(inv);
		playerlist.add(player.getName());
	}

	/**
	 * Sets the item button in the slot
	 * 
	 * @param slot The slot to set
	 * @param item The item to set
	 * @param button The button action
	 * @return The instance of the InventoryGUI (used for easy construction)
	 */
	public InventoryGUI setSlot(int slot, ItemStack item, InventoryButton button) {
		setSize(slot);
		inv.setItem(slot - 1, item);
		buttonmap.put(slot - 1, button);
		return this;
	}
	
	public boolean hasOpen(Player player) {
		return playerlist.contains(player.getName());
	}
	
	private void setSize(int size) {
		if (size > 54)
			return;
		size = size % 9 == 0 ? size : size + 9 - (size % 9);
		if (size != inv.getSize()) {
			Inventory newinv = Bukkit.createInventory(null, size);
			newinv.setContents(inv.getContents());
			this.inv = newinv;
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		if (playerlist.contains(event.getPlayer().getName())) {
			playerlist.remove(event.getPlayer().getName());
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (playerlist.contains(event.getWhoClicked().getName())) {
			event.setCancelled(true);
			if (event.getInventory().getType() != InventoryType.PLAYER) {
				InventoryButton button = buttonmap.get(event.getSlot());
				if (button != null) {
					try {
						button.onClick(Bukkit.getPlayer(event.getWhoClicked().getName()), event.getSlot() + 1);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	public interface InventoryButton {
		/**
		 * The method that is called when a slot is clicked
		 * 
		 * @param player The player who clicked
		 * @param slot The slot that was clicked
		 */
		public void onClick(Player player, int slot);
	}

}
