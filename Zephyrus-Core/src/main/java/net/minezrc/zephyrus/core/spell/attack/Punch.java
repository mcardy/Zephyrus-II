package net.minezrc.zephyrus.core.spell.attack;

import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;

import net.minezrc.zephyrus.aspect.Aspect;
import net.minezrc.zephyrus.aspect.AspectList;
import net.minezrc.zephyrus.core.util.DataStructureUtils;
import net.minezrc.zephyrus.spell.ConfigurableSpell;
import net.minezrc.zephyrus.spell.Spell;
import net.minezrc.zephyrus.spell.SpellAttributes.CastResult;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellElement;
import net.minezrc.zephyrus.spell.SpellAttributes.SpellType;
import net.minezrc.zephyrus.spell.SpellAttributes.TargetType;
import net.minezrc.zephyrus.spell.annotation.Bindable;
import net.minezrc.zephyrus.spell.annotation.Targeted;
import net.minezrc.zephyrus.user.User;

/**
 * Zephyrus - Punch.java
 * 
 * @author minnymin3
 * 
 */

@Bindable
@Targeted(type = TargetType.ENTITY)
public class Punch extends Spell implements ConfigurableSpell {

	private int damage;
	
	public Punch() {
		super("punch", "Punches your target with a superpunch", 25, 2, AspectList.newList()
				.setAspectTypes(Aspect.ATTACK, Aspect.POWER).setAspectValues(8, 8), 1, SpellElement.NEUTREAL, SpellType.ATTACK);
	}

	@Override
	public CastResult onCast(User user, int power, String[] args) {
		user.getTarget(this).getEntity().damage(damage * power, user.getPlayer());
		return CastResult.SUCCESS;
	}

	@Override
	public Map<String, Object> getDefaultConfiguration() {
		Map<String, Object> map = DataStructureUtils.createConfigurationMap();
		map.put("Damage", 2);
		return map;
	}

	@Override
	public void loadConfiguration(ConfigurationSection config) {
		this.damage = config.getInt("Damage");
	}

}
