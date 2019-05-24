package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class PetiteFille extends Villageois {

	private boolean power = true;

	public String getName() {		
		return "PetiteFille";
	}

	@Override
	public RoleType getType() {
		return RoleType.PetiteFille;
	}

	public boolean isPower() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}

	@Override
	public void onGive(Player p) {
		super.onGive(p);
		give(p, new ItemStack(Material.TNT, 5));
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999*20, 0, false, false));
	}
	
	@Override
	public void onTick(Player player) {
		super.onTick(player);
		long time = (player.getWorld().getTime())%24000;
		if (time >= 12000 && time <= 12020) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999*20, 0, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 9999*20, 0, false, false));
			player.sendMessage("§cAttention vous êtes invisible et vous avez l'effet de faiblesse!");
		}
		else if (time >= 0 && time <= 20) {
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			player.removePotionEffect(PotionEffectType.WEAKNESS);
			player.sendMessage("§aVous êtes de nouveau visible.");
		}
	}

}
