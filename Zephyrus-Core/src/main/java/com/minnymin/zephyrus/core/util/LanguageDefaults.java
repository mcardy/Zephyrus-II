package com.minnymin.zephyrus.core.util;

import org.bukkit.ChatColor;

/**
 * Zephyrus - LanguageDefaults.java
 * 
 * @author minnymin3
 * 
 */

class LanguageDefaults {

	static void addKeys() {
		// General command
		Language.add("command.spell", "No spell specified");
		Language.add("command.player", "No player specified");
		Language.add("command.offline", "That player is offline");
		Language.add("command.ingame", "This command is only available in game");
		Language.add("command.noperm", "You do not have permission to perform that action");

		// Spelltome command
		Language.add("command.spelltome.badspell", "That is not a spell");
		Language.add("command.spelltome.complete.self", "You have been given a [SPELL] spelltome");
		Language.add("command.spelltome.badspell", "That is not a spell");
		Language.add("command.spelltome.complete", "[PLAYER] has been given a [SPELL] spelltome");

		// Aspect command
		Language.add("command.aspects.noitem", "You need to have an item in your hand to analyze");
		Language.add("command.aspects.aspecttitle", "Aspects: ");
		Language.add("command.aspects.none", "None");
		Language.add("command.aspects.aspects", "[NAME] x[AMOUNT] - [DESC]");

		// Book command
		Language.add("command.book.max", "You have reached the maximum amount of [BOOK] books allowed");
		Language.add("command.book.info", "You recieved a Zephyronomicon");
		Language.add("command.book.recipe", "You recieved a spell recipe book! Level [START-LEVEL] to [END-LEVEL]");
		Language.add("command.book.recipe.reqlevel", "You need to be level [LEVEL] to get the teir [TEIR] recipe book");
		Language.add("command.book.unknown", "Book choices are: 'recipe', 'recipe [1, 2, 3, 4, 5]' and 'info'");

		// Bind command
		Language.add("command.bind.nowand", "You need to be equiped with a wand to bind a spell");
		Language.add("command.bind.learn", "You have not learned [SPELL]");
		Language.add("command.bind.unable", "That spell cannot be bound to a wand");
		Language.add("commabd.bind.level", "That spell requires a higher level wand to bind to");
		Language.add("command.bind.complete", "Successfully bound [SPELL] to your wand");

		// Unbind command
		Language.add("command.unbind.nowand", "You need to be equiped with a wand to unbind a spell");
		Language.add("command.unbind.complete", "Successfully unbound all spells from your wand");

		// Learn command
		Language.add("command.learn.badspell", "That is not a spell");
		Language.add("command.learn.complete", "You have learned [SPELL]");

		// Level command
		Language.add("command.level.display", ChatColor.GRAY + "Level [LEVEL], [AMOUNT] {[BAR]}");
		Language.add("command.level.add.complete", "Levelled up [PLAYER]");
		Language.add("command.level.add.number", "Got [STRING] but expected a number");

		// Mana command
		Language.add("command.mana.display", ChatColor.GRAY + "Mana: [AMOUNT] {[BAR]}");
		Language.add("command.mana.restore.complete", "Your magical powers feel restored");

		// Protections keys
		Language.add("user.target.block.faction", "You cannot target blocks inside of a faction");
		Language.add("spell.cast.faction", "You cannot cast spells inside of a faction");
		Language.add("user.target.entity.faction", "You cannot target that player");
		Language.add("user.target.block.town", "You cannot target blocks inside of a town");
		Language.add("user.target.entity.towny", "You cannot target that entity here");
		Language.add("spell.cast.town", "You cannot cast spells inside of towns");
		Language.add("user.target.block.worldguard", "You cannot target that block here");
		Language.add("spell.cast.region", "You cannot cast spells inside of this region");
		Language.add("user.target.entity.worldguard", "You cannot target that player here");

		// Item listener
		Language.add("item.arcane.max", "This item is already at max level");
		Language.add("crafting.reqwandlevel", "Your wand is not powerful enough to craft [SPELL]");
		Language.add("crafting.reqplayerlevel", "You are not powerful enough to craft [SPELL]");
		Language.add("crafting.nospell",
				"You cannot craft any spells with those aspects. Consult the SpellBook for more information.");
		Language.add("item.arcane.create", ChatColor.GOLD + "You have created an Arcane Leveller");
		Language.add("item.wand.bound", "Current bound spell: " + ChatColor.GOLD + "[SPELL]");
		Language.add("item.wand.nobound", "There is no spell bound to this wand! Bind one with /bind <spell>");
		Language.add("crafting.item.requiredlevel", "You lack the knowledge of level [LEVEL] required to craft [ITEM]");
		Language.add("item.delay", "You still need to wait [SECONDS] seconds to use this item");
		Language.add("crafting.nopermission", "You do not have permission to craft [ITEM]");

		// Spell Book item
		Language.add("item.wand.nobound", "There is no spell bound to this wand! Bind one with /bind <spell>");
		Language.add("command.bind.complete", "Successfully bound [SPELL] to your wand");

		// Spell Tome item
		Language.add("item.spelltome.broken", "Something went wrong! Spell not found...");
		Language.add("item.spelltome.learned", "You already know [SPELL]!");
		Language.add("item.spelltome.level", "You need to have the knowledge of level [LEVEL] to learn [SPELL]!");
		Language.add("item.spelltome.requiredspell", "You do not have the knowledge of [SPELL]");
		Language.add("item.spelltome.complete", "You have successfully learned ");

		// Action items
		Language.add("item.gemoflightning.failure", "You can't strike lightning there!");
		Language.add("item.rodoffire.failure", "You can't shoot a fireball there!");

		// Shops
		Language.add("shop.create.noeconomy", "No economy system found. Cannot create shop.");
		Language.add("shop.permission", "You do not have permission to create shops");
		Language.add("shop.item.create.amount", "Cost on line 3 not valid. Expected a number.");
		Language.add("shop.item.create.item", "Item on line 2 not valid. No item found by that name.");
		Language.add("shop.item.create.complete",
				"Successfully created an ItemShop selling the [ITEM] item for [AMOUNT]");
		Language.add("shop.item.use.amount", "You do not have enough money to buy this item: [AMOUNT]");
		Language.add("shop.item.use.complete", "You have successfully purchased ");
		Language.add("shop.item.use.full", "Your inventory is full! Cannot add item!");
		Language.add("shop.spell.create.amount", "Cost on line 3 not valid. Expected a number.");
		Language.add("shop.spell.create.spell", "Spell on line 2 not valid. No spell found by that name.");
		Language.add("shop.spell.create.complete",
				"Successfully created a SpellShop selling the [SPELL] spell for [AMOUNT]");
		Language.add("shop.spell.use.broken", "Something went wrong! Spell not found...");
		Language.add("shop.spell.use.level", "You are not high enough level to learn this spell: [LEVEL]");
		Language.add("shop.spell.use.learned", "You already know [SPELL]!");
		Language.add("shop.spell.use.level", "You do not have the spells required to learn this spell: [SPELL]");
		Language.add("shop.spell.use.amount", "You do not have enough money to buy this spell: [AMOUNT]");
		Language.add("shop.spell.use.complete", "You have successfully purchased ");
		Language.add("shop.wand.create.amount", "Cost on line 3 not valid. Expected a number.");
		Language.add("shop.wand.create.wand", "Wand on line 2 not valid. No wand found by that name.");
		Language.add("shop.wand.create.complete",
				"Successfully created a WandShop selling the [WAND] wand for [AMOUNT]");
		Language.add("shop.wand.use.amount", "You do not have enough money to buy this wand: [AMOUNT]");
		Language.add("shop.wand.use.complete", "You have successfully purchased ");
		Language.add("shop.wand.use.full", "Your inventory is full! Cannot add item!");

		// Spells
		{
			// Armor
			Language.add("spell.armor.applied", ChatColor.GOLD + "Your skin is hardened with magic and gold");
			Language.add("spell.armor.fail", "You cannot be waring armour when casting this spell");

			// Conjure
			Language.add("spell.conjure.noitem", "Specify an item to conjure! /cast conjure <id>:<data> [amount]");
			Language.add("spell.conjure.badid", "Invalid item!");
			Language.add("spell.conjure.badamount", "Invalid amount!");
			Language.add("spell.conjure.baditem", "You cannot conjure that item!");
			Language.add("spell.conjure.mana", "You do not have enough mana to conjure that item [MANA]");
			Language.add("spell.conjure.complete", "Successfully conjured [AMOUNT] [ITEM]");

			// Jail
			Language.add("spell.jail.break", "You cannot break jail blocks!");

			// Disorient
			Language.add("spell.notarget", "You do not have a target");

			// Blink
			Language.add("spell.blink.failure", "You can't blink there!");

			// Home
			Language.add("spell.home.needset", "You need to set your home with '/cast home set'");
			Language.add("spell.home.set", "Home successfully set!");
			Language.add("spell.home.success", "Going home...");

			// Phase
			Language.add("spell.phase.fail", "You cannot phase through that block");

			// Feed
			Language.add("spell.feed.full", "You are already full!");

			// Repair
			Language.add("spell.repair.success", "Your tool feels a bit stronger");
			Language.add("spell.repair.fail", "You can't repair that item");

			// Clock
			Language.add("spell.clock.time", "It is currently [TIME]");
			Language.add("spell.clock.usage", "Expected number or <day|night|noon|midnight>");

			// Detect
			Language.add("spell.detect.nearby", ChatColor.GRAY + "Nearby mobs:");
			Language.add("spell.detect.none", ChatColor.GRAY + "None...");

			// Dig
			Language.add("spell.dig.blacklist", "You cannot break that block");

			// Prospect
			Language.add("spell.prospect.ores", ChatColor.GRAY + "Ores found: [ORES]");
			Language.add("spell.prospect.noores", ChatColor.GRAY + "No ores found...");

			// SuperHeat
			Language.add("spell.superheat.fail", "That block can't be superheated");

			// Track
			Language.add("spell.track.notarget", "Specify a target entity");
			Language.add("spell.track.badtarget", "That is not an entity");
			Language.add("spell.track.title", ChatColor.GRAY + "There is a [ENTITY] nearby");
			Language.add("spell.track.location", ChatColor.GRAY + "Travel [X], [Y], [Z]");
			Language.add("spell.track.none", "There are no entities of that type nearby");
		}
		
		// States
		{
			// Armor
			Language.add("spell.armor.warning", "Your magic armor begins to fade");

			// Build
			Language.add("spell.build.applied", ChatColor.GRAY + "You feel your reach extend");
			Language.add("spell.build.removed", ChatColor.GRAY + "You feel your reach shorten back to normal");
			Language.add("spell.build.warning", ChatColor.GRAY + "You feel your begin to shorten back to normal");
			
			// Feather
			Language.add("spell.feather.start", "You feel like you can float like a feather");
			Language.add("spell.feather.end", "You feel heavy again");
			Language.add("spell.feather.warn", "You begin to feel heavy");
			
			// FireShield
			Language.add("spell.fireshield.removed", "Your shield fades to cold air");
			Language.add("spell.fireshield.warning", "Your shield begins to fade");
			
			// FlameStep
			Language.add("spell.flamestep.applied", "You feel the power of fire running through your pixels");
			Language.add("spell.flamestep.removed", "You feel the power of fire dissapate throughout");
			Language.add("spell.flamestep.warning", "You feel the power of fire fading");
			
			// Fly
			Language.add("spell.fly.applied", "You have conjured wings");
			Language.add("spell.fly.removed", "Your wings have faded");
			Language.add("spell.fly.warning", "Your wings begin to fade");
			
			// Shield
			Language.add("spell.shield.applied", "A shield of magical energy surrounds you");
			Language.add("spell.shield.applied", "Your magic shield dissapates");
			
			// Speed
			Language.add("spell.speed.applied", "You feel your legs speed up");
			Language.add("spell.speed.removed", "You feel your legs slow down");
			Language.add("spell.speed.removed", "You feel your legs begin to slow down");
			
			// Zephyr
			Language.add("spell.zephyr.removed", "The air becomes still around you");
			Language.add("spell.zephyr.warning", "The air begins to slow down around you");
		}
		
		// User
		Language.add("game.levelup", ChatColor.AQUA + "You leveled up to level [LEVEL]");
		Language.add("command.cast.learn", "You have not learned [SPELL]");
		Language.add("command.cast.mana", "You do not have enough mana to cast [SPELL] [MANA]");
		Language.add("spell.notarget", "You do not have a target");
		Language.add("command.cast.continuous.mana", "You do not have enough mana to continue cast [SPELL] [MANA]");
	}

}
