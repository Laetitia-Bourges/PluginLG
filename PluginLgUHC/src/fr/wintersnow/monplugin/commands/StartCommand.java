package fr.wintersnow.monplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.wintersnow.monplugin.enums.GameManager;

public class StartCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		player.getWorld().setTime(1000);
		player.getWorld().setPVP(false);
		GameManager.instance.start();
		Bukkit.broadcastMessage("§aLa partie commence !!");
		return true;
	}

}
