package com.minnymin.zephyrus.core.spell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.item.SpellTome;
import com.minnymin.zephyrus.core.spell.attack.ArrowRainSpell;
import com.minnymin.zephyrus.core.spell.attack.ArrowSpell;
import com.minnymin.zephyrus.core.spell.attack.ArrowStormSpell;
import com.minnymin.zephyrus.core.spell.attack.BoltSpell;
import com.minnymin.zephyrus.core.spell.attack.ButcherSpell;
import com.minnymin.zephyrus.core.spell.attack.FireballSpell;
import com.minnymin.zephyrus.core.spell.attack.FirechargeSpell;
import com.minnymin.zephyrus.core.spell.attack.FlareSpell;
import com.minnymin.zephyrus.core.spell.attack.GrenadeSpell;
import com.minnymin.zephyrus.core.spell.attack.HomingArrowSpell;
import com.minnymin.zephyrus.core.spell.attack.LifeStealSpell;
import com.minnymin.zephyrus.core.spell.attack.PunchSpell;
import com.minnymin.zephyrus.core.spell.buff.ArmorSpell;
import com.minnymin.zephyrus.core.spell.buff.BuildSpell;
import com.minnymin.zephyrus.core.spell.buff.DispelSpell;
import com.minnymin.zephyrus.core.spell.buff.FeatherSpell;
import com.minnymin.zephyrus.core.spell.buff.FireShieldSpell;
import com.minnymin.zephyrus.core.spell.buff.FlameStepSpell;
import com.minnymin.zephyrus.core.spell.buff.FlySpell;
import com.minnymin.zephyrus.core.spell.buff.LightSpell;
import com.minnymin.zephyrus.core.spell.buff.ShieldSpell;
import com.minnymin.zephyrus.core.spell.buff.SpeedSpell;
import com.minnymin.zephyrus.core.spell.buff.ZephyrSpell;
import com.minnymin.zephyrus.core.spell.creation.ConjureSpell;
import com.minnymin.zephyrus.core.spell.creation.EnderchestSpell;
import com.minnymin.zephyrus.core.spell.creation.JailSpell;
import com.minnymin.zephyrus.core.spell.illusion.ConfuseSpell;
import com.minnymin.zephyrus.core.spell.illusion.DisorientSpell;
import com.minnymin.zephyrus.core.spell.mobility.BangSpell;
import com.minnymin.zephyrus.core.spell.mobility.BlinkSpell;
import com.minnymin.zephyrus.core.spell.mobility.HomeSpell;
import com.minnymin.zephyrus.core.spell.mobility.MassParalyzeSpell;
import com.minnymin.zephyrus.core.spell.mobility.ParalyzeSpell;
import com.minnymin.zephyrus.core.spell.mobility.PhaseSpell;
import com.minnymin.zephyrus.core.spell.mobility.WooshSpell;
import com.minnymin.zephyrus.core.spell.restoration.FeedSpell;
import com.minnymin.zephyrus.core.spell.restoration.FeederSpell;
import com.minnymin.zephyrus.core.spell.restoration.HealSpell;
import com.minnymin.zephyrus.core.spell.restoration.HealerSpell;
import com.minnymin.zephyrus.core.spell.restoration.RepairSpell;
import com.minnymin.zephyrus.core.spell.restoration.SatisfySpell;
import com.minnymin.zephyrus.core.spell.world.BrightSpell;
import com.minnymin.zephyrus.core.spell.world.ClockSpell;
import com.minnymin.zephyrus.core.spell.world.DetectSpell;
import com.minnymin.zephyrus.core.spell.world.DigSpell;
import com.minnymin.zephyrus.core.spell.world.DimSpell;
import com.minnymin.zephyrus.core.spell.world.ExplodeSpell;
import com.minnymin.zephyrus.core.spell.world.FireRingSpell;
import com.minnymin.zephyrus.core.spell.world.GrowSpell;
import com.minnymin.zephyrus.core.spell.world.ProspectSpell;
import com.minnymin.zephyrus.core.spell.world.SmiteSpell;
import com.minnymin.zephyrus.core.spell.world.StormSpell;
import com.minnymin.zephyrus.core.spell.world.SuperHeatSpell;
import com.minnymin.zephyrus.core.spell.world.TrackSpell;
import com.minnymin.zephyrus.spell.ConfigurableSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellManager;
import com.minnymin.zephyrus.spell.SpellRecipe;

/**
 * Zephyrus - SpellManager.java
 * 
 * @author minnymin3
 * 
 */

public class CoreSpellManager implements SpellManager {

	private List<Spell> spellList;
	private YmlConfigFile spellConfig;

	public CoreSpellManager() {
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
	public List<Spell> getSpells(List<ItemStack> recipe) {
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
		registerSpell(new ArrowSpell());
		registerSpell(new ArrowRainSpell());
		registerSpell(new ArrowStormSpell());
		registerSpell(new BoltSpell());
		registerSpell(new ButcherSpell());
		registerSpell(new FireballSpell());
		registerSpell(new FirechargeSpell());
		registerSpell(new FlareSpell());
		registerSpell(new GrenadeSpell());
		registerSpell(new HomingArrowSpell());
		registerSpell(new LifeStealSpell());
		registerSpell(new PunchSpell());

		// Buff
		registerSpell(new ArmorSpell());
		registerSpell(new BuildSpell());
		registerSpell(new DispelSpell());
		registerSpell(new FeatherSpell());
		registerSpell(new FireShieldSpell());
		registerSpell(new FlameStepSpell());
		registerSpell(new FlySpell());
		registerSpell(new LightSpell());
		registerSpell(new ShieldSpell());
		registerSpell(new SpeedSpell());
		registerSpell(new ZephyrSpell());

		// Creation
		registerSpell(new ConjureSpell());
		registerSpell(new EnderchestSpell());
		registerSpell(new JailSpell());

		// Illusion
		registerSpell(new ConfuseSpell());
		registerSpell(new DisorientSpell());

		// Mobility
		registerSpell(new BangSpell());
		registerSpell(new BlinkSpell());
		registerSpell(new HomeSpell());
		registerSpell(new MassParalyzeSpell());
		registerSpell(new ParalyzeSpell());
		registerSpell(new PhaseSpell());
		registerSpell(new WooshSpell());

		// Restoration
		registerSpell(new FeedSpell());
		registerSpell(new FeederSpell());
		registerSpell(new HealSpell());
		registerSpell(new HealerSpell());
		registerSpell(new RepairSpell());
		registerSpell(new SatisfySpell());
		
		// World
		registerSpell(new BrightSpell());
		registerSpell(new ClockSpell());
		registerSpell(new DetectSpell());
		registerSpell(new DigSpell());
		registerSpell(new DimSpell());
		registerSpell(new ExplodeSpell());
		registerSpell(new FireRingSpell());
		registerSpell(new GrowSpell());
		registerSpell(new ProspectSpell());
		registerSpell(new SmiteSpell());
		registerSpell(new StormSpell());
		registerSpell(new SuperHeatSpell());
		registerSpell(new TrackSpell());

		// TODO Re-implement:
		// Vanish, Vision, Zap

		// TODO Add: God spells (FireGod, IceGod, etc.), Freeze, Woosh (move
		// forwards fast), Magnet, Transplace, Shear, Chop, Flash, Telekenisis,
		// 'WorldEdit' (build)

		// TODO Spell level balancing (broader range of levels)
		
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
