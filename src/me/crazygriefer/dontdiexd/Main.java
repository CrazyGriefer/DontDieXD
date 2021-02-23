package me.crazygriefer.dontdiexd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.crazygriefer.dontdiexd.commands.Start;
import me.crazygriefer.dontdiexd.commands.Stop;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	public static Main plugin;
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "Starting DontDieXD");
		
		// Enable the commands.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "Enabling commands");
		new Start(this);
		new Stop(this);
		
		// Enable the commands.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "Registering the event listener.");
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		// Stating that the plugin has started
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.GREEN + "DontDieXD has successfully been enabled!");
				
		// Providing very useful information.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "This plugin is free. If you paid for it you have been swindled!");
	}
}
