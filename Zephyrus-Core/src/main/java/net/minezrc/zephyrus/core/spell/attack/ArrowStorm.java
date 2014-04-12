package net.minezrc.zephyrus.core.spell.attack;

import java.util.Map;

import net.minezrc.zephyrus.Zephyrus;
import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.spell.annotation.Prerequisite;
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
public class ArrowStorm extends Spell implements ConfigurableSpell {

	private int amount;

	public ArrowStorm() {
		super("arrowstorm", "A barrage of arrows", 150, 12, AspectList.newList()
				.setAspectTypes(Aspect.ATTACK, Aspect.WIND).setAspectValues(50, 25), 6, SpellElement.AIR, SpellType.ATTACK);
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
	public CastResult onCast(User user, int power, String[] args) {
		final Player player = user.getPlayer();
		final int amount = this.amount * power;
		Bukkit.getScheduler().runTaskTimer(Zephyrus.getPlugin(), new Runnable() {
			@Override
			public void run() {
				int shots = amount;
				if (shots > 0 && player.isOnline()) {
					player.launchProjectile(org.bukkit.entity.Arrow.class)
							.setMetadata("ignore_pickup", new FixedMetadataValue(Zephyrus.getPlugin(), true));
					shots--;
				}
			}
		}, 1, 1);
		return CastResult.SUCCESS;
	}

}
