package fr.wintersnow.monplugin.roles.loupgarous;

import org.bukkit.entity.Player;


import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.LoupGarou;

public class LoupBasic extends LoupGarou {

	public String getName() {		
		return "Loup";
	}

	@Override
	public RoleType getType() {
		return RoleType.LoupBasic;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
	}
	
	
}
	
