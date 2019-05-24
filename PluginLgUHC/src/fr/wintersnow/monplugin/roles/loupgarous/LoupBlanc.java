package fr.wintersnow.monplugin.roles.loupgarous;

import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.LoupGarou;

public class LoupBlanc extends LoupGarou {

	public String getName() {		
		return "Loup Garou Blanc";
	}

	@Override
	public RoleType getType() {
		return RoleType.LoupBlanc;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		p.setMaxHealth(16*2d);
		p.setHealth(16*2d);
	}

	
}
	
