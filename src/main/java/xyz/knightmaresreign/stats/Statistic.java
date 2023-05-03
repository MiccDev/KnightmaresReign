package xyz.knightmaresreign.stats;

public class Statistic {
	
	public static Statistic HEALTH = new Statistic(0, "health", 50);
	public static Statistic MANA = new Statistic(1, "mana", 50);
	public static Statistic SPEED = new Statistic(2, "speed", 1);
	public static Statistic STRENGTH = new Statistic(3, "strength", 5);
	public static Statistic DEFENSE = new Statistic(4, "defense");
	
	public final int id;
	public final String key;
	public final double defaultValue;
	
	public Statistic(int id, String key) {
		this(id, key, 0);
	}
	
	public Statistic(int id, String key, double defaultValue) {
		this.id = id;
		this.key = key;
		this.defaultValue = defaultValue;
	}

}
