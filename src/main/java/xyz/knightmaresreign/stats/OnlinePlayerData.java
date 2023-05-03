package xyz.knightmaresreign.stats;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.managers.PlayerData;

public class OnlinePlayerData {

	private static HashMap<UUID, OnlinePlayerData> data = new HashMap<UUID, OnlinePlayerData>();

	public static void setPlayer(Player plr, OnlinePlayerData data) {
		OnlinePlayerData.data.put(plr.getUniqueId(), data);
	}

	public static OnlinePlayerData getPlayer(Player plr) {
		return OnlinePlayerData.data.get(plr.getUniqueId());
	}

	public static OnlinePlayerData addPlayer(Player plr) {
		if(getPlayer(plr) != null) {
			getPlayer(plr).cancelTick();
		}
		
		return new OnlinePlayerData(plr);
	}

	private Player plr;
	private PlayerData plrData;
	private double health;
	private double mana;
	private double strength;
	private double defense;
	private double speed;

	private boolean dead = false;
	
	private boolean canRegen = true;
	private boolean canManaRegen = true;
	
	private BukkitRunnable tick;

	public OnlinePlayerData(Player plr) {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		this.plr = plr;
		this.plrData = plugin.playerManager.getPlayerData(plr);
		this.health = Statistic.HEALTH.defaultValue;
		this.mana = Statistic.MANA.defaultValue;
		
		this.strength = Statistic.MANA.defaultValue;
		this.defense = Statistic.HEALTH.defaultValue;

		tick = new BukkitRunnable() {
			int ticks = 0;
			@Override
			public void run() {
				if(health < plrData.health && canRegen) {
					tryHeal();
				}
				
				if(mana < plrData.mana && canManaRegen) {
					tryRegenMana();
				}
				ticks++;
			}
			
			private void tryRegenMana() {
				if((ticks - 3) % 2 == 0) {
					addMana(5);
				}
			}
			
			private void tryHeal() {
				if((ticks - 3) % 2 == 0) {
					heal(1);
				}
			}
		};
		
		tick.runTaskTimer(plugin, 20L, 20L);
		OnlinePlayerData.data.put(this.plr.getUniqueId(), this);
	}

	public double setHealth(double health) {
		this.health = health;
		if (this.health <= 0 && !dead) {
			health = 0;
			plr.setHealth(0);
			dead = true;
		}
		return this.health;
	}

	public void revive() {
		health = plrData.health;
		mana = plrData.mana;
		dead = false;
	}

	public double heal(double amount) {
		this.health += amount;
		if (this.health > plrData.health) {
			this.health = plrData.health;
		}
		dead = false;
		return this.health;
	}

	public double damage(double amount) {
		double damageTaken = amount - defense;
		plr.sendMessage(KnightmaresReign.getInstance().toComponent("Damage Taken: " + damageTaken));
		
		this.health -= amount;
		canRegen = false;
		if (this.health <= 0 && !dead) {
			health = 0;
			dead = true;
			plr.setHealth(0);
		}
		
		if(!canRegen) {
			new BukkitRunnable() {
				@Override
				public void run() {
					canRegen = true;	
				}
			}.runTaskLater(KnightmaresReign.getInstance(), 20 * 3);
		}
		return this.health;
	}

	public double getHealth() {
		return this.health;
	}

	public double setMana(double mana) {
		this.mana = mana;
		return this.mana;
	}

	public double addMana(double amount) {
		this.mana += amount;
		if (this.mana > plrData.mana) {
			this.mana = plrData.mana;
		}
		return this.mana;
	}

	public double subtractMana(double amount) {
		this.mana -= amount;
		canManaRegen = false;
		if (this.mana <= 0) {
			this.mana = 0;
		}
		
		if(!canManaRegen) {
			new BukkitRunnable() {
				@Override
				public void run() {
					canManaRegen = true;	
				}
			}.runTaskLater(KnightmaresReign.getInstance(), 20 * 3);
		}
		return this.mana;
	}

	public double getMana() {
		return this.mana;
	}
	
	public void cancelTick() {
		tick.cancel();
	}

}
