package xyz.knightmaresreign.events.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.items.data.WeaponData;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class EntityDamage extends CustomEvent {

	public static HashMap<UUID, LivingEntity> lastEntityDamageEntity = new HashMap<UUID, LivingEntity>();
	
	public EntityDamage() {
		super();
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			onPlayerDamage(event, (Player) event.getEntity());
		} else if(event.getEntity() instanceof LivingEntity) {
			onLivingEntityDamage(event, (LivingEntity) event.getEntity());
		}
	}
	
	public void onPlayerDamage(EntityDamageEvent event, Player player) {
		OnlinePlayerData plrData = OnlinePlayerData.getPlayer(player);
		if(plrData == null) 
			plrData = OnlinePlayerData.addPlayer(player);
		
		double damage = event.getDamage();
		if(player.getFallDistance() > 3) {
			damage = 3 * Math.round(player.getFallDistance()) - 9;
		}
		
		event.setDamage(0);
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		plrData.damage(damage);
	}
	
	public void onLivingEntityDamage(EntityDamageEvent event, LivingEntity damagee) {
		if(event.getCause().equals(DamageCause.ENTITY_ATTACK)) {
			onEntityDamageEntity(event, damagee);
			return;
		}
	}
	
	public void onEntityDamageEntity(EntityDamageEvent event, LivingEntity damagee) {
		LivingEntity damager = (LivingEntity) ((EntityDamageByEntityEvent) event).getDamager();
		
		ItemStack heldItem = damager.getEquipment().getItemInMainHand();
		
		if(damager instanceof Player) {
			OnlinePlayerData data = OnlinePlayerData.getPlayer((Player)damager);
			double manaCost = getMana(heldItem);
			if(data.getMana() >= manaCost) {
				data.subtractMana(manaCost);
			} else {
				return;
			}
		}
		
		double damage = 1;
		if(heldItem != null) { 
			damage = getDamage(heldItem);
			damage = damage > 0 ? damage : event.getDamage();
		}
		event.setDamage(damage);
		lastEntityDamageEntity.put(damagee.getUniqueId(), damager);
	}
	
	public double getDamage(ItemStack heldItem) {
		if(heldItem == null) return 1;
		WeaponData data = getWeaponData(heldItem);
		if(data != null) return data.getDamage();
		
		if(heldItem.getItemMeta() == null) return 1;
		Collection<AttributeModifier> attributes = heldItem.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
		if(attributes == null) return 1;
		if(attributes.isEmpty()) return 1;
		AttributeModifier attackDamage = (AttributeModifier) attributes.toArray()[0];
		return attackDamage.getAmount() > 0 ? attackDamage.getAmount() : 1;
	}
	
	public double getMana(ItemStack heldItem) {
		if(heldItem == null) return 0;
		WeaponData data = getWeaponData(heldItem);
		return data != null ? data.getMana() : 0;
	}
	
	public WeaponData getWeaponData(ItemStack item) {
		if(!CustomItem.isCustomItem(item)) return null;
		CustomItem customItem = CustomItem.toItem(item);
		if(customItem.getDataOfType(WeaponData.class) == null) return null;
		return customItem.getDataOfType(WeaponData.class);
	}
	
}
