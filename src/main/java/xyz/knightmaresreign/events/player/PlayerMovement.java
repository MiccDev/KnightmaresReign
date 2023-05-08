package xyz.knightmaresreign.events.player;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import net.minecraft.network.protocol.game.PacketPlayOutEntity;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import xyz.knightmaresreign.entities.npc.NPCManager;
import xyz.knightmaresreign.events.CustomEvent;

public class PlayerMovement extends CustomEvent {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		NPCManager.getNPCs().values().forEach(npc -> {
			EntityPlayer human = npc.getEntityPlayer();
			
			Location location = npc.getLocation();
			if(player.getLocation().distance(location) > 7) return;
			location.setDirection(player.getLocation().subtract(location).toVector());
			float yaw = location.getYaw();
			float pitch = location.getPitch();
			
			PlayerConnection ps = ((CraftPlayer) player).getHandle().b;
			ps.a(new PacketPlayOutEntityHeadRotation(human, (byte) ((yaw % 360) * 256 / 360)));
			ps.a(new PacketPlayOutEntity.PacketPlayOutEntityLook(human.getBukkitEntity().getEntityId(), (byte) ((yaw % 360) * 256 / 360), (byte) ((pitch % 360) * 256 / 360), false));
		});
	}
	
}
