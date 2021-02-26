package me.crazygriefer.dontdiexd;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.crazygriefer.dontdiexd.commands.SelectEvent;
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
		new SelectEvent(this);
		
		// Enable the commands.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "Registering the event listener.");
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		// Configuring the config file.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "Configuring the config file.");
		
		FileConfiguration config = this.getConfig();
		config.addDefault("OnDamage.Event", "mobspawn");
		
		config.options().copyDefaults(true);
		saveConfig();
		
		// Importing the config file.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "Importing settings from " + ChatColor.GREEN + "config.yml" + ChatColor.BLUE + ".");
		GlobalVar.OnDamageEvent = getConfig().getString("OnDamage.Event");
		
		// Stating that the plugin has started
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.GREEN + "DontDieXD has successfully been enabled!");
				
		// Providing very useful information.
		Bukkit.getConsoleSender().sendMessage(GlobalVar.Prefix + ChatColor.BLUE + "This plugin is free. If you paid for it you have been swindled!");
	}
}
