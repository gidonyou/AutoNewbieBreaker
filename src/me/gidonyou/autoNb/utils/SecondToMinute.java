package me.gidonyou.autoNb.utils;

import org.bukkit.ChatColor;

public class SecondToMinute {

	public static String formatTime(int second) {
		int minutes = second / 60;
		int seconds = second % 60;
		String disMinu = (minutes < 10 ? "0" : "") + minutes;
		String disSec = (seconds < 10 ? "0" : "") + seconds;
		String formattedTime = ChatColor.YELLOW + disMinu + ChatColor.GOLD +"Ка " + ChatColor.YELLOW + disSec + ChatColor.GOLD +"УЪ";

		return formattedTime;
	}
}
