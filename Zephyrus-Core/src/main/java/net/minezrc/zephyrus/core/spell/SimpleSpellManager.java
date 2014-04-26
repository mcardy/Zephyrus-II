package net.minezrc.zephyrus.core.spell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.minezrc.zephyrus.YmlConfigFile;
import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.item.SpellTome;
import net.minezrc.zephyrus.core.spell.attack.Arrow;
import net.minezrc.zephyrus.core.spell.attack.ArrowRain;
import net.minezrc.zephyrus.core.spell.attack.ArrowStorm;
import net.minezrc.zephyrus.core.spell.attack.Bolt;
import net.minezrc.zephyrus.core.spell.attack.Butcher;
import net.minezrc.zephyrus.core.spell.attack.Fireball;
import net.minezrc.zephyrus.core.spell.attack.Firecharge;
import net.minezrc.zephyrus.core.spell.attack.Punch;
import net.minezrc.zephyrus.core.spell.buff.Armor;
import net.minezrc.zephyrus.core.spell.buff.Dispel;
import net.minezrc.zephyrus.core.spell.buff.Feather;
import net.minezrc.zephyrus.core.spell.buff.Shield;
import net.minezrc.zephyrus.core.spell.creation.Conjure;
import net.minezrc.zephyrus.core.spell.creation.Enderchest;
import net.minezrc.zephyrus.core.spell.illusion.Confuse;
import net.minezrc.zephyrus.core.spell.illusion.Disorient;
import net.minezrc.zephyrus.core.spell.mobility.Bang;
import net.minezrc.zephyrus.core.spell.mobility.Blink;
import net.minezrc.zephyrus.core.spell.mobility.Woosh;
import net.minezrc.zephyrus.core.spell.restoration.Feed;
import net.minezrc.zephyrus.core.spell.restoration.Feeder;
import net.minezrc.zephyrus.core.spell.restoration.Heal;
import net.minezrc.zephyrus.core.spell.restoration.Healer;
import net.minezrc.zephyrus.core.spell.world.Bright;
import net.minezrc.zephyrus.core.spell.world.Clock;
import net.minezrc.zephyrus.core.spell.world.Detect;
import net.minezrc.zephyrus.core.spell.world.Dig;
import net.minezrc.zephyrus.core.spell.world.Dim;
import net.minezrc.zephyrus.core.spell.world.Explode;
import net.minezrc.zephyrus.core.spell.world.Smite;
import net.minezrc.zephyrus.core.spell.world.Track;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellManager;
import net.minezrc.zephyrus.spell.SpellRecipe;

import org.bukkit.Bukkit;
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
	public YmlConfigFile getConfig() {
		return spellConfig;
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
	public List<Spell> getSpells(Set<ItemStack> recipe) {
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
			if (spell.getClass().isAssignableFrom(spellClass)) {
				return spell;
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
		if (spellConfig.getConfig().getBoolean(spell.getDefaultName() + ".Enabled")) {
			this.spellList.add(spell);
			if (spell instanceof ConfigurableSpell) {
				((ConfigurableSpell) spell).loadConfiguration(spellConfig.getConfig().getConfigurationSection(
						spell.getDefaultName()));
			}
			if (spell instanceof Listener) {
				Bukkit.getPluginManager().registerEvents((Listener) spell, Zephyrus.getPlugin());
			}
		}
	}

	@Override
	public void load() {
		Bukkit.getPluginManager().registerEvents(new SpellTome(), Zephyrus.getPlugin());
		spellConfig.saveDefaultConfig();

		// Attack
		registerSpell(new Arrow());
		registerSpell(new ArrowRain());
		registerSpell(new ArrowStorm());
		registerSpell(new Bolt());
		registerSpell(new Butcher());
		registerSpell(new Fireball());
		registerSpell(new Firecharge());
		registerSpell(new Punch());

		// Buff
		registerSpell(new Armor());
		registerSpell(new Dispel());
		registerSpell(new Feather());
		registerSpell(new Shield());

		// Creation
		registerSpell(new Conjure());
		registerSpell(new Enderchest());

		// Illusion
		registerSpell(new Confuse());
		registerSpell(new Disorient());

		// Mobility
		registerSpell(new Bang());
		registerSpell(new Blink());
		registerSpell(new Woosh());

		// Restoration
		registerSpell(new Feed());
		registerSpell(new Feeder());
		registerSpell(new Heal());
		registerSpell(new Healer());

		// World
		registerSpell(new Bright());
		registerSpell(new Clock());
		registerSpell(new Detect());
		registerSpell(new Dig());
		registerSpell(new Dim());
		registerSpell(new Explode());
		registerSpell(new Smite());
		registerSpell(new Track());

		// TODO Re-implement:
		// FireRing, FireShield, FlameStep, Flare, Fly,
		// Frenzy, Gernade, Grow, Heal, Home, Jail, LifeSteal, MageLight,
		// MassParalyze, Paralyze, Phase, Prospect, Repair, Satisfy,
		// Shield, Smite, Storm, Summon, SuperHeat, SuperSpeed, Vanish,
		// VIsion, Zap, Zephyr

		// TODO Add: God spells (FireGod, IceGod, etc.), Freeze, Woosh (move
		// forwards fast), Magnet, Transplace, Shear, Chop, Flash, Telekenisis,
		// 'WorldEdit' (build)

		// TODO Spell recipe balancing once more spells are implemented

		for (Spell spell : spellList) {
			spell.onEnable();
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
