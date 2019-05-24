package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.functions.User;

public class TeamCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Usage : /team

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {

				User user = User.getUser(player);
				Role role = user.getRole();
				if(!role.isFriendly()) {
					String team = "Votre équipe de loup est composée de : ";
					for(Player ploup : Bukkit.getOnlinePlayers()) {
						User uloup = User.getUser(ploup);
						Role rloup = uloup.getRole();
						if(!rloup.isFriendly()) {
							team += uloup.getName(); 
							team += "   ";
						}
					player.sendMessage(team); //TODO: Commnent mettre le message en couleur ?
					}
					
				}else {
					sender.sendMessage("§cCette commande est réservé aux Loups.");
				}
			}else {
				sender.sendMessage("§cUsage : /team");
			}
		}

		return true;
	}

}
