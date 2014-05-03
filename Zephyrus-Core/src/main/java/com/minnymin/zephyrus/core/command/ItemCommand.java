package com.minnymin.zephyrus.core.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.chat.Message;
import com.minnymin.zephyrus.core.chat.MessageComponent;
import com.minnymin.zephyrus.core.chat.MessageEvent.MessageHoverEvent;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageColor;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageFormatting;
import com.minnymin.zephyrus.core.item.SpellTome;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.command.Command;
import com.minnymin.zephyrus.core.util.command.CommandArgs;
import com.minnymin.zephyrus.core.util.command.Completer;
import com.minnymin.zephyrus.event.UserBindSpellEvent;
import com.minnymin.zephyrus.item.Item;
import com.minnymin.zephyrus.item.Wand;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ItemCommand.java
 * 
 * @author minnymin3
 * 
 */

public class ItemCommand {

	@Command(name = "spelltome",
			aliases = { "tome" },
			permission = "zephyrus.command.spelltome.give",
			description = "Gives a spelltome to the designated player",
			usage = "/spelltome <spell> [player]")
	public void onTomeCommand(CommandArgs args) {
		if (args.getArgs().length == 0) {
			Language.sendMessage("command.spell", "No spell specified", args.getSender());
		} else if (args.getArgs().length == 1) {
			if (!args.isPlayer()) {
				Language.sendError("command.player", "No player specified", args.getSender());
				return;
			}
			Spell spell = Zephyrus.getSpell(args.getArgs()[0]);
			if (spell == null) {
				Language.sendError("command.spelltome.badspell", "That is not a spell", args.getSender());
				return;
			}
			args.getPlayer().getInventory().addItem(SpellTome.createSpellTome(spell));
			Language.sendMessage("command.spelltome.complete.self", "You have been given a [SPELL] spelltome", args
					.getSender(), "[SPELL]", ChatColor.GOLD + WordUtils.capitalize(spell.getName()));
		} else {
			Spell spell = Zephyrus.getSpell(args.getArgs()[0]);
			if (spell == null) {
				Language.sendError("command.spelltome.badspell", "That is not a spell", args.getSender());
				return;
			}
			Player player = Bukkit.getPlayer(args.getArgs()[1]);
			if (player == null) {
				Language.sendError("command.offline", "That player is offline", args.getSender());
				return;
			}
			player.getInventory().addItem(SpellTome.createSpellTome(spell));
			Language.sendMessage("command.spelltome.complete", "[PLAYER] has been given a [SPELL] spelltome", args
					.getSender(), "[PLAYER]", player.getName(), "[SPELL]", ChatColor.GOLD
					+ WordUtils.capitalize(spell.getName()));
			Language.sendMessage("command.spelltome.complete.self", "You have been given a [SPELL] spelltome", args
					.getSender(), "[SPELL]", ChatColor.GOLD + WordUtils.capitalize(spell.getName()));
		}
	}

	@Command(name = "aspects",
			permission = "zephyrus.command.aspects",
			description = "Checks the aspects on an itemstack",
			usage = "/aspects")
	public void onAspects(CommandArgs args) {
		if (args.getArgs().length == 0) {
			if (!args.isPlayer()) {
				Language.sendError("command.ingame", "This command is only available in game", args.getSender());
				return;
			}
			if (args.getPlayer().getItemInHand() == null) {
				Language.sendError("command.aspects.noitem", "You need to have an item in your hand to analyze", args
						.getSender());
				return;
			}
			AspectList list = Zephyrus.getAspectManager().getAspects(args.getPlayer().getItemInHand());
			Language.sendMessage("command.aspects.aspecttitle", "Aspects: ", args.getSender());
			if (list == null) {
				Language.sendError("command.aspects.none", "None", args.getSender());
				return;
			}
			for (Entry<Aspect, Integer> entry : list.getAspectMap().entrySet()) {
				Language.sendMessage("command.aspects.aspects", "[NAME] x[AMOUNT] - [DESC]", args.getSender(), "[NAME]", entry
						.getKey().getColor()
						+ Language.get("aspect." + entry.getKey().name().toLowerCase() + ".name", entry.getKey()
								.getDefaultName()) + ChatColor.WHITE, "[DESC]", Language.get("aspect."
						+ entry.getKey().name() + ".desc", entry.getKey().getDefaultDescription()), "[AMOUNT]", entry
						.getValue() + "");
			}
		} else {
			Material mat = Material.getMaterial(args.getArgs()[0].toUpperCase());
			if (mat != null) {
				AspectList list = Zephyrus.getAspectManager().getAspects(new ItemStack(mat));
				Language.sendMessage("command.aspects.aspecttitle", "Aspects: ", args.getSender());
				if (list == null) {
					Language.sendError("command.aspects.none", "None", args.getSender());
					return;
				}
				for (Entry<Aspect, Integer> entry : list.getAspectMap().entrySet()) {
					Language.sendMessage("command.aspects.aspects", "[NAME] x[AMOUNT] - [DESC]", args.getSender(), "[NAME]", Language
							.get("aspect." + entry.getKey().name().toLowerCase() + ".name", entry.getKey()
									.getDefaultName()), "[DESC]", Language.get("aspect." + entry.getKey().name()
							+ ".desc", entry.getKey().getDefaultDescription()), "[AMOUNT]", entry.getValue() + "");
				}
			}
		}
	}

