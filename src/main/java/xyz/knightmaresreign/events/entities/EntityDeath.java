package xyz.knightmaresreign.events.entities;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;

import xyz.knightmaresreign.entities.CustomEntity;
import xyz.knightmaresreign.entities.data.DeathData;
import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.managers.PlayerData;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class EntityDeath extends CustomEvent {

	public EntityDeath() {
		super();
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(EntityDeathEvent event) {
		if(!(event.getEntity() instanceof LivingEntity)) return;
		
		LivingEntity entity = (LivingEntity) event.getEntity();
		if(entity instanceof Player) {
			onPlayerDeath(event, (Player) entity);
			return;
		}
		
		if(!CustomEntity.isCustomEntity(entity)) return;
		CustomEntity e = CustomEntity.toEntity(entity);
		
		event.setDroppedExp(0);
		event.getDrops().clear();
		
		if(e.getDataOfType(DeathData.class) != null) {
			DeathData data = e.getDataOfType(DeathData.class);
			LivingEntity killer = EntityDamage.lastEntityDamageEntity.get(entity.getUniqueId());
			if(killer == null) return;
			if(!(killer instanceof Player)) return;
			Player player = (Player) killer;
			
			PlayerData plrData = getPlugin().playerManager.get(player);
			plrData.addXp(player, data.getXp());
			plrData.addCurrency(data.getCoins());
			getPlugin().playerManager.set(player, plrData);
			
			killer.sendMessage(getPlugin().toComponent(" + &6" + data.getCoins() + "&r coins."));
			killer.sendMessage(getPlugin().toComponent(" + &a" + data.getXp() + "&r XP."));
		}
	}
	
	public void onPlayerDeath(EntityDeathEvent event, Player player) {
		OnlinePlayerData.addPlayer(player);
	}
	
}
