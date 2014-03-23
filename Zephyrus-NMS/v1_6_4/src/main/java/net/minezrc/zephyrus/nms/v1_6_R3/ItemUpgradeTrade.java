package net.minezrc.zephyrus.nms.v1_6_R3;

import net.minecraft.server.v1_6_R3.EntityHuman;
import net.minecraft.server.v1_6_R3.IMerchant;
import net.minecraft.server.v1_6_R3.MerchantRecipe;
import net.minecraft.server.v1_6_R3.MerchantRecipeList;
import net.minezrc.zephyrus.nms.UpgradeTrade;

import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - ItemUpgradeTrade.java
 *
 * This class represents the 1.6.4 version of the item upgrade trade
 *
 * @author minnymin3
 *
 */

public class ItemUpgradeTrade implements IMerchant, UpgradeTrade {

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
	public EntityHuman m_() {
		return currentTrader;
	}
	
	@Override
	public void a_(EntityHuman trader) {
		this.currentTrader = trader;
	}

	@Override
	public void a_(net.minecraft.server.v1_6_R3.ItemStack arg0) {
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
	public ItemUpgradeTrade clone() {
		ItemUpgradeTrade mer = new ItemUpgradeTrade();
		mer.offer = this.offer;
		return mer;
	}
	
}
