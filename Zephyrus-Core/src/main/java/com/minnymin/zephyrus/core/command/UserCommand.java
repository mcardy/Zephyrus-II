package com.minnymin.zephyrus.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.command.Command;
import com.minnymin.zephyrus.core.util.command.CommandArgs;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - UserCommand.java
 * 
 * @author minnymin3
 * 
 */

public class UserCommand {
	
	@Command(name = "level",
			description = "View your level and progress",
			permission = "zephyrus.command.level",
			usage = "/level [player]")
	public void onLevelCommand(CommandArgs args) {
		if (args.getArgs().length < 1) {
			if (args.isPlayer()) {
				User user = Zephyrus.getUser(args.getSender().getName());
				StringBuilder builder = new StringBuilder();
				int level = user.getLevel();
				int next = (level * level * level + 100);
				int percent = (int) (((float) user.getLevelProgress() / (float) next) * 10);
				builder.append(ChatColor.GOLD);
				for (int i = 0; i < 10; i++) {
					if (i == percent) {
						builder.append(ChatColor.GRAY);
					}
					builder.append("=");
				}
				builder.append(ChatColor.DARK_GRAY);
				Language.sendMessage("command.level.display", args.getSender(), "[LEVEL]", user.getLevel() + "",
						"[AMOUNT]", ChatColor.RED + "" + user.getLevelProgress() + ChatColor.GRAY + " / " + next
								+ ChatColor.DARK_GRAY, "[BAR]", builder.toString());
			} else {
				Language.sendError("command.player", args.getSender());
			}
		} else {
			if (args.getSender().hasPermission("zephyrus.command.level.other")) {
				if (Bukkit.getPlayer(args.getArgs()[0]) != null) {
					User user = Zephyrus.getUser(args.getArgs()[0]);
					StringBuilder builder = new StringBuilder();
					int level = user.getLevel();
					int next = (level * level * level + 100);
					int percent = (int) (((float) user.getLevelProgress() / (float) next) * 10);
					builder.append(ChatColor.GOLD);
					for (int i = 0; i < 10; i++) {
						if (i == percent) {
							builder.append(ChatColor.GRAY);
						}
						builder.append("=");
					}
					builder.append(ChatColor.DARK_GRAY);
					Language.sendMessage("command.level.display", args.getSender(), "[LEVEL]", user.getLevel() + "",
							"[AMOUNT]", ChatColor.GOLD + "" + user.getLevelProgress() + ChatColor.GRAY + " / " + next
									+ ChatColor.DARK_GRAY, "[BAR]", builder.toString());
				} else {
					Language.sendError("command.offline", args.getSender());
				}
			} else {
				Language.sendError("command.noperm", args.getSender());
			}
		}
	}

	@Command(name = "level.add",
			permission = "zephyrus.command.level.add",
			description = "Levels up you or someone else",
			usage = "/level add [amount] [player]")
	public void onLevelAdd(CommandArgs args) {
		if (args.getArgs().length == 0) {
			if (args.isPlayer()) {
				User user = Zephyrus.getUser(args.getPlayer().getName());
				int level = user.getLevel();
				user.addLevelProgress((level * level * level + 100));
				Language.sendMessage("command.level.add.complete", args.getSender(), "[PLAYER]", args.getSender()
						.getName());
			} else {
				Language.sendError("command.player", args.getSender());
			}
		} else if (args.getArgs().length == 1) {
			if (Bukkit.getPlayer(args.getArgs()[0]) != null) {
				Player target = Bukkit.getServer().getPlayer(args.getArgs()[0]);
				User user = Zephyrus.getUser(target.getName());
				int level = user.getLevel();
				user.addLevelProgress((level * level * level + 100));
				Language.sendMessage("command.level.add.complete", args.getSender(), "[PLAYER]", args.getSender()
						.getName());
			} else if (args.isPlayer()) {
				try {
					int levels = Integer.parseInt(args.getArgs()[0]);
					User user = Zephyrus.getUser(args.getPlayer());
					for (int i = 0; i < levels; i++) {
						int level = user.getLevel();
						user.addLevelProgress((level * level * level + 100));
					}
					Language.sendMessage("command.level.add.complete", args.getSender(), "[PLAYER]", args.getSender()
							.getName());
				} catch (NumberFormatException ex) {
					Language.sendError("command.level.add.number", args.getSender(), "[STRING]", args.getArgs()[2]);
				}
			} else {
				Language.sendError("command.player", args.getSender());
			}
		} else {
			if (Bukkit.getPlayer(args.getArgs()[0]) != null) {
				try {
					int levels = Integer.parseInt(args.getArgs()[1]);
					Player target = Bukkit.getServer().getPlayer(args.getArgs()[0]);
					User user = Zephyrus.getUser(target.getName());
					for (int i = 0; i < levels; i++) {
						int level = user.getLevel();
						user.addLevelProgress((level * level * level + 100));
					}
					Language.sendMessage("command.level.add.complete", args.getSender(), "[PLAYER]", args.getSender()
							.getName());
				} catch (NumberFormatException ex) {
					Language.sendError("command.level.add.number", args.getSender(), "[STRING]", args.getArgs()[2]);
				}
			} else {
				Language.sendError("command.offline", args.getSender());
			}
		}
	}

