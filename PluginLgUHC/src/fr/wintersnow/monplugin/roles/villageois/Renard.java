package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class Renard extends Villageois {
	
	private boolean power = true;
	private int nbPower = 3;
	
	public String getName() {		
		return "Renard";
	}

	@Override
	public RoleType getType() {
		return RoleType.Renard;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999*20, 0, false, false));
		
	}
	
	public boolean isPower() {
		return power;
	}
	
	public void setPower(boolean power) {
		this.power = power;
	}

	public int getNbPower() {
		return nbPower;
	}

	public void removeNbPower(int nbPowerLess) {
		this.nbPower = this.nbPower - nbPowerLess;
	}
	
	/* @Override
	public void onTick(Player player) {
		super.onTick(player);
		long time = (player.getWorld().getTime())%24000;
		}
	} */
	
}
