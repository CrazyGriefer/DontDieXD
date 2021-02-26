package me.crazygriefer.dontdiexd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.crazygriefer.dontdiexd.GlobalVar;
import me.crazygriefer.dontdiexd.Main;
import net.md_5.bungee.api.ChatColor;

public class SelectEvent implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public SelectEvent(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ddevent").setExecutor((CommandExecutor) this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Send an error message when this command is ran from console (has no gui).
		if (!(sender instanceof Player)) {
			sender.sendMessage(GlobalVar.Prefix + ChatColor.RED + "Only players may execute this command!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("ddevent")) {
			Player p = (Player) sender;

		    // Continue if the player has permission to run this command.
		    if (p.hasPermission("event")) {
		        		  	
		    	// If no arguments are given.
				if (args.length == 0) {
					
					// Set the eventcolor to green (default).
					ChatColor eventcolor = ChatColor.GREEN;
				
					// Explain the syntax to the player.
					p.sendMessage(GlobalVar.Prefix + "Current selected event: " + eventcolor + GlobalVar.OnDamageEvent + ChatColor.YELLOW + ".");
					
					return true;
				}
							
				
				// If the first argument (index 0) is 'nothing'.
				if (args[0].contentEquals("mobspawn")) {
					
					// Set the ondamageevent to shutdown.
					GlobalVar.OnDamageEvent = "mobspawn";
					
					// Change the config.yml file.
					plugin.getConfig().set("OnDamage.Event", GlobalVar.OnDamageEvent);
					plugin.saveConfig();
					
					// Confirm to the player the mob they selected.
					p.sendMessage(GlobalVar.Prefix + "You selected the event " + ChatColor.GREEN + GlobalVar.OnDamageEvent + ".");
					
					return true;
				}
				
				// If the first argument (index 0) is 'nothing'.
				if (args[0].contentEquals("nothing")) {
					
					// Set the ondamageevent to shutdown.
					GlobalVar.OnDamageEvent = "nothing";
					
					// Change the config.yml file.
					plugin.getConfig().set("OnDamage.Event", GlobalVar.OnDamageEvent);
					plugin.saveConfig();
					
					// Confirm to the player the mob they selected.
					p.sendMessage(GlobalVar.Prefix + "You selected the event " + GlobalVar.OnDamageEvent + ".");	
				
					return true;
				}
				
				// If none of these if-statements returned true (so an invalid argument is given).
				p.sendMessage(GlobalVar.Prefix + ChatColor.RED + "Usage: /ddevent [mobspawn | nothing]");
		    	
		    } else {
		    	// Tell the player who ran the command that they have no permission to run it.
				p.sendMessage(GlobalVar.Prefix + ChatColor.RED + "You do not have permission to execute this command!");
			}
			
		}
		
		return false;
	}
	
}