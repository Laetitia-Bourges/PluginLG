package fr.wintersnow.monplugin.roles;

import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.functions.Role;

public abstract class Villageois extends Role {

	@Override
	public boolean isFriendly() {
		return true;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		p.sendMessage("§9 Votre but est de tuer les Loup Garous !!!");
	}
	

}
