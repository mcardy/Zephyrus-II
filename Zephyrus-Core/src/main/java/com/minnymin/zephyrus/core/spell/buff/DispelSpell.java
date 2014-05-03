package com.minnymin.zephyrus.core.spell.buff;


import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Dispel.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class DispelSpell extends Spell {

	public DispelSpell() {
		super("dispel", "Gets rid of those pesky potion effects", 50, 3, AspectList
				.newList(Aspect.MYSTICAL, 30, Aspect.FIRE, 15, Aspect.WATER, 15, Aspect.LIFE, 15), 1, SpellElement.ARCANE, SpellType.BUFF);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		for (PotionEffect pe : player.getActivePotionEffects()) {
			player.removePotionEffect(pe.getType());
		}
		return CastResult.SUCCESS;
	}

}
