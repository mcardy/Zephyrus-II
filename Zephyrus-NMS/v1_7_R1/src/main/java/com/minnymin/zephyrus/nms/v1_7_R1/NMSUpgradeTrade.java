package com.minnymin.zephyrus.nms.v1_7_R1;

import net.minecraft.server.v1_7_R1.EntityHuman;
import net.minecraft.server.v1_7_R1.IMerchant;
import net.minecraft.server.v1_7_R1.MerchantRecipe;
import net.minecraft.server.v1_7_R1.MerchantRecipeList;

import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.nms.UpgradeTrade;

/**
 * Zephyrus - ItemUpgradeTrade.java
 *
 * This class represents the 1.7.2 version of the item upgrade trade
 *
 * @author minnymin3
 *
 */

public class NMSUpgradeTrade implements IMerchant, UpgradeTrade {

	private MerchantRecipe offer;
	private EntityHuman currentTrader;
	
	@Override
	public void a(MerchantRecipe offer) {
		this.offer = offer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MerchantRecipeList getOffers(EntityHuman trader) {
		MerchantRecipeList list = new MerchantRecipeList();
		list.add(this.offer);
		return list;
	}

	@Override
	public EntityHuman b() {
		return currentTrader;
	}
	
	@Override
	public void a_(EntityHuman trader) {
		this.currentTrader = trader;
	}

	@Override
	public void a_(net.minecraft.server.v1_7_R1.ItemStack arg0) {
	}

	@Override
	public void setOffer(ItemStack i1, ItemStack i2, ItemStack i3) {
		a(new MerchantRecipe(CraftItemStack.asNMSCopy(i1), CraftItemStack.asNMSCopy(i2), CraftItemStack.asNMSCopy(i3)));
	}
	
	@Override
	public void openTrade(Player player) {
		currentTrader = ((CraftPlayer) player).getHandle();
		currentTrader.openTrade(this, "Arcane Leveler");
	}
	
	@Override
	public ItemStack getFirstInput() {
		return CraftItemStack.asBukkitCopy(this.offer.getBuyItem1());
	}
	
	@Override
	public ItemStack getSecondInput() {
		return CraftItemStack.asBukkitCopy(this.offer.getBuyItem2());
	}
	
	@Override
	public ItemStack getOutput() {
		return CraftItemStack.asBukkitCopy(this.offer.getBuyItem3());
	}
	
	@Override
	public NMSUpgradeTrade clone() {
		NMSUpgradeTrade mer = new NMSUpgradeTrade();
		mer.offer = this.offer;
		return mer;
	}
	
}
