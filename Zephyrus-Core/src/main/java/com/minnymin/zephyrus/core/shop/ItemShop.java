package com.minnymin.zephyrus.core.shop;

import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.chat.Message;
import com.minnymin.zephyrus.core.chat.MessageComponent;
import com.minnymin.zephyrus.core.chat.MessageEvent.MessageHoverEvent;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageColor;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageFormatting;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.item.Item;
import com.minnymin.zephyrus.item.LevelledItem;
import com.minnymin.zephyrus.shop.Shop;

/**
 * Zephyrus - WandShop.java
 * 
 * @author minnymin3
 * 
 */

public class ItemShop implements Shop {

	@Override
	public boolean create(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] args = event.getLines();
		int amount;
		Item item;
		try {
			amount = Integer.parseInt(args[2]);
		} catch (Exception ex) {
			Language.sendError("shop.item.create.amount", "Cost on line 3 not valid. Expected a number.", player);
			return false;
		}
		item = Zephyrus.getItemManager().getItemFromBaseName(args[1]);
		if (item == null) {
			Language.sendError("shop.item.create.wand", "Item on line 2 not valid. No item found by that name.", player);
			return false;
		}
		Language.sendMessage("shop.item.create.complete",
				"Successfully created an ItemShop selling the [ITEM] item for [AMOUNT]", player, "[ITEM]",
				item.getName(), "[AMOUNT]", amount + "");
		event.setLine(2, "$" + args[2]);
		event.setLine(1, item.getName());
		return true;
	}

	@Override
	public ChatColor getChatColorIdentifier() {
		return ChatColor.BLUE;
	}

	@Override
	public String getName() {
		return "ItemShop";
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onClick(Player player, String[] args) {
		Item item = (Item) Zephyrus.getItemManager().getItem(args[1]);
		int amount = Integer.parseInt(args[2].replace("$", ""));
		if (Zephyrus.getHookManager().getEconomyHook().getBalance(player) < amount) {
			Language.sendError("shop.item.use.amount", "You do not have enough money to buy this spell: [AMOUNT]",
					player, "[AMOUNT]", Zephyrus.getHookManager().getEconomyHook().getBalance(player) + "/" + amount);
			return;
		}
		ItemStack stack = new ItemStack(item.getMaterial());
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(item.getName());
		if (item instanceof LevelledItem) {
			meta.setLore(((LevelledItem) item).getLevelledLore(1));
		} else {
			meta.setLore(item.getLore());
		}
		for (Entry<Enchantment, Integer> entry : item.getEnchantments().entrySet()) {
			meta.addEnchant(entry.getKey(), entry.getValue(), true);
		}
		stack.setItemMeta(meta);
		if (player.getInventory().addItem(stack).isEmpty()) {
			Zephyrus.getHookManager().getEconomyHook().drainBalance(player, amount);
			player.updateInventory();
			new Message("shop.item.use.complete", "You have successfully purchased ", MessageColor.GRAY,
					MessageFormatting.NONE).addComponent(
					new MessageComponent(item.getName(), MessageColor.GOLD, MessageFormatting.BOLD).setHoverEvent(
							MessageHoverEvent.TEXT, item.getLore().get(0))).sendMessage(player);
		} else {
			Language.sendError("shop.item.use.full", "Your inventory is full! Cannot add item!", player);
		}
	}

}
