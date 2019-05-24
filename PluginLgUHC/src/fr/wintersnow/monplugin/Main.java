package fr.wintersnow.monplugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.wintersnow.monplugin.bukkitRennable.GetRole;
import fr.wintersnow.monplugin.commands.FlairCommand;
import fr.wintersnow.monplugin.commands.LoveCommand;
import fr.wintersnow.monplugin.commands.ProtectCommand;
import fr.wintersnow.monplugin.commands.SaveCommand;
import fr.wintersnow.monplugin.commands.SeeCommand;
import fr.wintersnow.monplugin.commands.StartCommand;
import fr.wintersnow.monplugin.commands.TeamCommand;
import fr.wintersnow.monplugin.commands.VoteCommand;
import fr.wintersnow.monplugin.enums.RoleType;
import fr.wintersnow.monplugin.functions.User;
import fr.wintersnow.monplugin.listeners.EventListener;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		System.out.println("le plugin viens de s'allumer");
		getCommand("flair").setExecutor(new FlairCommand());
		getCommand("love").setExecutor(new LoveCommand());
		getCommand("protect").setExecutor(new ProtectCommand());
		getCommand("save").setExecutor(new SaveCommand());
		getCommand("see").setExecutor(new SeeCommand());
		getCommand("team").setExecutor(new TeamCommand());
		getCommand("start").setExecutor(new StartCommand());
		getCommand("vote").setExecutor(new VoteCommand());
		this.getServer().getPluginManager().registerEvents(new EventListener(), this);
		
		instance = this;
		
		/*Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					User user = User.getUser(player);
					if(user.getRole() != null) {
						user.getRole().onTick(player);
					}
				}
			}
			
		}, 20L, 20L); */
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("le plugin viens de s'eteindre");
	}
	
	public static void main(String[] args) {
		
		User user = User.getUser("Laetitia");
		System.out.println(user.getName());
		user.setRole(RoleType.SimpleVillageois.create());
		System.out.println(user.getRole().getName());

	}

	
	public static int random (int min, int max){
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
	
	
}
