package me.crazygriefer.dontdiexd;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.md_5.bungee.api.ChatColor;

public class Events implements Listener {

	@EventHandler
	// When an entity takes damage.
    public void onDamageEvent(EntityDamageEvent event) throws EventException {
		
		// If the event listener is enabled.
		if (GlobalVar.DamageEnable) {
		
			// If that entity was a player.
			if (event.getEntity() instanceof Player){
        	
        		// Set the entity to variable p (Player).
        		Player p = (Player) event.getEntity();
        	
        		// If the player took so much damage that they died.
        		if (p.getHealth() == 0.0) {
        		
        			// Play a dragon growl sound at each player.
        			for (Player player : Bukkit.getOnlinePlayers()) {
        				player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f);
        			}
        		} else {
        			
        			// Tell everybody who took damage.
        			Bukkit.broadcastMessage(GlobalVar.Prefix + ChatColor.RED + p.getName() + ChatColor.YELLOW + " took damage!");
        			
        			// Get a random index in the mob list.
        			int rnd = new Random().nextInt(EntityType.values().length);
        			
        			// Set the error variable to false.
        			Boolean Error = false;
        			
        			// Spawn the randomly choosen mob.
        			try {
        				p.getWorld().spawnEntity(p.getLocation(), EntityType.values()[rnd]);
        			} catch (Exception except) {
        				// if the entity could not be spawned, set error to true.
        				Error = true;
        			}
        			
        			// If no error occured.
        			if (!Error) {
        				
        				// Tell the player who took damage what spawned.
            			p.sendMessage(GlobalVar.Prefix + "A new " + ChatColor.AQUA + EntityType.values()[rnd].getName() + ChatColor.YELLOW + " spawned!");
        				
        			}
        			
        			
        		}
        		
        	}
		}
    }
}
