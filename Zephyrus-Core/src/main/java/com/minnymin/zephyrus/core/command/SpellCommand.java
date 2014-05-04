package com.minnymin.zephyrus.core.command;

import java.util.ArrayList;
import java.util.List;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.item.MagicBooks;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.command.Command;
import com.minnymin.zephyrus.core.util.command.CommandArgs;
import com.minnymin.zephyrus.core.util.command.Completer;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.user.User;


/**
 * Zephyrus - CastingCommands.java
 * 
 * @author minnymin3
 * 
 */

public class SpellCommand {

	@Command(name = "book",
			permission = "zephyrus.command.book",
			description = "Allows each player to get one Zephyronomicon and a Mystic Recipe Book",
			usage = "/book <recipe|info>")
	public void onBookCommand(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", "This command is only available in game", args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			args.getPlayer().getInventory().addItem(MagicBooks.createZephyronomicon());
			Language.sendMessage("command.book.info", "You recieved a Zephyronomicon", args.getSender());
			return;
		}
		if (args.getArgs()[0].equalsIgnoreCase("info")) {
			args.getPlayer().getInventory().addItem(MagicBooks.createZephyronomicon());
			Language.sendMessage("command.book.info", "You recieved a Zephyronomicon", args.getSender());
		} else if (args.getArgs()[0].equalsIgnoreCase("recipe")) {
			args.getPlayer().getInventory().addItem(MagicBooks.createZephyricRecipeBook(0, 50));
			Language.sendMessage("command.book.recipe", "You recieved a spell recipe book!", args.getSender());
		} else {
			Language.sendError("command.book.unknown", "Only books are: recipe and info", args.getSender());
		}
	}

	@Command(name = "cast",
			permission = "zephyrus.command.cast",
			description = "The mage's spell casting",
			usage = "/cast <spell> [args]")
	public void onCastCommand(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", "This command is only available in game", args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			Language.sendError("command.spell", "No spell specified", args.getSender());
			return;
		}
		User user = Zephyrus.getUser(args.getPlayer().getName());
		Spell spell = Zephyrus.getSpell(args.getArgs()[0]);
		user.castSpell(spell, 1, args.getArgs());
	}

	@Command(name = "learn", aliases = { "teach" },
			permission = "zephyrus.command.teach",
			description = "The power of knowledge",
			usage = "/learn <spell>")
	public void onLearnCommand(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", "This command is only available in game", args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			Language.sendError("command.spell", "No spell specified", args.getSender());
			return;
		}
		User user = Zephyrus.getUser(args.getPlayer().getName());
		Spell spell = Zephyrus.getSpell(args.getArgs()[0]);
		if (spell == null) {
			Language.sendError("command.learn.badspell", "That is not a spell", args.getSender());
			return;
		}
		user.addSpell(spell);
		Language.sendMessage("command.learn.complete", "You have learned [SPELL]", args.getSender(), "[SPELL]", spell
				.getName());
	}

	@Completer(name = "cast", aliases = { "bind" })
	public List<String> onCastComplete(CommandArgs args) {
		if (args.isPlayer()) {
			List<String> list = Zephyrus.getUser(args.getPlayer().getName()).getLearnedSpells();
			if (args.getArgs().length == 0) {
				return list;
			}
			String spell = args.getArgs()[0];
			List<String> newList = new ArrayList<String>();
			for (String s : list) {
				if (s.startsWith(spell.toLowerCase())) {
					newList.add(s);
				}
			}
			return newList;
		} else {
			return null;
		}
	}

}
