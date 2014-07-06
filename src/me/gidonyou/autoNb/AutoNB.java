package me.gidonyou.autoNb;

import me.gidonyou.autoNb.commands.Anb;
import me.gidonyou.autoNb.commands.Nb;
import me.gidonyou.autoNb.handlers.GUIInventoryManager;
import me.gidonyou.autoNb.listener.PlayerInventoryClicked;
import me.gidonyou.autoNb.threads.MainThread;

import org.bukkit.plugin.java.JavaPlugin;

public class AutoNB extends JavaPlugin {

	private final MainThread mt = new MainThread(this);

	@SuppressWarnings("deprecation")
	public void onEnable() {
		mt.enable();
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, mt, 20l,
				20l);

		getServer().getPluginManager().registerEvents(
				new PlayerInventoryClicked(this), this);
		saveDefaultConfig();
		saveConfig();

		getCommand("anb").setExecutor(new Anb(this));
		getCommand("nb").setExecutor(new Nb(this));

	}

	public void onDisable() {
		getServer().getScheduler().cancelAllTasks();
		saveConfig();
	}

	public boolean getState() {
		return getConfig().getBoolean("enabled");
	}

	public void updateGui() {
		new GUIInventoryManager(this).update();
	}

}
