package xyz.knightmaresreign.stats;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.items.data.DefenseData;
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
	
	public static double getDefense(Player player) {
		EntityEquipment equipment = player.getEquipment();
		ItemStack helmetStack = equipment.getHelmet();
		ItemStack chestplateStack = equipment.getChestplate();
		ItemStack leggingsStack = equipment.getLeggings();
		ItemStack bootsStack = equipment.getBoots();
		
		CustomItem helmet = null;
		CustomItem chesplate = null;
		CustomItem leggings = null;
		CustomItem boots = null;
		if(CustomItem.isCustomItem(helmetStack)) helmet = CustomItem.toItem(helmetStack);
		if(CustomItem.isCustomItem(chestplateStack)) chesplate = CustomItem.toItem(chestplateStack);
		if(CustomItem.isCustomItem(leggingsStack)) leggings = CustomItem.toItem(leggingsStack);
		if(CustomItem.isCustomItem(bootsStack)) boots = CustomItem.toItem(bootsStack);
		
		double defense = 0;
		if(helmet != null && helmet.getDataOfType(DefenseData.class) != null) {
			defense += helmet.getDataOfType(DefenseData.class).getDefense();
		}
		if(chesplate != null && chesplate.getDataOfType(DefenseData.class) != null) {
			defense += chesplate.getDataOfType(DefenseData.class).getDefense();
		}
		if(leggings != null && leggings.getDataOfType(DefenseData.class) != null) {
			defense += leggings.getDataOfType(DefenseData.class).getDefense();
		}
		if(boots != null && boots.getDataOfType(DefenseData.class) != null) {
			defense += boots.getDataOfType(DefenseData.class).getDefense();
		}
		
		return defense;
	}

	private Player plr;
	private PlayerData plrData;
	private double health;
	private double mana;
	@SuppressWarnings("unused")
	private double strength;
	private double defense;
	@SuppressWarnings("unused")
	private double speed;

	private boolean dead = false;
	
	private boolean canRegen = true;
	private boolean canManaRegen = true;
	
	private BukkitRunnable tick;

	public OnlinePlayerData(Player plr) {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		this.plr = plr;
		this.plrData = plugin.playerManager.get(plr);
		this.health = Statistic.HEALTH.defaultValue;
		this.mana = Statistic.MANA.defaultValue;
		
		this.strength = Statistic.MANA.defaultValue;
		this.defense = OnlinePlayerData.getDefense(plr);

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
					addMana(3);
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
		double damage = amount * amount / (amount + defense) * 2.5;
		
		this.health -= damage;
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
	
	public double setDefense(double defense) {
		this.defense = defense;
		return this.defense;
	}
	
	public double getDefense() {
		return this.defense;
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
