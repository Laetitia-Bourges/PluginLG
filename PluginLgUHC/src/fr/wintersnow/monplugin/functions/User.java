package fr.wintersnow.monplugin.functions;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import fr.wintersnow.monplugin.Main;

public class User {
	
	private static int nbVote = 0; 
	private String name;
	private Role role = null;
	private boolean noFall = false;
	
	public static HashMap<String, User> users = new HashMap<String, User>();
	
	public static User getUser (String name) {
		if (users.containsKey(name.toLowerCase()))
			return users.get(name.toLowerCase());
		else 
			return new User(name);
	}
	
	public static User getUser (Player player) {
		if (users.containsKey(player.getName().toLowerCase()))
			return users.get(player.getName().toLowerCase());
		else 
			return new User(player);
	}
	
	public User(Player player) {
		this(player.getName());
	}
	
	public User(String name) {
		this.name = name;
		users.put(name.toLowerCase(), this);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public boolean isNoFall() {
		return noFall;
	}

	public void setNoFall(boolean noFall) {
		this.noFall = noFall;
	}
	
	public void start(Player player) {
		User user = User.getUser(player);
		user.setNoFall(true);
		for(PotionEffect pe : player.getActivePotionEffects()) 
			player.removePotionEffect(pe.getType());
		player.setMaxHealth(10*2d);
		player.setHealth(10*2d);
		player.setFallDistance(0F);
		player.setGameMode(GameMode.SURVIVAL);
		player.teleport(new Location(Bukkit.getWorlds().get(0), Main.random(-1000, 1000), 150, Main.random(-1000, 1000)));

	}

	public static int getNbVote() {
		return nbVote;
	}

	public static void setNbVote() {
		User.nbVote = User.nbVote + 1;
	}
	
}
