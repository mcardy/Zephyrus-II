package com.minnymin.zephyrus.core.spell.attack;


import org.bukkit.metadata.FixedMetadataValue;

import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.ContinuousSpell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ArrowRain.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Prerequisite(requiredSpell = ArrowStormSpell.class)
public class ArrowRainSpell extends ContinuousSpell {

	public ArrowRainSpell() {
		super("arrowrain", "Continuously rains arrows down upon your enemy", 50, 20, AspectList
				.newList(Aspect.WIND, 100, Aspect.WEAPON, 100, Aspect.WOOD, 100), 5, SpellElement.AIR, SpellType.ATTACK, 2);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().launchProjectile(org.bukkit.entity.Arrow.class)
				.setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		return CastResult.SUCCESS;
	}

	@Override
	public CastResult onCastTick(User user, int power, String[] args) {
		user.getPlayer().launchProjectile(org.bukkit.entity.Arrow.class)
				.setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		return CastResult.SUCCESS;
	}

}
