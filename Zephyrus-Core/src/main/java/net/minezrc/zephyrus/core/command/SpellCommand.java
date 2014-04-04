package net.minezrc.zephyrus.core.command;

import java.util.ArrayList;
import java.util.List;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.item.Zephyronomicon;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.core.util.command.Command;
import net.minezrc.zephyrus.core.util.command.CommandArgs;
import net.minezrc.zephyrus.core.util.command.Completer;
import net.minezrc.zephyrus.event.UserPostCastEvent;
import net.minezrc.zephyrus.event.UserPreCastEvent;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Zephyrus - CastingCommands.java
 * 
 * @author minnymin3
 * 
 */

public class SpellCommand {

	@Command(name = "book")
	public void onBookCommand(CommandArgs args) {
		if (!args.isPlayer()) {
			Language.sendError("command.ingame", "This command is only available in game", args.getSender());
			return;
		}
		args.getPlayer().getInventory().addItem(Zephyronomicon.createZephyricRecipeBook(0, 10));
	}

	@Command(name = "cast",
			permission = "zephyrus.cast",
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
		if (spell == null || !user.isSpellLearned(spell)) {
			Language.sendError("command.cast.learn", "You have not learned [SPELL]", args.getSender(), "[SPELL]", args
					.getArgs()[0]);
			return;
		}
		if (user.getMana() < spell.getManaCost()) {
			Language.sendError("command.cast.mana", "You do not have enough mana to cast [SPELL] [MANA]", args
					.getSender(), "[SPELL]", spell.getName(), "[MANA]", ChatColor.RED + "" + user.getMana()
					+ ChatColor.GRAY + "/" + ChatColor.GREEN + spell.getManaCost());
			return;
		}
		UserPreCastEvent preCast = new UserPreCastEvent(user, spell, 1, args.getArgs());
		Bukkit.getPluginManager().callEvent(preCast);
		if (!preCast.isCancelled()) {
			CastResult result = spell.onCast(user, 1, args.getArgs());
			if (result == CastResult.NORMAL_SUCCESS) {
				user.drainMana(spell.getManaCost());
				user.addLevelProgress(spell.getXpReward());
				UserPostCastEvent postCast = new UserPostCastEvent(user, spell, 1, args.getArgs());
				Bukkit.getPluginManager().callEvent(postCast);
			}
		}
	}

	@Command(name = "learn",
			permission = "zephyrus.learn",
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

	@Completer(name = "cast")
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

	@Completer(name = "learn")
	public List<String> spelltomeComplete(CommandArgs cmd) {
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
