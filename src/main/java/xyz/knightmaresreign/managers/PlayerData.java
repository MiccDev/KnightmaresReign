package xyz.knightmaresreign.managers;

import java.io.Serializable;

import xyz.knightmaresreign.stats.Statistic;

public class PlayerData implements Serializable {
	private static final long serialVersionUID = -5212489006369374834L;
	
	public double health;
	public double mana;
	public double speed;
	public double strength;
	public double defense;
	
	public PlayerData() {
		this.health = Statistic.HEALTH.defaultValue;
		this.mana = Statistic.MANA.defaultValue;
		this.speed = Statistic.SPEED.defaultValue;
		this.strength = Statistic.STRENGTH.defaultValue;
		this.defense = Statistic.DEFENSE.defaultValue;
	}
}
