package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class Voyante extends Villageois {
	
	private boolean power = true;
	
	public String getName() {		
		return "Voyante";
	}

	@Override
	public RoleType getType() {
		return RoleType.Voyante;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		give(p, new ItemStack(Material.OBSIDIAN, 4), new ItemStack(Material.BOOKSHELF, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999*20, 0, false, false));
		
	}
	
	public boolean isPower() {
		return power;
	}
	
	public void setPower(boolean power) {
		this.power = power;
	}
	
	@Override
	public void onTick(Player player) {
		super.onTick(player);
		long time = (player.getWorld().getTime())%24000;
		if (time >= 0 && time <= 20) {
			setPower(true);
			player.sendMessage("§aIl est 6h00, vous avez récuperé votre pouvoir : /see <joueur>");
		}
	}
	
}
