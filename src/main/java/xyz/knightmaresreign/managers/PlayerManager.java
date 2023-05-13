package xyz.knightmaresreign.managers;

public class PlayerManager extends Manager<PlayerData> {
	public PlayerManager() {
		super("player", new PlayerData());
	}
}
