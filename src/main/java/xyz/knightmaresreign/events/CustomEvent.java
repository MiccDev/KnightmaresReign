package xyz.knightmaresreign.events;

import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;

import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.npc.NPCManager;
import xyz.knightmaresreign.events.entities.EntityDamage;
import xyz.knightmaresreign.events.entities.EntityDeath;
import xyz.knightmaresreign.events.entities.PlayerInteractEntity;
import xyz.knightmaresreign.events.gui.MenuListener;
import xyz.knightmaresreign.events.player.PlayerEquipArmour;
import xyz.knightmaresreign.events.player.PlayerInteract;
import xyz.knightmaresreign.events.player.PlayerMovement;
import xyz.knightmaresreign.events.player.PlayerRespawn;
import xyz.knightmaresreign.events.player.PlayerUseProjectileWeapon;
import xyz.knightmaresreign.events.quests.Quests;
import xyz.knightmaresreign.events.traffic.PlayerJoin;
import xyz.knightmaresreign.events.traffic.PlayerLeave;

public class CustomEvent implements Listener {

	public static void init(ProtocolManager manager) {
		manager.addPacketListener(new PacketAdapter(KnightmaresReign.getInstance(), PacketType.Play.Client.USE_ENTITY) {
			@Override
			public void onPacketReceiving(PacketEvent event) {
				PacketContainer packet = event.getPacket();
				int entityID = packet.getIntegers().read(0);
				EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
				EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();
				
				if(hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
					NPCManager.getNPCs().values().forEach(npc -> {
						npc.setClick(npc.getClick());
						if(npc.getEntityID() == entityID) {
							new BukkitRunnable() {
								@Override
								public void run() {
									npc.getClick().run(npc, event.getPlayer());
								}
							}.runTask(plugin);
							return;
						}
					});
				}
			}
		});
		
		new PlayerJoin();
		new PlayerLeave();
		new PlayerRespawn();
		new PlayerMovement();
		new PlayerInteract();
		new PlayerEquipArmour();
		
		new EntityDamage();
		new EntityDeath();

		new MenuListener();
		new PlayerInteractEntity();

		new PlayerUseProjectileWeapon();
		new Quests();
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
