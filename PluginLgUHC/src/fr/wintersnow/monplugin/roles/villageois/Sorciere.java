package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.roles.Villageois;

public class Sorciere extends Villageois {

	private boolean power = true;

	public String getName() {		
		return "Sorciere";
	}

	@Override
	public RoleType getType() {
		return RoleType.Sorciere;
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
		give(p, new ItemStack(Material.POTION, 3, (short)16453), new ItemStack(Material.POTION, 1, (short)16385), new ItemStack(Material.POTION, 3, (short)16460));
	}

}
