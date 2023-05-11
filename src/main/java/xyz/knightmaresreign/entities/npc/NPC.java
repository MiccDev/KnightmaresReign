package xyz.knightmaresreign.entities.npc;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.boss.Dialog;
import xyz.knightmaresreign.utils.SkinUtils;

public class NPC {
	
	public interface NPCRunnable {
		public void run(NPC npc, Player player);
	}

	private Location location;
	private String name;
	private String texture;
	private String signature;
	private GameProfile gameProfile;
	private EntityPlayer entityPlayer;
	
	private Dialog dialog;
	
	private NPCRunnable onClick;
	
	public NPC(String name) {
		this(name, "Steve");
	}
	
	public NPC(String name, String skinName) {
		this.name = name;
		
		String[] skin = SkinUtils.getSkin(skinName);
		this.texture = skin[0];
		this.signature = skin[1];
		
		dialog = new Dialog(name);
		NPCManager.addNPC(this);
	}
	
	public void hide(Player player) {
		PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().b;
		if(entityPlayer.getBukkitEntity() == null) return;
		playerConnection.a(new PacketPlayOutEntityDestroy(entityPlayer.getBukkitEntity().getEntityId()));
	}
	
	public void spawn(Location location) {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();
		
		gameProfile = new GameProfile(UUID.randomUUID(), plugin.toColour(name));
		gameProfile.getProperties().removeAll("textures");
		gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
		
		entityPlayer = new EntityPlayer(server, worldServer, gameProfile);
		// Sets location.
		entityPlayer.e(location.getX(), location.getY(), location.getZ());
//		entityPlayer.a(location.getYaw(), location.getPitch());
	}
	
	public void show(Player player) {
		PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().b;
		
		// Send packet of ADD_PLAYER type.
		playerConnection.a(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.a.a, entityPlayer));
		playerConnection.a(new PacketPlayOutNamedEntitySpawn(entityPlayer));
	}
	
	public NPC setClick(NPCRunnable click) {
		this.onClick = click;
		return this;
	}
	
	public NPCRunnable getClick() {
		return onClick;
	}
	
	public NPC setMessagesId(String id, List<String> messages) {
		dialog.setMessagesId(id, messages);
		return this;
	}
	
	public NPC setMessageForId(String id, String message) {
		dialog.setMessageForId(id, message);
		return this;
	}
	
	public int getEntityID() {
		return entityPlayer == null ? 0 : entityPlayer.af();
	}

	public Dialog getDialog() {
		return dialog;
	}
	
	public Location getLocation() {
		return location;
	}

	public NPC setLocation(Location location) {
		this.location = location;
		return this;
	}

	public String getName() {
		return name;
	}

	public NPC setName(String name) {
		this.name = name;
		return this;
	}

	public String getTexture() {
		return texture;
	}

	public NPC setTexture(String texture) {
		this.texture = texture;
		return this;
	}
	
	public String getSignature() {
		return signature;
	}

	public NPC setSignature(String signature) {
		this.signature = signature;
		return this;
	}

	public GameProfile getGameProfile() {
		return gameProfile;
	}

	public NPC setGameProfile(GameProfile gameProfile) {
		this.gameProfile = gameProfile;
		return this;
	}

	public EntityPlayer getEntityPlayer() {
		return entityPlayer;
	}

	public NPC setEntityPlayer(EntityPlayer entityPlayer) {
		this.entityPlayer = entityPlayer;
		return this;
	}
	
}
