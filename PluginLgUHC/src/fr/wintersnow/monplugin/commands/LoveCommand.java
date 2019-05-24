package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.roles.villageois.Cupidon;

public class LoveCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /love <player1> <player2>

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 2) {

				User user = User.getUser(player);
				Role role = user.getRole();
				if(role instanceof Cupidon) {
					Cupidon s = (Cupidon) role;
					if(!s.isPower()) {
						sender.sendMessage("§cVous ne pouvez plus utiliser votre pouvoir");
						return true;
					}
					
					String ciblename1 = args[0];
					String ciblename2 = args[1];
					Player cible1 = Bukkit.getPlayer(ciblename1);
					Player cible2 = Bukkit.getPlayer(ciblename2);
					if(cible1 != null && cible2 != null) {

						if(cible1.getGameMode() == GameMode.SURVIVAL && cible2.getGameMode() == GameMode.SURVIVAL) {

							User cUser1 = User.getUser(cible1);
							User cUser2 = User.getUser(cible2);
							if(cUser1.getRole() == null || cUser2.getRole() == null) {
								sender.sendMessage("§cUn des joueurs est bugué.");
								return true;
							}
							s.setPower(false);
							
							cUser1.getRole().setCouple(cible2);
							cUser2.getRole().setCouple(cible1);
							cUser1.getRole().give(cible1, new ItemStack(Material.COMPASS));
							cUser2.getRole().give(cible2, new ItemStack(Material.COMPASS));
							
							cible1.sendMessage("§bVous êtes en couple avec " + cUser2.getName() + "\n Votre but est maintenant de gagner avec votre amoureux/euse et avec votre cupidon");
							cible2.sendMessage("§bVous êtes en couple avec " + cUser1.getName() + "\n Votre but est maintenant de gagner avec votre amoureux/euse et avec votre cupidon");
						}else {
							sender.sendMessage("§cCe joueur est mort.");
						}

					}else
						sender.sendMessage("§cJoueur introuvable.");
				}else {
					sender.sendMessage("§cCette commande est réservé au Cupidon.");
				}
			}else {
				sender.sendMessage("§cUsage : /love <player1> <player2>");
			}
		}

		return true;
	}

}
