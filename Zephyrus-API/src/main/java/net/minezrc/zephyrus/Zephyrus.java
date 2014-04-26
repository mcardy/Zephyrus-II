package net.minezrc.zephyrus;

import java.util.List;
import java.util.Set;

import net.minezrc.zephyrus.aspect.AspectManager;
import net.minezrc.zephyrus.command.CommandManager;
import net.minezrc.zephyrus.enchant.EnchantManager;
import net.minezrc.zephyrus.hook.PluginHookManager;
import net.minezrc.zephyrus.item.Item;
import net.minezrc.zephyrus.item.ItemManager;
import net.minezrc.zephyrus.nms.NMSManager;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellManager;
import net.minezrc.zephyrus.state.StateManager;
import net.minezrc.zephyrus.user.User;
import net.minezrc.zephyrus.user.UserManager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Zephyrus - Zephyrus.java<br>
 * Zephyrus's main API access class. Allows access to all of Zephyrus's API
 * features and handles singleton feature managers.
 * 
 * @author minnymin3
 * 
 */

public class Zephyrus {

	private static AspectManager aspectManager;
	private static CommandManager commandManager;
	private static EnchantManager enchantmentManager;
	private static PluginHookManager hookManager;
	private static ItemManager itemManager;
	private static NMSManager nmsManager;
	private static Plugin plugin;
	private static SpellManager spellManager;
	private static StateManager stateManager;
	private static UserManager userManager;

	/**
	 * Gets the AspectManager singleton
	 * 
	 * @return The AspectManager instance
	 */
	public static AspectManager getAspectManager() {
		return aspectManager;
	}

	/**
	 * Gets the CommandManager singleton
	 * 
	 * @return The CommandManager instance
	 */
	public static CommandManager getCommandManager() {
		return commandManager;
	}

	/**
	 * Gets the EnchantManager singleton
	 * 
	 * @return The EnchantManager instance
	 */
	public static EnchantManager getEnchantmentManager() {
		return enchantmentManager;
	}

	/**
	 * Gets the PluginHookManager singleton
	 * 
	 * @return The PluginHookManager instance
	 */
	public static PluginHookManager getHookManager() {
		return hookManager;
	}

	/**
	 * Gets an item registered with Zephyrus by the given itemstack
	 * 
	 * @param item The itemstack of the item
	 * @return An item registered or Zephyrus or null if no item was found
	 */
	public static Item getItem(ItemStack item) {
		return itemManager.getItem(item);
	}

	/**
	 * Gets an item registered with Zephyrus by the given name
	 * 
	 * @param name The name of the item
	 * @return An item registered with Zephyrus or null if no item was found
	 */
	public static Item getItem(String name) {
		return itemManager.getItem(name);
	}

	/**
	 * Gets the ItemManager singleton
	 * 
	 * @return The ItemManager instance
	 */
	public static ItemManager getItemManager() {
		return itemManager;
	}

	/**
	 * Gets the NetMinecraftSource Manager singleton
	 * 
	 * @return The NMSManager instance
	 */
	public static NMSManager getNMSManager() {
		return nmsManager;
	}

	/**
	 * Gets the Zephyrus plugin instance loaded by Bukkit
	 * 
	 * @return The Zephyrus plugin instance
	 */
	public static Plugin getPlugin() {
		return plugin;
	}

	/**
	 * Gets a spell from its class
	 * 
	 * @param spellClass The class of the spell
	 * @return A spell loaded by Zephyrus or null if no spell is found
	 */
	public static Spell getSpell(Class<? extends Spell> spellClass) {
		return spellManager.getSpell(spellClass);
	}

	/**
	 * Gets a spell from its recipe
	 * 
	 * @param recipe The recipe to get
	 * @return A spell loaded by Zephyrus or null if no spell is found
	 */
	public static List<Spell> getSpell(Set<ItemStack> recipe) {
		return spellManager.getSpells(recipe);
	}

	/**
	 * Gets a spell from its name
	 * 
	 * @param name The name to get
	 * @return A spell loaded by Zephyrus or null if no spell is found
	 */
	public static Spell getSpell(String name) {
		return spellManager.getSpell(name);
	}

	/**
	 * Gets the spell config
	 * 
	 * @return A YmlConfigFile for spells.yml
	 */
	public static YmlConfigFile getSpellConfig() {
		return spellManager.getConfig();
	}

	/**
	 * Gets the SpellManager singleton
	 * 
	 * @return The SpellManager instance
	 */
	public static SpellManager getSpellManager() {
		return spellManager;
	}

	/**
	 * Creates a set full of all the names of all registered spells
	 * 
	 * @return A set filled with the names of all the registered spells
	 */
	public static Set<String> getSpellNameSet() {
		return spellManager.getSpellNameSet();
	}

	/**
	 * Creates a set full of all registered spells
	 * 
	 * @return A set filled with all the registered spells
	 */
	public static Set<Spell> getSpellSet() {
		return spellManager.getSpellSet();
	}

