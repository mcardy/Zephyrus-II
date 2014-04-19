package net.minezrc.zephyrus.spell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minezrc.zephyrus.YmlConfigFile;
import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Zephyrus - Spell.java<br>
 * Represents a spell
 * 
 * @author minnymin3
 * 
 */

public abstract class Spell {

	private final String defaultName;

	private String name;
	private String description;
	private int manaCost;
	private int xpReward;
	private AspectList recipe;
	private int requiredLevel;

	private SpellElement element;
	private SpellType type;

	public Spell(String name, String description, int manaCost, int xpReward, AspectList recipe, int requiredLevel,
			SpellElement element, SpellType type) {
		this.defaultName = name.toLowerCase();

		YmlConfigFile yml = Zephyrus.getSpellConfig();
		updateCompatibility(defaultName);
		yml.addDefaults(defaultName + ".Enabled", true);
		yml.addDefaults(defaultName + ".Name", name.toLowerCase());
		yml.addDefaults(defaultName + ".Description", description);
		yml.addDefaults(defaultName + ".ManaCost", manaCost);
		yml.addDefaults(defaultName + ".XpReward", xpReward);
		yml.addDefaults(defaultName + ".Recipe", toList(recipe));
		yml.addDefaults(defaultName + ".RequiredLevel", requiredLevel);
		yml.saveConfig();

		if (this instanceof ConfigurableSpell) {
			Iterator<Entry<String, Object>> iter = ((ConfigurableSpell) this).getDefaultConfiguration().entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, Object> value = iter.next();
				yml.addDefaults(defaultName + "." + value.getKey(), value.getValue());
			}
		}

		ConfigurationSection config = yml.getConfig().getConfigurationSection(defaultName);
		this.name = config.getString("Name");
		this.description = config.getString("Description");
		this.manaCost = config.getInt("ManaCost");
		this.xpReward = config.getInt("XpReward");
		this.recipe = fromList(config.getStringList("Recipe"));
		this.requiredLevel = config.getInt("RequiredLevel");

		this.element = element;
		this.type = type;
	}

	/**
	 * Gets the name of the spell configured by the user
	 * 
	 * @return The name of the spell
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the default name as originally defined in the constructor
	 * 
	 * @return The name of the spell
	 */
	public String getDefaultName() {
		return this.defaultName;
	}

	/**
	 * Gets the description of the spell configured by the user
	 * 
	 * @return The description of the spell
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the mana cost of the spell configured by the user
	 * 
	 * @return The mana cost of the spell
	 */
	public int getManaCost() {
		return this.manaCost;
	}

	/**
	 * Gets the xp reward of the spell configured by the user
	 * 
	 * @return The xp reward of the spell
	 */
	public int getXpReward() {
		return this.xpReward;
	}

	/**
	 * Gets the recipe of the spell configured by the user
	 * 
	 * @return The recipe of the spell
	 */
	public AspectList getRecipe() {
		return this.recipe;
	}

	/**
	 * Gets the required level of the spell configured by the user
	 * 
	 * @return The required level of the spell
	 */
	public int getRequiredLevel() {
		return this.requiredLevel;
	}

	/**
	 * Gets the element of the spell
	 * 
	 * @return The SpellElement of the spell
	 */
	public SpellElement getElement() {
		return this.element;
	}

	/**
	 * Gets the type of the spell
	 * 
	 * @return The SpellType of the spell
	 */
	public SpellType getType() {
		return this.type;
	}

	/**
	 * Called when Zephyrus is enabled
	 */
	public void onDisable() {
	}

	/**
	 * Called when Zephyrus is disabled
	 */
	public void onEnable() {
	}

	/**
	 * Called when the player casts the spell
	 * 
	 * @param user The user who cast the spell
	 * @param power The power this spell was cast with
	 * @param args The arguments this spell was cast with
	 * @return SUCCESS to drain mana and call post cast tasks, FAILURE to not
	 *         drain mana
	 */
	public abstract CastResult onCast(User user, int power, String[] args);

	private AspectList fromList(List<String> list) {
		List<Aspect> aspectType = new ArrayList<Aspect>();
		List<Integer> aspectValue = new ArrayList<Integer>();
		for (String s : list) {
			String[] split = s.split("-");
			try {
				Aspect aspect = Aspect.valueOf(split[0]);
				int value = Integer.parseInt(split[1]);
				aspectType.add(aspect);
				aspectValue.add(value);
			} catch (Exception ex) {
				// Catch any syntax errors caused by the user
			}
		}
		return AspectList.newList().setAspectLists(aspectType, aspectValue);
	}

	private List<String> toList(AspectList recipe) {
		Map<Aspect, Integer> aspects = recipe.getAspectMap();
		List<String> list = new ArrayList<String>();
		for (Aspect aspect : aspects.keySet()) {
			list.add(aspect + "-" + aspects.get(aspect));
		}
		return list;
	}

	private void updateCompatibility(String base) {
		FileConfiguration config = Zephyrus.getSpellConfig().getConfig();
		updateKey(config, base, "enabled", "Enabled");
		updateKey(config, base, "desc", "Description");
		updateKey(config, base, "displayname", "Name");
		updateKey(config, base, "level", "RequiredLevel");
		updateKey(config, base, "mana", "ManaCost");
		updateKey(config, base, "exp", "XpReward");
	}

	private void updateKey(FileConfiguration config, String base, String oldKey, String newKey) {
		if (config.contains(base + "." + oldKey)) {
			config.set(base + "." + newKey, config.get(base + "." + oldKey));
			config.set(base + "." + oldKey, null);
		}
	}

}
