package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.villageois.Sorciere;

public class SaveCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /save <player>

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {

				User user = User.getUser(player);
				Role role = user.getRole();
				if(role instanceof Sorciere) {
					Sorciere s = (Sorciere) role;
					if(!s.isPower()) {
						sender.sendMessage("§cVous avez déjà utilisé votre pouvoir.");
						return true;
					}
					
					String ciblename = args[0];
					Player cible = Bukkit.getPlayer(ciblename);
					if(cible != null) {

						if(cible.getGameMode() == GameMode.ADVENTURE) {

							User cUser = User.getUser(cible);
							if(cUser.getRole() == null) {
								sender.sendMessage("§cCe joueur est bugué.");
								return true;
							}
							cUser.getRole().onRespawn(cible);
							s.setPower(false);
							sender.sendMessage("§aVous avez réssucité " + cible.getName() + ".");
						}else {
							sender.sendMessage("§cCe joueur n'est pas mort ou est déjà mort.");
						}

					}else
						sender.sendMessage("§cJoueur introuvable.");
				}else {
					sender.sendMessage("§cCette commande est réservé à la sorciere.");
				}
			}else {
				sender.sendMessage("§cUsage : /save <player>");
			}
		}

		return true;
	}

}
