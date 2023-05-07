package xyz.knightmaresreign.events;

import org.bukkit.event.Listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.npc.NPCManager;
import xyz.knightmaresreign.events.entities.EntityDamage;
import xyz.knightmaresreign.events.entities.EntityDamageEntity;
import xyz.knightmaresreign.events.entities.EntityDeath;
import xyz.knightmaresreign.events.entities.PlayerInteractEntity;
import xyz.knightmaresreign.events.gui.MenuListener;
import xyz.knightmaresreign.events.player.PlayerMovement;
import xyz.knightmaresreign.events.player.PlayerRespawn;
import xyz.knightmaresreign.events.traffic.PlayerJoin;
import xyz.knightmaresreign.events.traffic.PlayerLeave;

public class CustomEvent implements Listener {

	public static void init(ProtocolManager manager) {
		new PlayerJoin();
		new PlayerLeave();
		new PlayerRespawn();
		new PlayerMovement();
		
		new EntityDamage();
		new EntityDeath();
		new EntityDamageEntity();

		new MenuListener();
		new PlayerInteractEntity();
		
		manager.addPacketListener(new PacketAdapter(KnightmaresReign.getInstance(), PacketType.Play.Client.USE_ENTITY) {
			@Override
			public void onPacketReceiving(PacketEvent event) {
				PacketContainer packet = event.getPacket();
				int entityID = packet.getIntegers().read(0);
				EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
				EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();
				
				if(hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
					NPCManager.getNPCs().values().forEach(npc -> {
						if(npc.getEntityID() == entityID) {
							npc.getDialog().sendRandomMessageById(event.getPlayer(), "welcome-messages");
							return;
						}
					});
				}
			}
		});
	}
	
	private KnightmaresReign plugin;
	
	public CustomEvent() {
		this.plugin = KnightmaresReign.getInstance();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public KnightmaresReign getPlugin() {
		return plugin;
	}
	
}
