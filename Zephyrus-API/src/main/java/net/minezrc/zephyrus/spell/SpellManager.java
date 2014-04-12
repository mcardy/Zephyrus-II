package net.minezrc.zephyrus.spell;

import java.util.List;
import java.util.Set;

import net.minezrc.zephyrus.Manager;
import net.minezrc.zephyrus.YmlConfigFile;

import org.bukkit.inventory.ItemStack;

/**
 * Zephyrus - SpellManager.java
 * 
 * @author minnymin3
 * 
 */

public interface SpellManager extends Manager {

	public YmlConfigFile getConfig();
	public Spell getSpell(Class<? extends Spell> spellClass);
	public List<Spell> getSpell(Set<ItemStack> recipe);
	public Spell getSpell(String name);
	public Set<String> getSpellNameSet();
	public Set<Spell> getSpellSet();
	public ItemStack getSpellTome(Spell spell);
	public void registerSpell(Spell spell);
	
}
