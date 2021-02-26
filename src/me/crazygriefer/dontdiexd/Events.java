package me.crazygriefer.dontdiexd;

import java.io.IOException;
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

	@SuppressWarnings("deprecation")
	@EventHandler
	// When an entity takes damage.
    public void onDamageEvent(EntityDamageEvent event) throws EventException, IOException {
		
		// If the event listener is enabled.
		if (GlobalVar.DamageEnable) {
		
			// Get the amount of damage delt.
			int NormalDamage = (int) event.getDamage();
			
			// Get the amount of damage the shield blocked. This is a negative integer.
			int ShieldDamage = (int) event.getOriginalDamage(EntityDamageEvent.DamageModifier.BLOCKING);
			
			// Get the total amount of damage delt.
			int TotalDamage = NormalDamage + ShieldDamage;

			// If the the entity took damage.
			if (TotalDamage > 0) {
			
				// If that entity was a player.
				if (event.getEntity() instanceof Player){
        	
					// Set the entity to variable p (Player).
					Player p = (Player) event.getEntity();
        	
					// If the player took so much damage that they died.
					if (p.isDead()) {
        		
						// Play a dragon growl sound at each player.
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
						}
					} else {
        			
						// Tell everybody who took damage.
						Bukkit.broadcastMessage(GlobalVar.Prefix + ChatColor.RED + p.getName() + ChatColor.YELLOW + " took damage! (" + ChatColor.RED + TotalDamage + ChatColor.YELLOW + " hp)");
        			
						// If the config file has ondamagevent 'mobspawn'.
						if (GlobalVar.OnDamageEvent == "mobspawn") {
        			
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
    }
}
