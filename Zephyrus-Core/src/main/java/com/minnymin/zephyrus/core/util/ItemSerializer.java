package com.minnymin.zephyrus.core.util;

import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Zephyrus - ItemSerializer.java
 * 
 * @author minnymin3
 * 
 */

public class ItemSerializer {

	@SuppressWarnings("deprecation")
	public static ItemStack fromString(String item) {
		JSONObject obj;
		try {
			obj = (JSONObject) new JSONParser().parse(item);
		} catch (ParseException e) {
			System.out.println("Error loading itemstack " + item);
			return null;
		}
		int id = ((Long)obj.get("id")).intValue();
		short data = ((Long)obj.get("data")).shortValue();
		int amount = ((Long)obj.get("amount")).intValue();
		return new ItemStack(id, amount, (short)data);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static String toString(ItemStack item) {
		JSONObject obj = new JSONObject();
		obj.put("id", item.getTypeId());
		obj.put("data", item.getDurability());
		obj.put("amount", item.getAmount());
		return obj.toJSONString();
	}
	
}
