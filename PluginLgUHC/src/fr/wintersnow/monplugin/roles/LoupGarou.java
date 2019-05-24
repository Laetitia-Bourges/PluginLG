package fr.wintersnow.monplugin.roles;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.functions.Role;

public abstract class LoupGarou extends Role {

	@Override
	public boolean isFriendly() {
		return false;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		p.sendMessage("§c Votre but est de tuer les Villageois !!!");
	}

	
	@Override
	public void onTick(Player player) {
		super.onTick(player);
		long time = (player.getWorld().getTime())%24000;
		if (time >= 12000 && time <= 12020) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600*20, 0, false, false));
			player.sendMessage("§aVous avez l'effet de force pendant 10 minutes");
		}
	}
}
