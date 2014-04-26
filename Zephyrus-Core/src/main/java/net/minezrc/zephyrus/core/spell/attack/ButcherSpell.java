package net.minezrc.zephyrus.core.spell.attack;

import java.util.List;
import java.util.Map;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.user.User;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

/**
 * Zephyrus - Butcher.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
public class ButcherSpell extends Spell implements ConfigurableSpell {

	private int radius;
	private int damage;

	public ButcherSpell() {
		super("butcher", "Butchers all monsters nearby", 100, 10, AspectList
				.newList(Aspect.BEAST, 30, Aspect.WEAPON, 30), 4, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Player player = user.getPlayer();
		int radius = this.radius * power;
		List<Entity> entities = player.getNearbyEntities(radius, radius, radius);
		for (Entity entity : entities) {
			if (entity instanceof Monster) {
				((Monster) entity).damage(damage);
			}
		}
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Radius", 5);
		map.put("Damage", 5);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.radius = config.getInt("Radius");
		this.damage = config.getInt("Damage");
	}

}
