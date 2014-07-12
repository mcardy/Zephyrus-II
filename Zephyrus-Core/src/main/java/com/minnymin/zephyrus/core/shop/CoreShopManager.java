package com.minnymin.zephyrus.core.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.shop.Shop;
import com.minnymin.zephyrus.shop.ShopManager;

/**
 * Zephyrus - CoreShopManager.java
 * 
 * @author minnymin3
 * 
 */

public class CoreShopManager implements ShopManager, Listener {

	List<Shop> shops;

	public CoreShopManager() {
		shops = new ArrayList<Shop>();
	}

	@Override
	public void load() {
		Bukkit.getPluginManager().registerEvents(this, Zephyrus.getPlugin());
		registerShop(new SpellShop());
		registerShop(new WandShop());
		registerShop(new ItemShop());
	}

	@Override
	public void unload() {
	}

	@Override
	public void registerShop(Shop shop) {
		shops.add(shop);
	}

	@EventHandler
	public void onSignCreate(SignChangeEvent event) {
		for (Shop shop : shops) {
			if (event.getLine(0).equalsIgnoreCase("[" + shop.getName() + "]")) {
				if (Zephyrus.getHookManager().getEconomyHook() == null) {
					Language.sendError("shop.create.noeconomy", event.getPlayer());
					return;
				}
				if (!event.getPlayer().hasPermission("zephyrus.shop.create")) {
					Language.sendError("shop.permission", event.getPlayer());
					return;
				}
				if (shop.create(event)) {
					event.setLine(0, shop.getChatColorIdentifier() + "[" + shop.getName() + "]");
				}
			}
		}
	}

	@EventHandler
	public void onSignClick(PlayerInteractEvent event) {
		if (Zephyrus.getHookManager().getEconomyHook() != null && event.getPlayer().hasPermission("zephyrus.shop.use")) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Material type = event.getClickedBlock().getType();
				if (type == Material.SIGN || type == Material.SIGN_POST || type == Material.WALL_SIGN) {
					Sign sign = (Sign) event.getClickedBlock().getState();
					for (Shop shop : shops) {
						if (sign.getLine(0).equals(shop.getChatColorIdentifier() + "[" + shop.getName() + "]")) {
							shop.onClick(event.getPlayer(), sign.getLines());
						}
					}
				}
			}
		}
	}

}
