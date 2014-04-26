package net.minezrc.zephyrus.core.spell.attack;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Zephyrus - Arrow.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class ArrowSpell extends Spell implements Listener {

	public ArrowSpell() {
		super("arrow", "Shoots an arrow", 30, 5, AspectList
				.newList(Aspect.WIND, 15, Aspect.WEAPON, 15, Aspect.WOOD, 15), 1, SpellElement.AIR, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getPlayer().launchProjectile(org.bukkit.entity.Arrow.class)
				.setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
		return CastResult.SUCCESS;
	}

	@EventHandler
	public void onProjectilePickup(PlayerPickupItemEvent event) {
		if (event.getItem().hasMetadata("ignore_pickup")) {
			event.setCancelled(true);
		}
	}

}
