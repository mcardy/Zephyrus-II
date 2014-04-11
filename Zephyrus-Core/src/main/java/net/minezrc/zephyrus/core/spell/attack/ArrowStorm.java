package net.minezrc.zephyrus.core.spell.attack;

import java.util.Map;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.spell.Bindable;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Prerequisite;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.user.User;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Zephyrus - ArrowStorm.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Prerequisite(requiredSpell = Arrow.class)
public class ArrowStorm implements Spell, ConfigurableSpell {

	private int amount;

	@Override
	public String getName() {
		return "arrowstorm";
	}

	@Override
	public String getDescription() {
		return "A barrage of arrows";
	}

	@Override
	public int getRequiredLevel() {
		return 6;
	}

	@Override
	public int getManaCost() {
		return 150;
	}

	@Override
	public int getXpReward() {
		return 12;
	}

	@Override
	public AspectList getRecipe() {
		return AspectList.newList(Aspect.ATTACK, 50);
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		return DataStructureUtils.createMap(DataStructureUtils.createList("amount"), DataStructureUtils
				.createList((Object) 10));
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		amount = config.getInt("amount");
	}

	@Override
	public SpellElement getElement() {
		return SpellElement.AIR;
	}

	@Override
	public SpellType getType() {
		return SpellType.ATTACK;
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
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
					player.launchProjectile(org.bukkit.entity.Arrow.class)
							.setMetadata("removal_flag", new FixedMetadataValue(Zephyrus.getPlugin(), true));
					shots--;
				}
			}
		}, 1, 1);
		return CastResult.SUCCESS;
	}

}
