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
								+ "플레이이어가 한명도 없어서 밴을 못했습니다.");
				return;
			}
			Player player = Bukkit.getOnlinePlayers()[0];
			if (!player.hasPermission(perm)) {

				try {
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "에게 펄미션을 적용합니다.");
					plugin.getServer().dispatchCommand(
							Bukkit.getConsoleSender(),
							"pex user " + player.getName() + " add " + perm);
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "에게 펄미션을 적용했습니다.");

					plugin.getServer().dispatchCommand(player, "nb");
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.BLUE
									+ "이 성공적으로 커맨드를 실행했습니다.");

				} catch (Exception e) {
					e.printStackTrace();
					Bukkit.broadcastMessage(ChatColor.GOLD + "[ANB]"
							+ ChatColor.RED + "자동 뉴비밴을하는중 애러가 발생했습니다.");
				} finally {
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "에게 펄미션을 제거합니다.");
					plugin.getServer().dispatchCommand(
							Bukkit.getConsoleSender(),
							"pex user " + player.getName() + " remove " + perm);
					Bukkit.getConsoleSender().sendMessage(
							ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
									+ player.getName() + ChatColor.GREEN
									+ "에게 펄미션을 제거했습니다..");
				}
				return;
			} else {
				plugin.getServer().dispatchCommand(player, "nb");
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.GOLD + "[ANB] " + ChatColor.YELLOW
								+ player.getName() + ChatColor.BLUE
								+ "이 성공적으로 커맨드를 실행했습니다.");
				return;
			}
		}

	}

}