	@Command(name = "aspects.list",
			permission = "zephyrus.command.aspects",
			description = "List all aspects",
			usage = "/aspects list")
	public void onAspectList(CommandArgs args) {
		if (args.isPlayer()) {
			Message message = new Message("command.aspects.aspecttitle", "Aspects: ", MessageColor.RED,
					MessageFormatting.BOLD);
			for (Aspect aspect : Aspect.values()) {
				message.addComponent(new MessageComponent(Language.get("aspect." + aspect.name().toLowerCase()
						+ ".name", aspect.getDefaultName()), MessageColor.valueOf(aspect.getColor().name()))
						.setHoverEvent(MessageHoverEvent.TEXT, Language.get("aspect." + aspect.name().toLowerCase()
								+ ".desc", aspect.getDefaultDescription())));
				message.addComponent(new MessageComponent(" - ", MessageColor.BLACK, MessageFormatting.BOLD));
			}
			message.sendMessage(args.getPlayer());
		}
	}

	@Command(name = "bind",
			permission = "zephyrus.command.bind",
			description = "Binds a spell to the wand held in your hand",
			usage = "/bind <spell>")
	public void onBind(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", "This command is only available in game", args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			Language.sendMessage("command.spell", "No spell specified", args.getSender());
			return;
		}
		User user = Zephyrus.getUser(args.getPlayer().getName());
		Spell spell = Zephyrus.getSpell(args.getArgs()[0]);
		Item item = Zephyrus.getItem(args.getPlayer().getItemInHand());
		if (item == null || !(item instanceof Wand)) {
			Language.sendError("command.bind.nowand", "You need to be equiped with a wand to bind a spell", args
					.getSender());
			return;
		}
		Wand wand = (Wand) item;
		if (spell == null || !user.isSpellLearned(spell)) {
			Language.sendError("command.bind.learn", "You have not learned [SPELL]", args.getSender(), "[SPELL]", args
					.getArgs()[0]);
			return;
		}
		if (!spell.getClass().isAnnotationPresent(Bindable.class)) {
			Language.sendError("command.bind.unable", "That spell cannot be bound to a wand", args.getSender());
			return;
		}
		if (wand.getBindingAbilityLevel() < spell.getRequiredLevel()) {
			Language.sendError("commabd.bind.level", "That spell requires a higher level wand to bind to", args
					.getSender());
			return;
		}
		UserBindSpellEvent event = new UserBindSpellEvent(args.getPlayer(), spell, wand);
		Bukkit.getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			ItemStack stack = args.getPlayer().getItemInHand();
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(wand.getBoundName(spell));
			meta.setLore(wand.getBoundLore(spell));
			stack.setItemMeta(meta);
			args.getPlayer().setItemInHand(stack);
			Language.sendMessage("command.bind.complete", "Successfully bound [SPELL] to your wand", args.getSender(), "[SPELL]", ChatColor.GOLD
					+ spell.getName() + ChatColor.WHITE);
		}
	}

	@Command(name = "unbind",
			aliases = { "bind.none", "bind.remove" },
			permission = "zephyrus.command.bind",
			description = "Removes the bound spell from the wand",
			usage = "/unbind")
	public void onBindNone(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", "This command is only available in game", args.getSender());
			return;
		}
		Item item = Zephyrus.getItem(args.getPlayer().getItemInHand());
		if (item == null || !(item instanceof Wand)) {
			Language.sendError("command.unbind.nowand", "You need to be equiped with a wand to unbind a spell", args
					.getSender());
			return;
		}
		Wand wand = (Wand) item;
		ItemStack stack = args.getPlayer().getItemInHand();
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName(wand.getName());
		meta.setLore(wand.getLore());
		stack.setItemMeta(meta);
		args.getPlayer().setItemInHand(stack);
		Language.sendMessage("command.unbind.complete", "Successfully unbound all spells from your wand", args
				.getSender());
	}

	@Completer(name = "spelltome", aliases = { "tome" })
	public List<String> onTomeComplete(CommandArgs cmd) {
		List<String> list = new ArrayList<String>();
		for (String s : Zephyrus.getSpellNameSet()) {
			list.add(s);
		}
		if (cmd.getArgs().length == 0) {
			return list;
		}
		String spell = cmd.getArgs()[0];
		List<String> newList = new ArrayList<String>();
		for (String s : list) {
			if (s.startsWith(spell.toLowerCase())) {
				newList.add(s);
			}
		}
		return newList;
	}

}
