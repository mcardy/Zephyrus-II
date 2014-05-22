package com.minnymin.zephyrus.core.spell.attack;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.Zephyrus;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.core.util.DataStructureUtils;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.spell.annotation.Prerequisite;
import com.minnymin.zephyrus.user.User;

/**
 * Zephyrus - ArrowStorm.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Prerequisite(requiredSpell = ArrowSpell.class)
public class ArrowStormSpell extends Spell implements Configurable {

	private int amount;

	public ArrowStormSpell() {
		super("arrowstorm", "A barrage of arrows", 150, 12, AspectList
				.newList(Aspect.WIND, 50, Aspect.WEAPON, 50, Aspect.WOOD, 50), 6, SpellElement.AIR, SpellType.ATTACK);
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Amount", 10);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		amount = config.getInt("Amount");
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		final Player player = user.getPlayer();
		final int amount = this.amount * power;
		Bukkit.getScheduler().runTaskTimer(Zephyrus.getPlugin(), new Runnable() {
			@Override
			public void run() {
				int shots = amount;
				if (shots > 0 && player.isOnline()) {
					player.launchProjectile(Arrow.class)
							.setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
					shots--;
				}
			}
		}, 1, 1);
		return CastResult.SUCCESS;
	}

}
