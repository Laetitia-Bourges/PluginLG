package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.villageois.Renard;

public class FlairCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /flair <player>

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {

				User user = User.getUser(player);
				Role role = user.getRole();
				if(role instanceof Renard) {
					Renard s = (Renard) role;
					if(!s.isPower()) {
						sender.sendMessage("§cVous n'avez plus votre pouvoir.");
						return true;
					}
					
					String ciblename = args[0];
					Player cible = Bukkit.getPlayer(ciblename);
					if(cible != null) {

						if(cible.getGameMode() == GameMode.SURVIVAL) {

							User cUser = User.getUser(cible);
							if(cible.getLocation().distance(player.getLocation()) <= 20.5D) {
								if(cUser.getRole() == null) {
									sender.sendMessage("§cCe joueur est bugué.");
									return true;
								}
								
								else if (cUser.getRole().isFriendly()) {
									player.sendMessage("§aCe joueur est gentil :)");
										
								}
								
								else {
									player.sendMessage("§aCe joueur est méchant :(");
								}
								
								s.removeNbPower(1);

								if (s.getNbPower() == 0) {
									s.setPower(false);
								}
							} else {
								sender.sendMessage("§aLe joueur que vous voulez flairer est trop loin de vous !");
							}
							
						}else {
							sender.sendMessage("§cCe joueur est mort.");
						}

					}else
						sender.sendMessage("§cJoueur introuvable.");
				}else {
					sender.sendMessage("§cCette commande est réservé au Renard.");
				}
			}else {
				sender.sendMessage("§cUsage : /flair <player>");
			}
		}

		return true;
	}

}
