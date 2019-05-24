package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class Cupidon extends Villageois {
	
	private boolean power = true;
	
	public String getName() {		
		return "Cupidon";
	}

	@Override
	public RoleType getType() {
		return RoleType.Cupidon;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		ItemStack item = new ItemStack(Material.BOW, 1);
		item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		give(p, new ItemStack(Material.ARROW, 64), item);
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999*20, 0, false, false));
		
	}
	
	public boolean isPower() {
		return power;
	}
	
	public void setPower(boolean power) {
		this.power = power;
	}
	
	/* @Override
	public void onTick(Player player) {
		super.onTick(player);
		long time = (player.getWorld().getTime())%24000;
		}
	} */
	
}
