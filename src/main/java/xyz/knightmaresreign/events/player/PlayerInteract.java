package xyz.knightmaresreign.events.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.items.CustomItem;
import xyz.knightmaresreign.items.data.ProjectileWeaponData;
import xyz.knightmaresreign.items.data.WeaponData;
import xyz.knightmaresreign.projectile.Projectile;
import xyz.knightmaresreign.stats.OnlinePlayerData;

public class PlayerInteract extends CustomEvent {

	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if(!event.getAction().isRightClick()) return;
		Player player = event.getPlayer();
		ItemStack itemStack = player.getInventory().getItemInMainHand();
		if(itemStack == null) return;
		if(!CustomItem.isCustomItem(itemStack)) return;
		
		CustomItem item = CustomItem.toItem(itemStack);
		if(item.getDataOfType(ProjectileWeaponData.class) == null) return;
		ProjectileWeaponData data = item.getDataOfType(ProjectileWeaponData.class);
		
		OnlinePlayerData playerData = OnlinePlayerData.getPlayer(player);
		double manaCost = getMana(itemStack);
		if(playerData.getMana() >= manaCost) {
			playerData.subtractMana(manaCost);
		} else {
			return;
		}
		
		Location location = player.getLocation();
		new Projectile(player, location.getDirection(), location.add(0, 1.6, 0), 1, data.getProjectileData());
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
