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
import com.minnymin.zephyrus.item.Wand;
import com.minnymin.zephyrus.shop.Shop;

/**
 * Zephyrus - WandShop.java
 * 
 * @author minnymin3
 * 
 */

public class WandShop implements Shop {

	@Override
	public boolean create(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] args = event.getLines();
		int amount;
		Item item;
		Wand wand;
		try {
			amount = Integer.parseInt(args[2]);
		} catch (Exception ex) {
			Language.sendError("shop.wand.create.amount", player);
			return false;
		}
		item = Zephyrus.getItemManager().getItemFromBaseName(args[1]);
		if (item == null || !(item instanceof Wand)) {
			Language.sendError("shop.wand.create.wand", player);
			return false;
		}
		wand = (Wand) item;
		Language.sendMessage("shop.wand.create.complete", player, "[WAND]", wand.getName(), "[AMOUNT]", amount + "");
		event.setLine(2, "$" + args[2]);
		event.setLine(1, wand.getName());
		return true;
	}

	@Override
	public ChatColor getChatColorIdentifier() {
		return ChatColor.DARK_AQUA;
	}

	@Override
	public String getName() {
		return "WandShop";
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onClick(Player player, String[] args) {
		Wand wand = (Wand) Zephyrus.getItemManager().getItem(args[1]);
		int amount = Integer.parseInt(args[2].replace("$", ""));
		if (Zephyrus.getHookManager().getEconomyHook().getBalance(player) < amount) {
			Language.sendError("shop.wand.use.amount", player, "[AMOUNT]", Zephyrus.getHookManager().getEconomyHook()
					.getBalance(player)
					+ "/" + amount);
			return;
		}
		ItemStack stack = new ItemStack(wand.getMaterial());
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(wand.getName());
		if (wand instanceof LevelledItem) {
			meta.setLore(((LevelledItem) wand).getLevelledLore(1));
		} else {
			meta.setLore(wand.getLore());
		}
		for (Entry<Enchantment, Integer> entry : wand.getEnchantments().entrySet()) {
			meta.addEnchant(entry.getKey(), entry.getValue(), true);
		}
		stack.setItemMeta(meta);
		if (player.getInventory().addItem(stack).isEmpty()) {
			Zephyrus.getHookManager().getEconomyHook().drainBalance(player, amount);
			player.updateInventory();
			new Message("shop.wand.use.complete", MessageColor.GRAY, MessageFormatting.NONE).addComponent(
					new MessageComponent(wand.getName(), MessageColor.GOLD, MessageFormatting.BOLD).setHoverEvent(
							MessageHoverEvent.TEXT, wand.getLore().get(0))).sendMessage(player);
		} else {
			Language.sendError("shop.wand.use.full", player);
		}
	}

}
