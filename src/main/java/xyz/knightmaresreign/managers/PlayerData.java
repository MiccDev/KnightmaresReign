package xyz.knightmaresreign.managers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.stats.Statistic;

public class PlayerData implements Serializable {
	private static final long serialVersionUID = -5212489006369374834L;
	
	public double health;
	public double mana;
	public double speed;
	public double strength;
	public double defense;
	
	public int coins;
	
	public int level;
	public double xp;
	
	public HashMap<String, Integer> npcInteractions;
	
	public PlayerData() {
		this.health = Statistic.HEALTH.defaultValue;
		this.mana = Statistic.MANA.defaultValue;
		this.speed = Statistic.SPEED.defaultValue;
		this.strength = Statistic.STRENGTH.defaultValue;
		this.defense = Statistic.DEFENSE.defaultValue;
		
		this.coins = 0;
		
		this.level = 0;
		this.xp = 0;
		
		this.npcInteractions = new HashMap<String, Integer>();
	}
	
	public void addCurrency(int amount) {
		coins += amount;
	}
	
	public void removeCurrency(int amount) {
		coins -= amount;
	}
	
	public void setCurrency(int amount) {
		coins = amount;
	}
	
	public int getCurrency() {
		return coins;
	}

	public boolean hasCurrency(int amount) {
		return getCurrency() >= amount;
	}
	
	public void interactedWith(String name) {
		npcInteractions.put(name, npcInteractions.get(name) + 1);
	}
	
	public void addXp(Player player, double amount) {
		this.xp += amount;
		
		double xpToNextLevel = getXpToNextLevel();
		if(xp >= xpToNextLevel) {
			levelUp(player);
		}
	}
	
	public void levelUp(Player player) {
		levelUp(player, 0);
	}
	
	public void levelUp(Player player, double remainder) {
		this.level++;
		this.xp = remainder;
		player.sendMessage(KnightmaresReign.getInstance().toComponent("&aYou just leveled up! &a&oYou are now level " + this.level));
	}
	
	public double getXpToNextLevel() {
		return ((level - 1) * 400) + 200;
	}
}
