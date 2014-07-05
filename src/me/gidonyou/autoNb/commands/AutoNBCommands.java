package me.gidonyou.autoNb.commands;

import me.gidonyou.autoNb.AutoNB;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AutoNBCommands implements CommandExecutor {
	
	AutoNB plugin;
	
	public AutoNBCommands(AutoNB instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {
		return false;
	}

}
