package net.minezrc.zephyrus.core.spell;

import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - RegisteredSpell.java
 * 
 * @author minnymin3
 * 
 */

public class RegisteredSpell implements Spell {

	protected Spell spell;
	
	private String name = null;
	private String description = null;
	private int reqLevel = -1;
	private int manaCost = -1;
	private int xpReward = -1;
	private AspectList recipe = null;
	
	protected RegisteredSpell(Spell spell) {
		this.spell = spell;
	}

	@Override
	public String getName() {
		return this.name != null ? this.name : this.spell.getName();
	}
	
	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return this.description != null ? this.description : this.spell.getDescription();
	}
	
	protected void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getRequiredLevel() {
		return this.reqLevel != -1 ? this.reqLevel : this.spell.getRequiredLevel();
	}
	
	protected void setRequiredLevel(int reqLevel) {
		if (reqLevel > -1)
			this.reqLevel = reqLevel;
	}

	@Override
	public int getManaCost() {
		return this.manaCost != -1 ? this.manaCost : this.spell.getManaCost();
	}
	
	protected void setManaCost(int manaCost) {
		if (manaCost > -1)
			this.manaCost = manaCost;
	}

	@Override
	public int getXpReward() {
		return this.xpReward != -1 ? this.xpReward : this.spell.getXpReward();
	}

	protected void setXpReward(int xpReward) {
		if (xpReward > -1)
			this.xpReward = xpReward;
	}
	
	@Override
	public AspectList getRecipe() {
		return this.recipe != null ? this.recipe : this.spell.getRecipe();
	}
	
	protected void setRecipe(AspectList recipe) {
		this.recipe = recipe;
	}

	@Override
	public SpellElement getElement() {
		return this.spell.getElement();
	}

	@Override
	public SpellType getType() {
		return this.spell.getType();
	}

	@Override
	public void onEnable() {
		this.spell.onEnable();
	}

	@Override
	public void onDisable() {
		this.spell.onDisable();
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		return this.spell.onCast(user, power, args);
	}

	public Spell getOriginal() {
		return this.spell;
	}
	
	
}
