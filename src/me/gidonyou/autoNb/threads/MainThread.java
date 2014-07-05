package me.gidonyou.autoNb.threads;

import me.gidonyou.autoNb.AutoNB;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MainThread implements Runnable {

	protected AutoNB plugin;

	public MainThread(AutoNB instance) {
		plugin = instance;
	}

	public static int timeLeft;

	private String perm = "newbieban.nb";

	public void enable() {
		timeLeft = plugin.getConfig().getInt("defaultTime");

	}

	public void run() {
		boolean isEnabled = plugin.getState();
		plugin.updateGui();
		if (!isEnabled)
			return;

		if (timeLeft > 0) {
			timeLeft--;

		} else {
			timeLeft = plugin.getConfig().getInt("defaultTime");
			if (Bukkit.getOnlinePlayers().length == 0) {
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.GOLD + "[ANB] " + ChatColor.RED
								+ "�÷����̾ �Ѹ� ��� ���� ���߽��ϴ�.");
				return;
			}
			Player player = Bukkit.getOnlinePlayers()[0];
			if (!player.hasPermission(perm)) {

				try {
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "���� �޹̼��� �����մϴ�.");
					plugin.getServer().dispatchCommand(
							Bukkit.getConsoleSender(),
							"pex user " + player.getName() + " add " + perm);
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "���� �޹̼��� �����߽��ϴ�.");

					plugin.getServer().dispatchCommand(player, "nb");
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.BLUE
									+ "�� ���������� Ŀ�ǵ带 �����߽��ϴ�.");

				} catch (Exception e) {
					e.printStackTrace();
					Bukkit.broadcastMessage(ChatColor.GOLD + "[ANB]"
							+ ChatColor.RED + "�ڵ� ��������ϴ��� �ַ��� �߻��߽��ϴ�.");
				} finally {
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "���� �޹̼��� �����մϴ�.");
					plugin.getServer().dispatchCommand(
							Bukkit.getConsoleSender(),
							"pex user " + player.getName() + " remove " + perm);
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "���� �޹̼��� �����߽��ϴ�..");
				}
				return;
			} else {
				plugin.getServer().dispatchCommand(player, "nb");
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
								+ player.getName() + ChatColor.BLUE
								+ "�� ���������� Ŀ�ǵ带 �����߽��ϴ�.");
				return;
			}
		}

	}

}
