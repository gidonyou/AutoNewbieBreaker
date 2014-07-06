package me.gidonyou.autoNb.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.gidonyou.autoNb.AutoNB;
import me.gidonyou.autoNb.method.DoNB;

public class Nb extends AutoNBCommands {

	public Nb(AutoNB instance) {
		super(instance);
	}
	
	protected DoNB nb;

	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String[] args) {

		if (!sender.hasPermission("newbieban.nb"))
			return false;
		
		if(nb == null)
			nb = new DoNB(plugin);
		
		nb.nb();
		
		return true;

		/*
		 * 
		 * if(!commandLabel.equalsIgnoreCase("nb")) return false;
		 * 
		 * if(args.length == 0){
		 * 
		 * return false; }
		 * 
		 * if(args[0].equalsIgnoreCase("start")){ }
		 * 
		 * 
		 * return false;
		 */}

}
