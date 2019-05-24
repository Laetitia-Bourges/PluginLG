package fr.wintersnow.monplugin.enums;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.wintersnow.monplugin.Main;
import fr.wintersnow.monplugin.bukkitRennable.GetRole;
import fr.wintersnow.monplugin.functions.User;

public class GameManager {

	public static GameManager instance = new GameManager();

	private State state = State.STOPPED;


	public void start() {
		state = State.STARTED;
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			User user = User.getUser(player);
			player.getInventory().clear();
			user.start(player);
		}
		
		BukkitRunnable task = new GetRole();
		task.runTaskLater(Main.instance, 400);
	}

	public void stop() {
		state = State.STOPPED;
	}

}
