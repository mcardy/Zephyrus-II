package com.minnymin.zephyrus.core.item;

import java.util.Arrays;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.chat.Message;
import com.minnymin.zephyrus.core.chat.MessageComponent;
import com.minnymin.zephyrus.core.chat.MessageEvent.MessageHoverEvent;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageColor;
import com.minnymin.zephyrus.core.chat.MessageForm.MessageFormatting;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.event.UserLearnSpellEvent;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - SpellTome.java
 * 
 * @author minnymin3
 * 
 */

public class SpellTome implements Listener {

	public static ItemStack createSpellTome(Spell spell) {
		ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) item.getItemMeta();
		meta.setDisplayName(Language.get("item.spelltome.name", ChatColor.GOLD + "SpellTome"));
		meta.setTitle(Language.get("item.spelltome.name", ChatColor.GOLD + "SpellTome"));
		meta.setLore(Arrays.asList(ChatColor.GRAY + WordUtils.capitalize(spell.getName())));
		meta.addPage(spell.getDescription()
				+ "\n\n"
				+ Language.get("item.spelltome.cast", "Cast this spell with " + ChatColor.DARK_AQUA + "/cast [SPELL]")
						.replace("[SPELL]", WordUtils.capitalize(spell.getName())) + "\n\n"
				+ Language.get("item.spelltome.learn", ChatColor.GRAY + "Learn this spell by left-clicking this book"));
		item.setItemMeta(meta);
		return item;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		try {
			Player player = event.getPlayer();
			if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
				return;
			}
			if (player.getItemInHand() == null
					|| !player.getItemInHand().hasItemMeta()
					|| !player.getItemInHand().getItemMeta().hasDisplayName()
					|| !player.getItemInHand().getItemMeta().getDisplayName()
							.equalsIgnoreCase(Language.get("item.spelltome.name", ChatColor.GOLD + "SpellTome"))) {
				return;
			}
			User user = Zephyrus.getUser(player.getName());
			Spell spell = Zephyrus
					.getSpell(ChatColor.stripColor(player.getItemInHand().getItemMeta().getLore().get(0)));
			if (spell == null) {
				Language.sendError("item.spelltome.broken", player);
				return;
			}
			if (user.isSpellLearned(spell)) {
				Language.sendError("item.spelltome.learned", player, "[SPELL]", spell.getName());
				return;
			}
			if (user.getLevel() < spell.getRequiredLevel()) {
				Language.sendError("item.spelltome.level", player, "[SPELL]", spell.getName(), "[LEVEL]",
						spell.getRequiredLevel() + "");
				return;
			}
			if (spell.getClass().isAnnotationPresent(Prerequisite.class)
					&& !user.isSpellLearned(Zephyrus.getSpell(((Prerequisite) spell.getClass().getAnnotation(
							Prerequisite.class)).requiredSpell()))) {
				Language.sendError("item.spelltome.requiredspell", player, "[SPELL]", ((Prerequisite) spell)
						.requiredSpell().getName());
				return;
			}
			UserLearnSpellEvent learn = new UserLearnSpellEvent(player, spell);
			Bukkit.getPluginManager().callEvent(learn);
			if (!learn.isCancelled()) {
				user.addSpell(spell);
				new Message("item.spelltome.complete", MessageColor.GRAY, MessageFormatting.NONE).addComponent(
						new MessageComponent(spell.getName(), MessageColor.GOLD, MessageFormatting.BOLD).setHoverEvent(
								MessageHoverEvent.TEXT, spell.getDescription())).sendMessage(player);
				player.setItemInHand(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
