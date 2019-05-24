package fr.wintersnow.monplugin.roles.villageois;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class SimpleVillageois extends Villageois {
	
	public String getName() {		
		return "Simple Villageois";
	}

	@Override
	public RoleType getType() {
		return RoleType.SimpleVillageois;
	}
	
}
