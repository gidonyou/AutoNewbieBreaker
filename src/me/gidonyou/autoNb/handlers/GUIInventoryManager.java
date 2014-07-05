package me.gidonyou.autoNb.handlers;

import me.gidonyou.autoNb.AutoNB;
import me.gidonyou.autoNb.threads.MainThread;
import me.gidonyou.autoNb.utils.ItemManager;
import me.gidonyou.autoNb.utils.SecondToMinute;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class GUIInventoryManager {
	protected AutoNB plugin;

	public GUIInventoryManager(AutoNB instance) {
		plugin = instance;
	}

	// protected final MainThread mt = new MainThread(plugin);

	private static Inventory inv;

	public static Inventory getinv() {
		return inv;
	}

	public void update() {
		if (inv == null) {
			inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "ANB " + ChatColor.BLUE + "��Ʈ�� ����");
			return;
		}
		boolean isenabled = plugin.getState();

		inv.setItem(8, ItemManager.makeItem(Material.WATCH, 1,
				SecondToMinute.formatTime(MainThread.timeLeft), null, null,
				null, null, 0, null, 0, null, 0));
		if (isenabled) {
			inv.setItem(0, ItemManager.makeItem(Material.EMERALD_BLOCK, 1,
					ChatColor.GREEN + "�۵���", ChatColor.ITALIC + "��Ŭ���Ͻø� �����ϴ�.",
					null, null, null, 0, null, 0, null, 0));

			inv.setItem(
					2,
					ItemManager.getDye(ChatColor.RED + "1" + ChatColor.GREEN
							+ "�� �߰�", DyeColor.RED));
			inv.setItem(
					3,
					ItemManager.getDye(ChatColor.BLUE + "1" + ChatColor.GREEN
							+ "�� ������", DyeColor.BLUE));

			inv.setItem(5, ItemManager.getDye(ChatColor.BLUE + "�⺻�ð��� "
					+ ChatColor.GREEN + "1�� " + ChatColor.BLUE + "�ø���.",
					DyeColor.GREEN));
			inv.setItem(6, ItemManager.getDye(ChatColor.BLUE + "�⺻�ð��� "
					+ ChatColor.YELLOW + "1��" + ChatColor.BLUE + " ������",
					DyeColor.YELLOW));

		} else {
			inv.setItem(0, ItemManager.makeItem(Material.REDSTONE_BLOCK, 1,
					ChatColor.DARK_RED + "��������", ChatColor.ITALIC
							+ "��Ŭ���Ͻø� �����ϴ�.", null, null, null, 0, null, 0,
					null, 0));
			for(int i = 1; i <7; i++)
				inv.setItem(i, null);
		}
	}

}
