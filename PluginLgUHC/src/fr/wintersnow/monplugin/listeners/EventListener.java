package fr.wintersnow.monplugin.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.wintersnow.monplugin.functions.User;

public class EventListener implements Listener{
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		User user = User.getUser(p);
		if(user.getRole() != null) {
			user.getRole().onDeath(e);
		}
	}
	

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			User user = User.getUser(p);
			
			if(e.getCause()==DamageCause.FALL && user.isNoFall()) 
				e.setCancelled(true);
			
		}
	}
}