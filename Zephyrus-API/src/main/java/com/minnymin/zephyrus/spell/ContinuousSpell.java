package com.minnymin.zephyrus.spell;

import com.minnymin.zephyrus.YmlConfigFile;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ContinuousSpell.java<br>
 * Represents a spell that is cast and then held until the player right clicks
 * 
 * @author minnymin3
 * 
 */

public abstract class ContinuousSpell extends Spell {

	private int manaCostTick;

	public ContinuousSpell(String name, String description, int manaCost, int xpReward, AspectList recipe,
			int requiredLevel, SpellElement element, SpellType type, int manaCostTick) {
		super(name, description, manaCost, xpReward, recipe, requiredLevel, element, type);
		YmlConfigFile yml = Zephyrus.getSpellConfig();
		yml.addDefaults(getDefaultName() + ".ManaCostPerTick", manaCostTick);
		this.manaCostTick = yml.getConfig().getConfigurationSection(getDefaultName()).getInt("ManaCostPerTick");
	}

	/**
	 * Gets the mana cost of this spell per 10th of a second while this spell is
	 * held
	 * 
	 * @return An int for the mana cost
	 */
	public int getManaCostPerTick() {
		return manaCostTick;
	}

	/**
	 * Called every 10th of a second to continuously cast the spell
	 * 
	 * @param user The user who is casting the spell
	 * @param power The power of the spell
	 * @param args The arguments originally used to cast the spell
	 * @return SUCCESS to drain mana and continue casting, FAILURE to stop
	 *         casting
	 */
	public abstract CastResult onCastTick(User user, int power, String[] args);

}
