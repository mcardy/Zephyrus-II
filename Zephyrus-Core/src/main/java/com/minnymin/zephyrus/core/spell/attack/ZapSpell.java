package com.minnymin.zephyrus.core.spell.attack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.minnymin.zephyrus.Configurable;
import com.minnymin.zephyrus.aspect.Aspect;
import com.minnymin.zephyrus.aspect.AspectList;
import com.minnymin.zephyrus.spell.Spell;
import com.minnymin.zephyrus.spell.SpellAttributes.CastResult;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellElement;
import com.minnymin.zephyrus.spell.SpellAttributes.SpellType;
import com.minnymin.zephyrus.spell.annotation.Bindable;
import com.minnymin.zephyrus.user.User;
import com.minnymin.zephyrus.user.targeted.Target.TargetType;
import com.minnymin.zephyrus.user.targeted.Targeted;

/**
 * Zephyrus - ZapSpell.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY, range = 30, friendly = false)
public class ZapSpell extends Spell implements Configurable {

	private int radius;
	private int range;
	
	public ZapSpell() {
		super("zap", "Zap your targets! Make em burn!!!", 1000, 100, AspectList.newList(Aspect.ENERGY, 200,
				Aspect.FIRE, 100, Aspect.LIGHT, 100), 12, SpellElement.LIGHT, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		Set<Entity> list = new HashSet<Entity>();
		LivingEntity en = (LivingEntity) user.getTarget(this).getTarget();
		en.getWorld().strikeLightning(en.getLocation());
		list.add(en);
		strikeEntities(en.getNearbyEntities(radius, radius, radius), user.getPlayer(), list, power);
		return CastResult.SUCCESS;
	}

	private void strikeEntities(List<Entity> e, Player player, Set<Entity> list, int power) {
		for (Entity en : e) {
			if (en instanceof LivingEntity && en != player && en.getLocation().distance(player.getLocation()) < range
					&& !list.contains(en)) {
				en.getWorld().strikeLightning(en.getLocation());
				list.add(en);
				strikeEntities(en.getNearbyEntities(radius, radius, radius), player, list, power);
			}
		}
	}
	
	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Radius", 8);
		map.put("Range", 50);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		radius = config.getInt("Radius");
		range = config.getInt("Range");
	}

}
