package com.minnymin.zephyrus.core.command;

import java.util.ArrayList;
import java.util.List;

import com.minnymin.zephyrus.Zephyrus;
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
	
	@Command(name = "cast",
			permission = "zephyrus.command.cast",
			description = "The mage's spell casting",
			usage = "/cast <spell> [args]")
	public void onCastCommand(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			Language.sendError("command.spell", args.getSender());
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
			Language.sendError("command.ingame", args.getSender());
			return;
		}
		if (args.getArgs().length == 0) {
			Language.sendError("command.spell", args.getSender());
			return;
		}
		User user = Zephyrus.getUser(args.getPlayer().getName());
		Spell spell = Zephyrus.getSpell(args.getArgs()[0]);
		if (spell == null) {
			Language.sendError("command.learn.badspell", args.getSender());
			return;
		}
		user.addSpell(spell);
		Language.sendMessage("command.learn.complete", args.getSender(), "[SPELL]", spell
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
