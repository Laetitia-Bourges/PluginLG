package fr.wintersnow.monplugin.roles.loupgarous;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.LoupGarou;

public class VilainPetitLoup extends LoupGarou {

	public String getName() {		
		return "Vilain Petit Loup";
	}

	@Override
	public RoleType getType() {
		return RoleType.VilainPetitLoup;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 0, false, false));
	}
}
	
