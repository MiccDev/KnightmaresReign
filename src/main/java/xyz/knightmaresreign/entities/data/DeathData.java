package xyz.knightmaresreign.entities.data;

public class DeathData extends EntityData {
	private int coins = 0;
	private double xp = 1;
	
	public DeathData() {
		this(0, 1);
	}
	
	public DeathData(int coins) {
		this(coins, 1);
	}
	
	public DeathData(int coins, double xp) {
		this.coins = coins;
		this.xp = xp;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public DeathData setCoins(int coins) {
		this.coins = coins;
		return this;
	}
	
	public double getXp() {
		return xp;
	}
	
	public DeathData setXp(double xp) {
		this.xp = xp;
		return this;
	}
	
}
