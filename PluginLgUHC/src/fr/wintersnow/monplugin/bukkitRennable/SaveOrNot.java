package fr.wintersnow.monplugin.bukkitRennable;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.villageois.Sorciere;

public class SaveOrNot extends BukkitRunnable {

	Player lastDead = null;
	Location locLastDead = null;
	
	public void setLastDead(Player p) {
		lastDead=p;
		locLastDead=p.getLocation();
	}

	@Override
	public void run() {
		Player sorciere = null;
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			User u = User.getUser(player);
			if (u.getRole().getName() == "Sorciere") {
				sorciere = player;
			}
			
		}
		
		User sorc = User.getUser(sorciere);
		Role role = sorc.getRole();
			Sorciere s = (Sorciere) role;
		
		if (s.isPower()) {
			
			for (ItemStack i : lastDead.getInventory()) {
				ItemStack item = i;
				Bukkit.getWorlds().get(0).dropItem(locLastDead, item);
			}
			
			lastDead.setGameMode(GameMode.SPECTATOR);
			
			User user = User.getUser(lastDead);
			Bukkit.broadcastMessage("§3" + user.getName() + " est mort, il était : " + user.getRole().getName());
			
			if(user.getRole().getCouple() != null) {
				User uC = User.getUser(user.getRole().getCouple());
				if(uC.getRole() != null) 
					uC.getRole().onDeathCouple(user.getRole().getCouple(), lastDead);
			}
			
		} 
		
	}
	
	
}

	

