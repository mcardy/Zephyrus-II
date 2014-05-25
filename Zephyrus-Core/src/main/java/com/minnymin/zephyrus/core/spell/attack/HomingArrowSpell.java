package com.minnymin.zephyrus.core.spell.attack;


import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.projectile.HomingEntityProjectile;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Targeted;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;

/**
 * Zephyrus - HomingArrow.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY, range = 40)
@Prerequisite(requiredSpell = ArrowSpell.class)
public class HomingArrowSpell extends Spell {

	public HomingArrowSpell() {
		super("homingarrow", "Shoots an arrow to track your target", 25, 2, AspectList.newList(Aspect.WEAPON, 40,
				Aspect.MOVEMENT, 20, Aspect.WOOD, 20, Aspect.WIND, 20), 2, SpellElement.AIR, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		HomingEntityProjectile projectile = new HomingEntityProjectile(user.getPlayer().launchProjectile(
				org.bukkit.entity.Arrow.class), (LivingEntity) user.getTarget(this).getTarget());
		projectile.getEntity().setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		projectile.launchProjectile(user.getPlayer());
		return CastResult.SUCCESS;
	}

}
