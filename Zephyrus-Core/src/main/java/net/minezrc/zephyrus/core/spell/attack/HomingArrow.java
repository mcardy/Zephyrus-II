package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.projectile.HomingEntityProjectile;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.spell.annotation.Prerequisite;
import net.minezrc.zephyrus.user.Targeted;
import net.minezrc.zephyrus.user.User;

import org.bukkit.metadata.FixedMetadataValue;

/**
 * Zephyrus - HomingArrow.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY, range = 40)
@Prerequisite(requiredSpell = Arrow.class)
public class HomingArrow extends Spell {

	public HomingArrow() {
		super("homingarrow", "Shoots an arrow to track your target", 25, 2, AspectList.newList(Aspect.WEAPON, 30,
				Aspect.MOVEMENT, 15, Aspect.WOOD, 15, Aspect.WIND, 15), 1, SpellElement.AIR, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		HomingEntityProjectile projectile = new HomingEntityProjectile(user.getPlayer().launchProjectile(
				org.bukkit.entity.Arrow.class), user.getTarget(getDefaultName()).getEntity());
		projectile.getEntity().setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		projectile.launchProjectile(user.getPlayer());
		return CastResult.SUCCESS;
	}

}
