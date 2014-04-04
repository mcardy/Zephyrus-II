package net.minezrc.zephyrus.core.spell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minezrc.zephyrus.YmlConfigFile;
import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.item.SpellTome;
import net.minezrc.zephyrus.core.spell.attack.Arrow;
import net.minezrc.zephyrus.core.spell.attack.ArrowStorm;
import net.minezrc.zephyrus.core.spell.buff.Armor;
import net.minezrc.zephyrus.core.spell.mobility.Bang;
import net.minezrc.zephyrus.core.spell.mobility.Blink;
import net.minezrc.zephyrus.core.spell.restoration.Feed;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellManager;
import net.minezrc.zephyrus.spell.SpellRecipe;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - SpellManager.java
 * 
 * @author minnymin3
 * 
 */

public class SimpleSpellManager implements SpellManager {

	private List<Spell> spellList;
	private YmlConfigFile spellConfig;

	public SimpleSpellManager() {
		spellList = new ArrayList<Spell>();
		spellConfig = new YmlConfigFile("spells.yml");
	}

	@Override
	public Spell getSpell(String name) {
		Iterator<Spell> spells = spellList.iterator();
		while (spells.hasNext()) {
			Spell spell = spells.next();
			if (spell.getName().equalsIgnoreCase(name))
				return spell;
		}
		return null;
	}

	@Override
	public List<Spell> getSpell(Set<ItemStack> recipe) {
		List<Spell> spellSet = new ArrayList<Spell>();
		Iterator<Spell> spells = spellList.iterator();
		while (spells.hasNext()) {
			Spell spell = spells.next();
			if (new SpellRecipe(spell.getRecipe()).isSatisfied(recipe))
				spellSet.add(spell);
		}
		return spellSet;
	}

	@Override
	public Spell getSpell(Class<? extends Spell> spellClass) {
		Iterator<Spell> spells = spellList.iterator();
		while (spells.hasNext()) {
			Spell spell = spells.next();
			if (spell instanceof RegisteredSpell) {
				if (((RegisteredSpell) spell).spell.getClass().isAssignableFrom(spellClass)) {
					return spell;
				}
			} else {
				if (spell.getClass().isAssignableFrom(spellClass)) {
					return spell;
				}
			}
		}
		return null;
	}

	@Override
	public Set<String> getSpellNameSet() {
		Set<String> spells = new HashSet<String>();
		for (Spell spell : this.spellList) {
			spells.add(spell.getName());
		}
		return spells;
	}

	@Override
	public Set<Spell> getSpellSet() {
		Set<Spell> spells = new LinkedHashSet<Spell>();
		for (Spell spell : this.spellList) {
			spells.add(spell);
		}
		return spells;
	}

	@Override
	public ItemStack getSpellTome(Spell spell) {
		return SpellTome.createSpellTome(spell);
	}

	@Override
	public void registerSpell(Spell spell) {
		RegisteredSpell regSpell = new RegisteredSpell(spell);
		String name = spell.getName();
		updateCompatibility(name);
		spellConfig.addDefaults(name + ".Enabled", true);
		spellConfig.addDefaults(name + ".Name", spell.getName().toLowerCase());
		spellConfig.addDefaults(name + ".Description", spell.getDescription());
		spellConfig.addDefaults(name + ".RequiredLevel", spell.getRequiredLevel());
		spellConfig.addDefaults(name + ".ManaCost", spell.getManaCost());
		spellConfig.addDefaults(name + ".XpReward", spell.getXpReward());
		spellConfig.addDefaults(name + ".Recipe", toList(spell.getRecipe()));
		if (spell instanceof ConfigurableSpell) {
			Iterator<Entry<String, Object>> iter = ((ConfigurableSpell) spell).getDefaultConfiguration().entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, Object> value = iter.next();
				spellConfig.addDefaults(name + "." + value.getKey(), value.getValue());
			}
		}
		if (spellConfig.getConfig().getBoolean(name + ".Enabled")) {
			FileConfiguration config = spellConfig.getConfig();
			regSpell.setDescription(config.getString(name + ".Description"));
			regSpell.setManaCost(config.getInt(name + ".ManaCost"));
			regSpell.setName(config.getString(name + ".Name").toLowerCase());
			regSpell.setRequiredLevel(config.getInt(name + ".RequiredLevel"));
			regSpell.setXpReward(config.getInt(name + ".XpReward"));
			regSpell.setRecipe(fromList(config.getStringList(name + ".Recipe")));
			if (regSpell.spell instanceof ConfigurableSpell) {
				((ConfigurableSpell) regSpell.spell).loadConfiguration(spellConfig.getConfig()
						.getConfigurationSection(name));
			}
			this.spellList.add(regSpell);
			if (spell instanceof Listener) {
				Bukkit.getPluginManager().registerEvents((Listener) spell, Zephyrus.getPlugin());
			}
		}
	}

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
		FileConfiguration config = spellConfig.getConfig();
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

	

	@Override
	public void load() {
		Bukkit.getPluginManager().registerEvents(new SpellTome(), Zephyrus.getPlugin());
		spellConfig.saveDefaultConfig();
		// Attack
		registerSpell(new Arrow());
		registerSpell(new ArrowStorm());
		// Buff
		registerSpell(new Armor());
		// Creation
		// Illusion
		// Mobility
		registerSpell(new Bang());
		registerSpell(new Blink());
		// Restoration
		registerSpell(new Feed());
		// World
		for (Spell spell : spellList) {
			spell.onDisable();
		}
		spellConfig.saveConfig();
	}

	@Override
	public void unload() {
		for (Spell spell : spellList) {
			spell.onDisable();
		}
	}

}
