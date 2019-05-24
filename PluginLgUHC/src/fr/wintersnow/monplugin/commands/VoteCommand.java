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

public class VoteCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /vote <player>

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				User user = User.getUser(player);
				Role role = user.getRole();
				String ciblename = args[0];
				Player cible = Bukkit.getPlayer(ciblename);
				if(cible.getGameMode() == GameMode.SURVIVAL) {
					User cUser = User.getUser(cible);
					cUser.setNbVote();
					sender.sendMessage("Votre vote a bien été comptabilisé");
					if(cUser.getRole() == null) {
						sender.sendMessage("§cCe joueur est bugué.");
						return true;
					}
					return true;
					
				}else 
					sender.sendMessage("§cCe joueur est mort.");
				
			}else
				sender.sendMessage("§cJoueur introuvable.");
				
		}else 
			sender.sendMessage("§cUsage : /vote <player>");
		
		return true;
	}

}
