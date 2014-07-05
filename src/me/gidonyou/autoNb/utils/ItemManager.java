package me.gidonyou.autoNb.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class ItemManager {

	public static ItemStack makeItem(Material material, int count, String name,
			String lore1, String lore2, String lore3, Enchantment enchant1,
			int enchant1LV, Enchantment enchant2, int enchant2LV, Enchantment enchant3, int enchant3LV) {
		ItemStack is = new ItemStack(material, count);
		ItemMeta im = is.getItemMeta();

		if (name != null)
			im.setDisplayName(name);

		List<String> lores = new ArrayList<String>();
		if (lore1 != null)
			lores.add(ChatColor.WHITE + lore1);
		if (lore2 != null)
			lores.add(ChatColor.WHITE + lore2);
		if (lore3 != null)
			lores.add(ChatColor.WHITE + lore3);
		if (lores.size() > 0)
			im.setLore(lores);

		if (enchant1 != null)
			im.addEnchant(enchant1, enchant1LV, true);
		if (enchant2 != null)
			im.addEnchant(enchant2, enchant2LV, true);
		if(enchant3 != null)
			im.addEnchant(enchant3, enchant3LV, true);

		is.setItemMeta(im);

		return is;
	}
	
	public static ItemStack getDye(String name, DyeColor color){
		Dye dye = new Dye();
		dye.setColor(color);
		
		ItemStack is = dye.toItemStack();
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return is;
	}

}
