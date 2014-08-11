package com.minnymin.zephyrus.core.user;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.core.config.ConfigOptions;
import com.minnymin.zephyrus.core.util.BlockUtils;
import com.minnymin.zephyrus.core.util.Language;
import com.minnymin.zephyrus.core.util.map.MultiMap;
import com.minnymin.zephyrus.event.UserLearnSpellEvent;
import com.minnymin.zephyrus.event.UserPostCastEvent;
import com.minnymin.zephyrus.event.UserPreCastEvent;
import com.minnymin.zephyrus.event.UserTargetBlockEvent;
import com.minnymin.zephyrus.event.UserTargetEntityEvent;
import com.minnymin.zephyrus.event.UserTargetPlayerEvent;
import com.minnymin.zephyrus.spell.ContinuousSpell;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.state.State;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Target;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;

/**
 * Zephyrus - OnlineUser.java
 * 
 * @author minnymin3
 * 
 */

public class OnlineUser implements User {

	private Player player;
	private String playerName;

	private Map<State, Integer> states;
	private List<String> learned;

	private float mana;
	private boolean display;

	private int extraMana;
	private int level;
	private int progress;
	private int tick;

	private MultiMap<Object, Integer, Target<?>> targetMap;

	private Map<String, Integer> delayMap;

	private ContinuousSpell continuousSpell;
	private int continuousPower;
	private String[] continuousArgs;

