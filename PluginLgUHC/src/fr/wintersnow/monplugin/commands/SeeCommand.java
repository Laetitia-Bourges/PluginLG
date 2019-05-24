package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.villageois.Voyante;

public class SeeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /see <player>

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {

				User user = User.getUser(player);
				Role role = user.getRole();
				if(role instanceof Voyante) {
					Voyante s = (Voyante) role;
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
							s.setPower(false);
							player.sendMessage("§aCe joueur est " + cUser.getRole().getName());
						}else {
							sender.sendMessage("§cCe joueur est mort.");
						}

					}else
						sender.sendMessage("§cJoueur introuvable.");
				}else {
					sender.sendMessage("§cCette commande est réservé à la Voyante.");
				}
			}else {
				sender.sendMessage("§cUsage : /see <player>");
			}
		}

		return true;
	}

}
