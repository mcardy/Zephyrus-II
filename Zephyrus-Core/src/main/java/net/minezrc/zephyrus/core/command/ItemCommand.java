package net.minezrc.zephyrus.core.command;

import java.util.ArrayList;
import java.util.List;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.spell.SpellTome;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.command.Command;
import net.minezrc.zephyrus.core.util.command.CommandArgs;
import net.minezrc.zephyrus.core.util.command.Completer;
import net.minezrc.zephyrus.event.UserBindSpellEvent;
import net.minezrc.zephyrus.item.Item;
import net.minezrc.zephyrus.item.Wand;
import net.minezrc.zephyrus.spell.Bindable;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.user.User;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Zephyrus - ItemCommand.java
 * 
 * @author minnymin3
 * 
 */

public class ItemCommand {

	@Command(name = "spelltome",
			aliases = { "tome" },
			permission = "zephyrus.spelltome.give",
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

	@Command(name = "bind")
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
		UserBindSpellEvent event = new UserBindSpellEvent(args.getPlayer(), spell);
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
