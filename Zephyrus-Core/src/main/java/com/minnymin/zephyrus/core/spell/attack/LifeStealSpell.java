package com.minnymin.zephyrus.core.spell.attack;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;

/**
 * Zephyrus - LifeStealSpell.java
 * 
 * @author minnymin3
 * 
 */

@Targeted(type = TargetType.ENTITY, friendly = false)
public class LifeStealSpell extends Spell {

	public LifeStealSpell() {
		super("lifesteal", "Suck the life force out of an enemy", 25, 2, AspectList.newList(Aspect.BEAST, 50,
				Aspect.LIFE, 25, Aspect.FLESH, 25), 3, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		player.setHealth(player.getHealth() + 2 <= player.getMaxHealth() ? player.getHealth() + 2 : player.getMaxHealth());
		LivingEntity entity = (LivingEntity) user.getTarget(this).getTarget();
		entity.damage(2);
		return CastResult.SUCCESS;
	}

}
