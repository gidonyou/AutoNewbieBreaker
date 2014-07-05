package me.gidonyou.autoNb.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.gidonyou.autoNb.AutoNB;
import me.gidonyou.autoNb.handlers.GUIInventoryManager;

public class Anb extends AutoNBCommands{

	public Anb(AutoNB instance) {
		super(instance);
	}
	
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
		if(!commandLabel.equalsIgnoreCase("anb"))
			return false;
		if(!(sender instanceof Player)){
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "플레이어만 이용가능한 커맨드입니다.");
			return false;
		}
		Player player = (Player)sender;
		if(!(sender.isOp() || sender.hasPermission("anb.gui"))){
			player.sendMessage(ChatColor.DARK_RED + "커맨드를 이용할 권한이 없습니다.");
			return false;
		}
		player.openInventory(GUIInventoryManager.getinv());
		
		return true;
	}

}
