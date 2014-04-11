package net.minezrc.zephyrus.user;

import java.util.Collection;
import java.util.List;

import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.state.State;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Zephyrus - User.java
 * 
 * @author minnymin3
 * 
 */

public interface User {

	public void addExtraMana(int mana);
	public int addLevelProgress(int progress);
	public void addSpell(Spell spell);
	public void addState(State state, int time);
	public void castSpell(Spell spell, int power, String[] args);
	public float drainMana(float mana);
	public List<String> getLearnedSpells();
	public int getLevel();
	public int getLevelProgress();
	public int getMana();
	public boolean getManaDisplay();
	public int getMaxMana();
	public Player getPlayer();
	public Collection<State> getStates();
	public LivingEntity getTarget(Spell spell);
	public boolean isSpellLearned(Spell spell);
	public boolean isStateApplied(State state);
	public void setManaDisplay(boolean b);
	public void setTarget(LivingEntity entity, String spell, boolean friendly);
	
}