	@Command(name = "mana",
			description = "View your mana amount",
			permission = "zephyrus.command.mana",
			usage = "/mana [player]")
	public void onManaCommand(CommandArgs args) {
		if (args.getArgs().length < 1) {
			if (args.isPlayer()) {
				User user = Zephyrus.getUser(args.getSender().getName());
				StringBuilder builder = new StringBuilder();
				int percent = (int) (((float) user.getMana() / (float) user.getMaxMana()) * 10);
				builder.append(ChatColor.DARK_AQUA);
				for (int i = 0; i < 10; i++) {
					if (i == percent) {
						builder.append(ChatColor.GRAY);
					}
					builder.append("=");
				}
				builder.append(ChatColor.DARK_GRAY);
				Language.sendMessage("command.mana.display", args.getSender(), "[LEVEL]", user.getLevel() + "",
						"[AMOUNT]", ChatColor.DARK_AQUA + "" + user.getMana() + ChatColor.GRAY + " / "
								+ ChatColor.GREEN + user.getMaxMana() + ChatColor.DARK_GRAY, "[BAR]",
						builder.toString());
			} else {
				Language.sendError("command.player", args.getSender());
			}
		} else {
			if (args.getSender().hasPermission("zephyrus.command.mana.other")) {
				if (Bukkit.getPlayer(args.getArgs()[0]) != null) {
					User user = Zephyrus.getUser(args.getSender().getName());
					StringBuilder builder = new StringBuilder();
					int percent = (int) (((float) user.getMana() / (float) user.getMaxMana()) * 10);
					builder.append(ChatColor.DARK_AQUA);
					for (int i = 0; i < 10; i++) {
						if (i == percent) {
							builder.append(ChatColor.GRAY);
						}
						builder.append("=");
					}
					builder.append(ChatColor.DARK_GRAY);
					Language.sendMessage("command.mana.display", args.getSender(), "[LEVEL]", user.getLevel() + "",
							"[AMOUNT]", ChatColor.DARK_AQUA + "" + user.getMana() + ChatColor.GRAY + " / "
									+ ChatColor.GREEN + user.getMaxMana() + ChatColor.DARK_GRAY, "[BAR]",
							builder.toString());
				} else {
					Language.sendError("command.offline", args.getSender());
				}
			} else {
				Language.sendError("command.noperm", args.getSender());
			}
		}
	}

	@Command(name = "mana.restore",
			permission = "zephyrus.command.mana.restore",
			description = "Restores your's or someone else's mana",
			usage = "/mana restore [player]")
	public void onManaRestoreCommand(CommandArgs args) {
		if (args.getArgs().length == 0) {
			if (args.isPlayer()) {
				Player player = args.getPlayer();
				User user = Zephyrus.getUser(player.getName());
				user.drainMana(-user.getMaxMana() + user.getMana());
				Language.sendMessage("command.mana.restore.complete", player);
			} else {
				Language.sendError("command.player", args.getSender());
			}
		} else {
			if (Bukkit.getPlayer(args.getArgs()[0]) != null) {
				Player player = Bukkit.getServer().getPlayer(args.getArgs()[1]);
				User user = Zephyrus.getUser(player.getName());
				user.drainMana(-user.getMaxMana() + user.getMana());
				Language.sendMessage("command.mana.restore.complete", player);
			} else {
				Language.sendError("command.offline", args.getSender());
			}
		}
	}

}
