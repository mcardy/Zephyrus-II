package net.minezrc.zephyrus.core.spell.buff;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * Zephyrus - Dispel.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class Dispel extends Spell {

	public Dispel() {
		super("dispel", "Gets rid of those pesky potion effects", 50, 3, AspectList.newList()
				.setAspectTypes(Aspect.MAGIC, Aspect.WATER, Aspect.FIRE).setAspectValues(16, 16, 16), 1,
				SpellElement.ARCANE, SpellType.BUFF);
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