	private Map<String, Object> dataMap;

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
		while (this.progress >= (level ^ 2 * 10 + 100)) {
			this.progress -= (level ^ 2 * 10 + 100);
			level++;
			Language.sendMessage("game.levelup", player, "[LEVEL]", getLevel() + "");
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2, 1);
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2, 8);
			player.playSound(player.getLocation(), Sound.ORB_PICKUP, 2, -1);
		}
		return this.progress;
	}

	@Override
	public boolean addSpell(Spell spell) {
		UserLearnSpellEvent event = new UserLearnSpellEvent(player, spell);
		Bukkit.getPluginManager().callEvent(event);
		if (!event.isCancelled()) {
			learned.add(spell.getName());
			return true;
		} else {
			return false;
		}
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
	public void castSpell(Spell spell, int power, String[] args) {
		if (isCastingSpell()) {
			return;
		}
		if (Zephyrus.getHookManager().canCast(player, spell)) {
			if (spell == null || !isSpellLearned(spell)) {
				Language.sendError("command.cast.learn", getPlayer(), "[SPELL]", args[0]);
				return;
			}
			if (getMana() < spell.getManaCost()) {
				Language.sendError("command.cast.mana", getPlayer(), "[SPELL]", spell.getName(), "[MANA]",
						ChatColor.RED + "" + getMana() + ChatColor.GRAY + "/" + ChatColor.GREEN + spell.getManaCost());
				return;
			}
			if (spell.getClass().isAnnotationPresent(Targeted.class)) {
				Targeted targeted = spell.getClass().getAnnotation(Targeted.class);
				setTarget(spell, targeted.type(), targeted.range(), targeted.friendly());
				if ((targeted.type() == TargetType.PLAYER || targeted.type() == TargetType.ENTITY)
						&& (getTarget(spell).getTarget() == null || ((LivingEntity) getTarget(spell).getTarget())
								.isDead())) {
					Language.sendError("spell.notarget", getPlayer());
					return;
				}
			}
			String[] modArgs = null;
			if (args != null) {
				modArgs = new String[args.length - 1];
				for (int i = 1; i < args.length; i++) {
					modArgs[i - 1] = args[i];
				}
			}
			UserPreCastEvent preCast = new UserPreCastEvent(this, spell, power, modArgs);
			Bukkit.getPluginManager().callEvent(preCast);
			if (!preCast.isCancelled()) {
				CastResult result = spell.onCast(this, power, modArgs);
				if (result == CastResult.SUCCESS) {
					if (spell instanceof ContinuousSpell) {
						continuousSpell = (ContinuousSpell) spell;
						continuousPower = power;
						continuousArgs = modArgs;
					}
					drainMana(spell.getManaCost());
					addLevelProgress(spell.getXpReward());
					Bukkit.getPluginManager().callEvent(new UserPostCastEvent(this, spell, power, modArgs));
				}
			}
		}
	}

	@Override
	public float drainMana(float mana) {
		this.mana -= mana;
		if (getManaDisplay()) {
			Zephyrus.getUserManager()
					.getBarDisplay()
					.setBar(getPlayer(),
							ChatColor.DARK_AQUA + "---{" + ChatColor.BOLD + ChatColor.AQUA + getMana() + "/"
									+ getMaxMana() + ChatColor.RESET + ChatColor.DARK_AQUA + "}---",
							(int) (((float) getMana() / (float) getMaxMana()) * 200));
		}
		return this.mana;
	}

	@Override
	public Collection<State> getActiveStates() {
		return states.keySet();
	}

	@Override
	public String getData(String key) {
		Object value = dataMap.get(key);
		if (value != null && value instanceof String) {
			return (String) value;
		}
		return null;
	}

	@Override
	public int getDelay(String key) {
		if (delayMap.containsKey(key)) {
			return delayMap.get(key);
		}
		return 0;
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
	public int getStateTime(State state) {
		return states.get(state);
	}

	@Override
	public Target<?> getTarget(Object key) {
		if (targetMap.containsKey(key)) {
			return targetMap.getSecondValue(key);
		}
		return null;
	}

	@Override
	public boolean isCastingSpell() {
		return continuousSpell != null;
	}

	@Override
	public boolean isStateApplied(State state) {
		return this.states.containsKey(state);
	}

	@Override
	public boolean isSpellLearned(Spell spell) {
		if (getPlayer().hasPermission("zephyrus.spell.cast." + spell.getDefaultName().toLowerCase())) {
			return true;
		}
		return getLearnedSpells().contains(spell.getName());
	}

	protected synchronized void load() {
		for (State state : Zephyrus.getStateManager().getStates()) {
			state.onStartup(this);
		}
		YmlConfigFile configFile = new YmlConfigFile(playerName + ".yml", new File(
				Zephyrus.getPlugin().getDataFolder(), "Players"));
		FileConfiguration config = configFile.getConfig();
		if (!config.contains("learned") || !config.contains("mana") || !config.contains("level")
				|| !config.contains("progress") || !config.contains("display") || config.contains("extra-mana")) {
			configFile.addDefaults("learned", new ArrayList<String>());
			configFile.addDefaults("mana", 100);
			configFile.addDefaults("extra-mana", 0);
			configFile.addDefaults("level", 1);
			configFile.addDefaults("progress", 0);
			configFile.addDefaults("display", true);
		}
		this.learned = config.getStringList("learned");
		this.mana = config.getInt("mana");
		this.extraMana = config.getInt("extra-mana");
		this.level = config.getInt("level");
		this.progress = config.getInt("progress");
		this.display = config.getBoolean("display");
		this.states = new HashMap<State, Integer>();
		if (getManaDisplay()) {
			Zephyrus.getUserManager()
					.getBarDisplay()
					.setBar(getPlayer(),
							ChatColor.DARK_AQUA + "---{" + ChatColor.BOLD + ChatColor.AQUA + getMana() + "/"
									+ getMaxMana() + ChatColor.RESET + ChatColor.DARK_AQUA + "}---",
							(int) (((float) getMana() / (float) getMaxMana()) * 200));
		}
		this.targetMap = new MultiMap<Object, Integer, Target<?>>();
		this.delayMap = new HashMap<String, Integer>();
		if (config.contains("data")) {
			this.dataMap = config.getConfigurationSection("data").getValues(true);
		} else {
			this.dataMap = new HashMap<String, Object>();
		}
	}

	protected synchronized void save() {
		YmlConfigFile config = new YmlConfigFile(playerName + ".yml", new File(Zephyrus.getPlugin().getDataFolder(),
				"Players"));
		config.getConfig().set("learned", this.learned);
		config.getConfig().set("mana", this.mana);
		config.getConfig().set("extra-mana", this.extraMana);
		config.getConfig().set("level", this.level);
		config.getConfig().set("progress", this.progress);
		config.getConfig().set("display", this.display);
		for (Entry<String, Object> entry : this.dataMap.entrySet()) {
			config.getConfig().set("data." + entry.getKey(), entry.getValue());
		}
		config.saveConfig();
	}

	@Override
	public void setData(String key, String value) {
		this.dataMap.put(key, value);
	}

	@Override
	public void setDelay(String key, int time) {
		this.delayMap.put(key, time);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void setTarget(Object key, TargetType type, int range, boolean friendly) {
		if (type == TargetType.BLOCK) {
			Block target = player.getTargetBlock(BlockUtils.getTransparent(), range);
			if (Zephyrus.getHookManager().canBuild(player, target)) {
				UserTargetBlockEvent event = new UserTargetBlockEvent(this, target);
				Bukkit.getPluginManager().callEvent(event);
				if (!event.isCancelled()) {
					targetMap.put(key, 0, new Target<Block>(target, type));
				}
			}
		} else if (type == TargetType.ENTITY) {
			LivingEntity target = getTargetEntity(range);
			if (!targetMap.containsKey(key) || targetMap.getSecondValue(key).getTarget() == null || target != null) {
				if (Zephyrus.getHookManager().canTarget(player, target, friendly)) {
					UserTargetEntityEvent event = new UserTargetEntityEvent(this, target, friendly);
					Bukkit.getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						targetMap.put(key, 0, new Target<LivingEntity>(target, type));
					}
				}
			}
		} else if (type == TargetType.PLAYER) {
			LivingEntity target = getTargetEntity(range);
			if (!targetMap.containsKey(key) || targetMap.getSecondValue(key).getTarget() == null
					|| (target != null && target instanceof Player)) {
				if (Zephyrus.getHookManager().canTarget(player, target, friendly)) {
					UserTargetPlayerEvent event = new UserTargetPlayerEvent(this, (Player) target, friendly);
					Bukkit.getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						targetMap.put(key, 0, new Target<Player>((Player) target, type));
					}
				}
			}
		}
	}

	@Override
	public void stopCasting() {
		continuousSpell = null;
		continuousArgs = null;
		continuousPower = 0;
	}

	private LivingEntity getTargetEntity(int range) {
		BlockIterator iterator = new BlockIterator(player, range);
		while (iterator.hasNext()) {
			Block block = iterator.next();
			for (Entity entity : player.getNearbyEntities(range, range, range)) {
				if (entity instanceof LivingEntity) {
					int accuracy = 2;
					for (int offX = -accuracy; offX < accuracy; offX++) {
						for (int offY = -accuracy; offY < accuracy; offY++) {
							for (int offZ = -accuracy; offZ < accuracy; offZ++) {
								if (entity.getLocation().getBlock().getRelative(offX, offY, offZ).equals(block)) {
									return (LivingEntity) entity;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	// This method is triggered every 2 ticks
	// Method is called every 10th of a second
	protected void tick() {
		if (Bukkit.getPlayer(playerName) == null) {
			save();
			((CoreUserManager) Zephyrus.getUserManager()).removeUser(playerName);
			return;
		}
		for (State state : getActiveStates()) {
			if (state.getTickTime() > 0 && tick % state.getTickTime() == 0) {
				state.onTick(this);
			}
		}
		if (isCastingSpell()) {
			if (getMana() < continuousSpell.getManaCostPerTick()) {
				Language.sendError("command.cast.continuous.mana", getPlayer(), "[SPELL]", continuousSpell.getName(),
						"[MANA]", ChatColor.RED + "" + getMana() + ChatColor.GRAY + "/" + ChatColor.GREEN
								+ continuousSpell.getManaCost());
				stopCasting();
				return;
			}
			CastResult result = continuousSpell.onCastTick(this, continuousPower, continuousArgs);
			if (result == CastResult.SUCCESS) {
				drainMana(continuousSpell.getManaCostPerTick());
			} else {
				stopCasting();
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
			for (Object key : targetMap.keySet()) {
				int time = targetMap.getFirstValue(key) + 1;
				if (time == 10) {
					targetMap.remove(key);
				} else {
					targetMap.put(key, time, targetMap.getSecondValue(key));
				}

			}
			for (String key : delayMap.keySet()) {
				int time = delayMap.get(key) - 1;
				if (time == 0) {
					delayMap.remove(key);
				} else {
					delayMap.put(key, time);
				}
			}
		}
		tick++;
	}

}