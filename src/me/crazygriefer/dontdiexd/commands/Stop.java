package me.crazygriefer.dontdiexd.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.crazygriefer.dontdiexd.GlobalVar;
import me.crazygriefer.dontdiexd.Main;
import net.md_5.bungee.api.ChatColor;

public class Stop implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public Stop(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ddstop").setExecutor((CommandExecutor) this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Send an error message when this command is ran from console (has no gui).
		if (!(sender instanceof Player)) {
			sender.sendMessage(GlobalVar.Prefix + ChatColor.RED + "Only players may execute this command!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("ddstop")) {
			Player p = (Player) sender;

		    // Continue if the player has permission to run this command.
		    if (p.hasPermission("stop")) {
		        		  	
		    	// Inform the players what is about to happen.
		    	Bukkit.broadcastMessage(GlobalVar.Prefix + "You are now safe.");
		    	
		    	// Enable the damage event listener.
		    	GlobalVar.DamageEnable = false;
		    	
		    } else {
		    	// Tell the player who ran the command that they have no permission to run it.
				p.sendMessage(GlobalVar.Prefix + ChatColor.RED + "You do not have permission to execute this command!");
			}
		        	
			
			
		}
		
		return false;
	}
	
}