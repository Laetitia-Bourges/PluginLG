package fr.wintersnow.monplugin.bukkitRennable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.wintersnow.monplugin.Main;
import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.functions.User;

public class GetRole extends BukkitRunnable  {

	@Override
	public void run() {
		
		Bukkit.getWorlds().get(0).setPVP(true);
		List<RoleType> types = new ArrayList<RoleType>();
		for(RoleType r : RoleType.values())
			types.add(r);
		types.add(RoleType.LoupBasic);
		types.add(RoleType.SimpleVillageois);
		for(Player player : Bukkit.getOnlinePlayers()) {
			User user = User.getUser(player);
			RoleType type = RoleType.SimpleVillageois;
			if(types.size() == 1)
				type=types.get(0);
			else if(types.size() > 1) 
				type=types.get(Main.random(1, types.size())-1);
			user.setRole(type.create());
			user.getRole().onGive(player);
			user.setNoFall(false);
			
		}
		
	}
	
}
