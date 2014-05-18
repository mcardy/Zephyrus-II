package com.minnymin.zephyrus.core.spell.attack;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - Gernade.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.BLOCK, range = 200)
// Making sure player can target grenade destination
public class GrenadeSpell extends Spell {

	public GrenadeSpell() {
		super("grenade", "Shoot a small, explosive object", 80, 8, AspectList.newList(Aspect.DESTRUCTION,
				50, Aspect.ENERGY, 25), 4, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		player.launchProjectile(WitherSkull.class);
		player.getWorld().playSound(player.getLocation(), Sound.WITHER_SHOOT, 1.0F, 0F);
		return CastResult.SUCCESS;
	}

}
