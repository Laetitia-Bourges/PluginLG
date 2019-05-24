package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.villageois.Salvateur;

public class ProtectCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /protect <player>

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {

				User user = User.getUser(player);
				Role role = user.getRole();
				if(role instanceof Salvateur) {
					Salvateur s = (Salvateur) role;
					if(!s.isPower()) {
						sender.sendMessage("§cVous avez déjà utilisé votre pouvoir aujourd'hui.");
						return true;
					}
					
					String ciblename = args[0];
					Player cible = Bukkit.getPlayer(ciblename);
					if(cible != null) {

						if(cible.getGameMode() == GameMode.SURVIVAL) {

							User cUser = User.getUser(cible);
							if(cUser.getRole() == null) {
								sender.sendMessage("§cCe joueur est bugué.");
								return true;
							}
							
							if(s.getLastSave() == cible) {
								sender.sendMessage("Vous ne pouvez pas protéger la même personne deux fois d'affiler");
								return true;
							} else {
								s.setPower(false);
								cible.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600*20, 0, false, false));
								/*cible.addPotionEffect(new PotionEffect(??????, 600*20, 0, false, false));*/ //TODO: Mettre NoFall
								cible.sendMessage("§aVous avez été protéger par le Salavateur");
								sender.sendMessage("§aVous avez protégé : " + cUser.getName());
								s.setLastSave(cible);
							}
						}else {
							sender.sendMessage("§cCe joueur est mort.");
						}

					}else
						sender.sendMessage("§cJoueur introuvable.");
				}else {
					sender.sendMessage("§cCette commande est réservé au Salvateur.");
				}
			}else {
				sender.sendMessage("§cUsage : /protect <player>");
			}
		}

		return true;
	}

}
