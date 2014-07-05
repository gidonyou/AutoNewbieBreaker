package me.gidonyou.autoNb.listener;

import me.gidonyou.autoNb.AutoNB;
import me.gidonyou.autoNb.handlers.GUIInventoryManager;
import me.gidonyou.autoNb.threads.MainThread;
import me.gidonyou.autoNb.utils.SecondToMinute;

import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInventoryClicked extends AutoNBListeners {

	public PlayerInventoryClicked(AutoNB instance) {
		super(instance);
	}

	@EventHandler
	public void onPlayerInventoryClicked(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		if (!(event.getWhoClicked() instanceof Player))
			return;
		if (inv.getName() != GUIInventoryManager.getinv().getName())
			return;

		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (item == null || !item.hasItemMeta())
			return;
		ItemMeta im = item.getItemMeta();
		String name = im.getDisplayName();

		if (name.equals(ChatColor.GREEN + "작동중")) {
			plugin.getConfig().set("enabled", false);
			plugin.saveConfig();
			return;
		}
		if (name.equals(ChatColor.DARK_RED + "가동중지")) {
			plugin.getConfig().set("enabled", true);
			plugin.saveConfig();
			return;
		}
		if (name.equals(ChatColor.RED + "1" + ChatColor.GREEN + "분 추가")) {
			MainThread.timeLeft = MainThread.timeLeft + 60;
			return;
		}
		if (name.equals(ChatColor.BLUE + "1" + ChatColor.GREEN + "분 내리기")) {
			if (MainThread.timeLeft - 60 <= 0) {
				player.playNote(player.getLocation(), Instrument.PIANO,
						Note.natural(0, Tone.F));
				return;
			}
			MainThread.timeLeft = MainThread.timeLeft - 60;
			return;
		}
		if (name.equals(ChatColor.BLUE + "기본시간을 " + ChatColor.GREEN + "1분 "
				+ ChatColor.BLUE + "올리기.")) {
			plugin.getConfig().set("defaultTime",
					plugin.getConfig().getInt("defaultTime") + 60);
			plugin.saveConfig();
			player.sendMessage((ChatColor.GOLD + "[ANB] " + ChatColor.WHITE
					+ ChatColor.GREEN + "1분" + ChatColor.BLUE + "를 올려서 기본시간은 " + String
					.valueOf(SecondToMinute.formatTime(plugin.getConfig()
							.getInt("defaultTime")))));
		}if (name.equals(ChatColor.BLUE + "기본시간을 "
				+ ChatColor.YELLOW + "1분" + ChatColor.BLUE + " 내리기")) {
			if(plugin.getConfig().getInt("defaultTime") - 60 <= 0){
				player.playNote(player.getLocation(), Instrument.PIANO,
						Note.natural(0, Tone.F));
				return;
			}
			plugin.getConfig().set("defaultTime",
					plugin.getConfig().getInt("defaultTime") - 60);
			plugin.saveConfig();
			player.sendMessage((ChatColor.GOLD + "[ANB] " + ChatColor.WHITE
					+ ChatColor.YELLOW + "1분" + ChatColor.BLUE + "를 내려서 기본시간은 " + String
					.valueOf(SecondToMinute.formatTime(plugin.getConfig()
							.getInt("defaultTime")))));
		}
	}
}
