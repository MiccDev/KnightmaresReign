package xyz.knightmaresreign.events.entities;

import java.util.Collection;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.items.data.WeaponData;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class EntityDamageEntity extends CustomEvent {

	public EntityDamageEntity() {
		super();
	}

	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof LivingEntity && event.getEntity() instanceof LivingEntity) {
			LivingEntity damager = (LivingEntity) event.getDamager();
			LivingEntity damagee = (LivingEntity) event.getEntity();
			
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
				event.setDamage(0);
			}
			
			damagee.damage(damage);
		}
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
