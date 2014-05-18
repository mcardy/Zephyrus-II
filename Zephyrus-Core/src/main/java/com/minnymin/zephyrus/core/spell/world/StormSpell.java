package com.minnymin.zephyrus.core.spell.world;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - StormSpell.java
 * 
 * @author minnymin3
 * 
 */

public class StormSpell extends Spell {

	public StormSpell() {
		super("storm", "Collect the power of the skies to create a storm", 300, 30, AspectList.newList(Aspect.WIND,
				150, Aspect.WATER, 75, Aspect.ENERGY, 75), 9, SpellElement.AIR, SpellType.WORLD);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		player.getWorld().setStorm(true);
		player.getWorld().setThundering(true);
		for (int i = 0; i < 5; i++) {
			Location loc = player.getLocation();
			loc.setX(loc.getX() + new Random().nextInt(20));
			loc.setX(loc.getX() - new Random().nextInt(20));
			loc.setY(loc.getY() + new Random().nextInt(20));
			loc.setY(loc.getY() - new Random().nextInt(20));
			loc.setY(loc.getY() - 20);
			loc.getWorld().strikeLightningEffect(loc);
		}
		player.getWorld().playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 0.0F);
		return CastResult.SUCCESS;
	}

}
