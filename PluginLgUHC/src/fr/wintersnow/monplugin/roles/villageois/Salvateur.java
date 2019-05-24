package fr.wintersnow.monplugin.roles.villageois;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.Villageois;

public class Salvateur extends Villageois {

	private boolean power = true;
	private Player lastSave;

	public String getName() {		
		return "Salvateur";
	}

	@Override
	public RoleType getType() {
		return RoleType.Salvateur;
	}

	@Override
	public void onGive(Player p) {
		super.onGive(p);
		give(p, new ItemStack(Material.POTION, 2, (short)16453));

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
			player.sendMessage("§aIl est 6h00, vous avez récuperé votre pouvoir : /protect <joueur>");
			if(getLastSave() != null) {
				User.getUser(getLastSave()).setNoFall(false);
				getLastSave().sendMessage("§cVous n'êtes plus protéger par le salvateur !");
			}
		} else if (isPower() && time >= 120 && time <= 140) {
			setPower(false);
			player.sendMessage("§cTrop tard pour le pouvoir ...");
		}
	}

	public Player getLastSave() {
		return lastSave;
	}

	public void setLastSave(Player lastSave) {
		this.lastSave = lastSave;
	}

}
