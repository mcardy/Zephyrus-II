package com.minnymin.zephyrus.core.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.chat.Message;
import com.minnymin.zephyrus.core.chat.MessageComponent;
import com.minnymin.zephyrus.core.chat.MessageEvent.MessageHoverEvent;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageColor;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageFormatting;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.event.UserLearnSpellEvent;
import com.minnymin.zephyrus.shop.Shop;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - SpellShop.java
 * 
 * @author minnymin3
 * 
 */

public class SpellShop implements Shop {

	@Override
	public boolean create(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] args = event.getLines();
		int amount;
		Spell spell;
		try {
			amount = Integer.parseInt(args[2]);
		} catch (Exception ex) {
			Language.sendError("shop.spell.create.amount", "Cost on line 3 not valid. Expected a number.", player);
			return false;
		}
		spell = Zephyrus.getSpell(args[1]);
		if (spell == null) {
			Language.sendError("shop.spell.create.spell", "Spell on line 2 not valid. No spell found by that name.",
					player);
			return false;
		}
		Language.sendMessage("shop.spell.create.complete",
				"Successfully created a SpellShop selling the [SPELL] spell for [AMOUNT]", player, "[SPELL]",
				spell.getName(), "[AMOUNT]", amount + "");
		event.setLine(2, "$" + args[2]);
		return true;
	}

	@Override
	public ChatColor getChatColorIdentifier() {
		return ChatColor.GOLD;
	}

	@Override
	public String getName() {
		return "SpellShop";
	}

	@Override
	public void onClick(Player player, String[] args) {
		User user = Zephyrus.getUser(player);
		Spell spell = Zephyrus.getSpell(args[1]);
		int amount = Integer.parseInt(args[2].replace("$", ""));
		if (spell == null) {
			Language.sendError("shop.spell.use.broken", "Something went wrong! Spell not found...", player);
			return;
		}
		if (user.getLevel() < spell.getRequiredLevel()) {
			Language.sendError("shop.spell.use.level", "You are not high enough level to learn this spell: [LEVEL]",
					player, "[LEVEL]", user.getLevel() + "/" + spell.getRequiredLevel());
			return;
		}
		if (user.isSpellLearned(spell)) {
			Language.sendError("shop.spell.use.learned", "You already know [SPELL]!", player, "[SPELL]", spell
					.getName());
			return;
		}
		if (spell.getClass().isAnnotationPresent(Prerequisite.class)
				&& !user.isSpellLearned(Zephyrus.getSpell(((Prerequisite) spell.getClass().getAnnotation(
						Prerequisite.class)).requiredSpell()))) {
			Language.sendError(
					"shop.spell.use.level",
					"You do not have the spells required to learn this spell: [SPELL]",
					player,
					"[SPELL]",
					Zephyrus.getSpell(
							((Prerequisite) spell.getClass().getAnnotation(Prerequisite.class)).requiredSpell())
							.getName());
			return;
		}
		if (Zephyrus.getHookManager().getEconomyHook().getBalance(player) < amount) {
			Language.sendError("shop.spell.use.amount", "You do not have enough money to buy this spell: [AMOUNT]",
					player, "[AMOUNT]", Zephyrus.getHookManager().getEconomyHook().getBalance(player) + "/" + amount);
			return;
		}
		UserLearnSpellEvent learn = new UserLearnSpellEvent(player, spell);
		Bukkit.getPluginManager().callEvent(learn);
		if (!learn.isCancelled()) {
			Zephyrus.getHookManager().getEconomyHook().drainBalance(player, amount);
			user.addSpell(spell);
			new Message("shop.spell.use.complete", "You have successfully purchased ",
					MessageColor.GRAY, MessageFormatting.NONE).addComponent(new MessageComponent(spell.getName(),
					MessageColor.GOLD, MessageFormatting.BOLD).setHoverEvent(MessageHoverEvent.TEXT, spell
					.getDescription())).sendMessage(player);
		}
		
	}
}