	/**
	 * Creates a spelltome ItemStack with the given spell attached
	 * 
	 * @param spell The spell to attach
	 * @return A new ItemStack with all required attributes for a spelltome
	 */
	public static ItemStack getSpellTome(Spell spell) {
		return spellManager.getSpellTome(spell);
	}

	/**
	 * Gets the StateManager singleton
	 * 
	 * @return The StateManager instance
	 */
	public static StateManager getStateManager() {
		return stateManager;
	}

	/**
	 * Gets a user from the given player
	 * 
	 * @param player The player to get
	 * @return A user linked to the provided player or null if that player is
	 *         not online
	 */
	public static User getUser(Player player) {
		return userManager.getUser(player);
	}

	/**
	 * Gets a user from the given name
	 * 
	 * @param playerName The name of the player to get
	 * @return A user linked to the provided player or null if that player is
	 *         not online
	 */
	public static User getUser(String playerName) {
		return userManager.getUser(playerName);
	}

	/**
	 * Gets the UserManager singleton
	 * 
	 * @return The UserManager instance
	 */
	public static UserManager getUserManager() {
		return userManager;
	}

	/**
	 * Registers the given item with Zephyrus
	 * 
	 * @param item The item to register
	 */
	public static void registerItem(Item item) {
		itemManager.registerItem(item);
	}

	/**
	 * Registers the given spell with Zephyrus
	 * 
	 * @param spell The spell to register
	 */
	public static void registerSpell(Spell spell) {
		spellManager.registerSpell(spell);
	}

	/**
	 * Attempts to set the AspectManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setAspectManager(AspectManager manager) {
		if (Zephyrus.aspectManager != null) {
			throw new IllegalStateException("Zephyrus aspect manager already set! It cannot be set twice!");
		}
		aspectManager = manager;
	}

	/**
	 * Attempts to set the CommandManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setCommandManager(CommandManager manager) {
		if (Zephyrus.commandManager != null) {
			throw new IllegalStateException("Zephyrus command manager already set! It cannot be set twice!");
		}
		commandManager = manager;
	}

	/**
	 * Attempts to set the EnchantManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setEnchantmentManager(EnchantManager manager) {
		if (Zephyrus.enchantmentManager != null) {
			throw new IllegalStateException("Zephyrus enchantment manager already set! It cannot be set twice!");
		}
		enchantmentManager = manager;
	}

	/**
	 * Attempts to set the PluginHookManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setHookManager(PluginHookManager manager) {
		if (Zephyrus.hookManager != null) {
			throw new IllegalStateException("Zephyrus hook manager already set! It cannot be set twice!");
		}
		hookManager = manager;
	}

	/**
	 * Attempts to set the ItemManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setItemManager(ItemManager manager) {
		if (Zephyrus.itemManager != null) {
			throw new IllegalStateException("Zephyrus item manager already set! It cannot be set twice!");
		}
		itemManager = manager;
	}

	/**
	 * Attempts to set the NMSManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setNMSManager(NMSManager manager) {
		if (Zephyrus.nmsManager != null) {
			throw new IllegalStateException("Zephyrus NMS manager already set! It cannot be set twice!");
		}
		nmsManager = manager;
	}

	/**
	 * Attempts to set the plugin instance loaded by Bukkit
	 * 
	 * @param plugin The plugin to set
	 * @throws IllegalStateException when the plugin is already set
	 */
	public static void setPlugin(Plugin plugin) {
		if (Zephyrus.plugin != null) {
			throw new IllegalStateException("Zephyrus plugin already registered! " + plugin.getName()
					+ " cannot be registered as well.");
		}
		Zephyrus.plugin = plugin;
		Zephyrus.plugin.getLogger().info("Initialized Zephyrus v" + plugin.getDescription().getVersion());
	}

	/**
	 * Attempts to set the SpellManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setSpellManager(SpellManager manager) {
		if (Zephyrus.spellManager != null) {
			throw new IllegalStateException("Zephyrus spell manager already set! It cannot be set twice!");
		}
		spellManager = manager;
	}

	/**
	 * Attempts to set the StateManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setStateManager(StateManager manager) {
		if (Zephyrus.stateManager != null) {
			throw new IllegalStateException("Zephyrus state manager already set! It cannot be set twice!");
		}
		stateManager = manager;
	}

	/**
	 * Attempts to set the UserManager singleton
	 * 
	 * @param manager The manager to set
	 * @throws IllegalStateException when the manager is already set
	 */
	public static void setUserManager(UserManager manager) {
		if (Zephyrus.userManager != null) {
			throw new IllegalStateException("Zephyrus user manager already set! It cannot be set twice!");
		}
		userManager = manager;
	}

	/**
	 * A method to null all static variables to avoid memory leaks on reloads
	 */
	public static void unload() {
		Zephyrus.commandManager = null;
		Zephyrus.enchantmentManager = null;
		Zephyrus.itemManager = null;
		Zephyrus.nmsManager = null;
		Zephyrus.spellManager = null;
		Zephyrus.stateManager = null;
		Zephyrus.userManager = null;
		Zephyrus.plugin = null;
	}

	private Zephyrus() {
	}

}
