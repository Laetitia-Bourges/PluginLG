package fr.wintersnow.monplugin.functions;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.wintersnow.monplugin.Main;
import fr.wintersnow.monplugin.bukkitRennable.GetRole;
import fr.wintersnow.monplugin.bukkitRennable.SaveOrNot;
import fr.wintersnow.monplugin.enums.RoleType;

public abstract class Role {
	
	private Player couple = null;
	
	public abstract String getName();
	public abstract RoleType getType();
	public abstract boolean isFriendly();
	
	public Player getCouple() {
		return couple;
	}
	
	public void setCouple(Player couple) {
		this.couple = couple;
	}
	
	public void onDeath(PlayerDeathEvent e) {
		e.setDeathMessage("");
		Player p = e.getEntity();
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(new Location(Bukkit.getWorlds().get(0), Bukkit.getWorlds().get(0).getSpawnLocation().getBlockX(),
				                Bukkit.getWorlds().get(0).getSpawnLocation().getBlockY(),
				                Bukkit.getWorlds().get(0).getSpawnLocation().getBlockZ()));
		
		Player killer = e.getEntity().getKiller();
		User uKiller = User.getUser(killer);
		if(!uKiller.getRole().isFriendly())
		{
			killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*60, 0, false, false));
			killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*120, 0, false, false));
		}
		
		Player sorciere = null;
		for (Player player : Bukkit.getOnlinePlayers()) {
			User u = User.getUser(player);
			if (u.getRole().getName() == "Sorciere") {
				sorciere = player;
			}
		sorciere.sendMessage("§5" + p + " est mort, vous pouvez le/la réssuciter avec /save si vous n'avez pas encore utilisé votre pouvoir de la partie." );
		}

		SaveOrNot son = new SaveOrNot();
		son.setLastDead(p);
		BukkitRunnable task = new SaveOrNot();
		task.runTaskLater(Main.instance, 200);
		
	}
	
	public void onDeathCouple(Player player, Player lover) {
		User user = User.getUser(player);
		Bukkit.broadcastMessage("§3" + user.getName() + " était fou amoureux de "+lover.getName()+", son chagrin le pouuse à suivre son âme-soeur dans sa tombe. Il était : " + user.getRole().getName());
		player.setGameMode(GameMode.SPECTATOR);
		player.sendMessage("§bVous êtes mort :P");
		setCouple(null);
	}
	
	public void onGive(Player p)  {
		p.sendMessage( (isFriendly() ? "§9" : "§c") + "Vous êtes " +  getName());
	}
	
	public void onRespawn(Player p) {
		p.sendMessage("§a Vous avez été réssuciter par la sorcière.");
		p.setGameMode(GameMode.SURVIVAL);
		User u = User.getUser(p);
		u.setNoFall(true);
		p.teleport(new Location(Bukkit.getWorlds().get(0), Main.random(-1000, 1000), 150, Main.random(-1000, 1000)));
		p.setHealth(p.getMaxHealth());
	}
	
	public void give(Player p, ItemStack... items) {
		for(ItemStack item : items) {
			if(p.getInventory().firstEmpty() != -1) {
				p.getLocation().getWorld().dropItem(p.getLocation(), item);
			}else {
				p.getInventory().addItem(item);
			}
		}
	}
	
	public void onTick(Player player) {
		if(getCouple() != null) {
			player.setCompassTarget(getCouple().getLocation());
		}
	}
	
	
}
