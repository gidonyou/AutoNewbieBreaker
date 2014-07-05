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
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "�÷��̾ �̿밡���� Ŀ�ǵ��Դϴ�.");
			return false;
		}
		Player player = (Player)sender;
		if(!(sender.isOp() || sender.hasPermission("anb.gui"))){
			player.sendMessage(ChatColor.DARK_RED + "Ŀ�ǵ带 �̿��� ������ �����ϴ�.");
			return false;
		}
		player.openInventory(GUIInventoryManager.getinv());
		
		return true;
	}

}
