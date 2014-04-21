package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.ContinuousSpell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.metadata.FixedMetadataValue;

/**
 * Zephyrus - ArrowRain.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class ArrowRain extends ContinuousSpell {

	public ArrowRain() {
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
