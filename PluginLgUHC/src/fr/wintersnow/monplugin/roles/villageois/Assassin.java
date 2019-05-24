package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class Assassin extends Villageois {
	
	public String getName() {		
		return "Assassin";
	}

	@Override
	public RoleType getType() {
		return RoleType.Assassin;
	}
	
	@Override
	public void onGive(Player p) {
		super.onGive(p);
		
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemStack item2 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemStack item3 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		item2.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 3);
		item3.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		give(p, item, item2, item3);
		
		
	}
	
	@Override
	public void onTick(Player player) {
		super.onTick(player);
		long time = (player.getWorld().getTime())%24000;
		if (time >= 0 && time <= 20) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600*20, 0, false, false));
			player.sendMessage("§aVous avez l'effet de force pendant 10 minutes");
		}
	}
	
}
