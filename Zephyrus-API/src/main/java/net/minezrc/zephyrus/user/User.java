package net.minezrc.zephyrus.user;

import java.util.Collection;
import java.util.List;

import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.state.State;

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
	public Target getTarget(Spell spell);
	public boolean isCastingSpell();
	public boolean isSpellLearned(Spell spell);
	public boolean isStateApplied(State state);
	public void setTarget(Spell spell, TargetType type, boolean friendly);
	public void stopCasting();
	
}
