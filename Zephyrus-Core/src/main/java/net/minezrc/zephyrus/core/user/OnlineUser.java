package net.minezrc.zephyrus.core.user;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minezrc.zephyrus.YmlConfigFile;
import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.core.config.ConfigOptions;
import net.minezrc.zephyrus.core.state.StateList;
import net.minezrc.zephyrus.core.util.Language;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.state.State;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Zephyrus - OnlineUser.java
 * 
 * @author minnymin3
 * 
 */

public class OnlineUser implements User {

	private Player player;
	private String playerName;
	private List<String> learned;
	private float mana;
	private int extraMana;
	private int level;
	private int progress;
	private boolean display;
	private Map<State, Integer> states;
	private int tick;

	protected OnlineUser(Player player) {
		this.player = player;
		this.playerName = player.getName();
		load();
	}

	@Override
	public void addExtraMana(int mana) {
		this.extraMana += mana;
	}

	@Override
	public int addLevelProgress(int progress) {
		this.progress += progress;
		while (this.progress >= (level * level * level + 100)) {
			this.progress -= (level * level * level + 100);
			level++;
			Language.sendMessage("game.levelup", ChatColor.AQUA + "You leveled up to level [LEVEL]", player, "[LEVEL]", getLevel()
					+ "");
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2, 1);
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2, 8);
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2, -1);
		}
		return this.progress;
	}

	@Override
	public void addSpell(Spell spell) {
		learned.add(spell.getName());
	}

	@Override
	public void addState(State state, int time) {
		if (this.states.containsKey(state)) {
			this.states.put(state, this.states.get(state) + time);
		} else {
			state.onApplied(this);
			this.states.put(state, time);
		}
	}

	@Override
	public float drainMana(float mana) {
		this.mana -= mana;
		if (getManaDisplay()) {
			Zephyrus.getUserManager().getBarDisplay().setBar(getPlayer(), ChatColor.DARK_AQUA + "---{" + ChatColor.BOLD
					+ ChatColor.AQUA + getMana() + "/" + getMaxMana() + ChatColor.RESET
					+ ChatColor.DARK_AQUA + "}---", (int) (((float) getMana() / (float) getMaxMana()) * 200));
		}
		return this.mana;
	}

	@Override
	public List<String> getLearnedSpells() {
		return learned;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getLevelProgress() {
		return progress;
	}

	@Override
	public int getMana() {
		return (int) mana;
	}

	@Override
	public boolean getManaDisplay() {
		return display;
	}

	@Override
	public int getMaxMana() {
		return level * 100 + extraMana;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Collection<State> getStates() {
		return states.keySet();
	}

	@Override
	public boolean isStateApplied(State state) {
		return this.states.containsKey(state);
	}

	@Override
	public boolean isSpellLearned(Spell spell) {
		return getLearnedSpells().contains(spell.getName());
	}

	protected synchronized void load() {
		for (State state : StateList.STATES) {
			state.onStartup(this);
		}
		YmlConfigFile configFile = new YmlConfigFile(playerName + ".yml", new File(
				Zephyrus.getPlugin().getDataFolder(), "Players"));
		FileConfiguration config = configFile.getConfig();
		if (!config.contains("learned") || !config.contains("mana") || !config.contains("level")
				|| !config.contains("progress") || !config.contains("display")) {
			configFile.addDefaults("learned", new ArrayList<String>());
			configFile.addDefaults("mana", 100);
			configFile.addDefaults("level", 1);
			configFile.addDefaults("progress", 0);
			configFile.addDefaults("display", true);
		}
		this.learned = config.getStringList("learned");
		this.mana = config.getInt("mana");
		this.level = config.getInt("level");
		this.progress = config.getInt("progress");
		this.display = config.getBoolean("display");
		this.states = new HashMap<State, Integer>();
		if (getManaDisplay()) {
			Zephyrus.getUserManager().getBarDisplay().setBar(getPlayer(), ChatColor.DARK_AQUA + "---{" + ChatColor.BOLD
					+ ChatColor.AQUA + getMana() + "/" + getMaxMana() + ChatColor.RESET
					+ ChatColor.DARK_AQUA + "}---", (int) (((float) getMana() / (float) getMaxMana()) * 200));
		}
	}

	protected synchronized void save() {
		YmlConfigFile config = new YmlConfigFile(playerName + ".yml", new File(Zephyrus.getPlugin().getDataFolder(),
				"Players"));
		config.getConfig().set("learned", this.learned);
		config.getConfig().set("mana", this.mana);
		config.getConfig().set("level", this.level);
		config.getConfig().set("progress", this.progress);
		config.getConfig().set("display", this.display);
		config.saveConfig();
	}

	@Override
	public void setManaDisplay(boolean b) {
		this.display = b;
	}

	// This method is triggered every 2 ticks
	// Tick time meaures 10ths of a second
	protected void tick() {
		if (Bukkit.getPlayer(playerName) == null) {
			save();
			((SimpleUserManager)Zephyrus.getUserManager()).removeUser(playerName);
			return;
		}
		for (State state : getStates()) {
			if (state.getTickTime() > 0 && tick % state.getTickTime() == 0) {
				state.onTick(this);
			}
		}
		if (tick >= 10) {
			tick = 0;
			float amount = 1F / (float) ConfigOptions.MANA_REGEN;
			if (getMana() + amount < getMaxMana()) {
				drainMana(-amount);
			} else if (getMana() != getMaxMana()) { 
				drainMana(getMana() - getMaxMana());
			}
			for (Entry<State, Integer> entry : this.states.entrySet()) {
				int time = entry.getValue() - 1;
				State state = entry.getKey();
				if (time == 5) {
					state.onWarning(this);
				}
				if (time == 0) {
					this.states.remove(state);
					state.onRemoved(this);
				} else {
					this.states.put(state, time);
				}
			}
		}
		tick++;
	}

}