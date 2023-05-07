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

import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.boss.Dialog;
import xyz.knightmaresreign.utils.SkinUtils;

public class NPC {

	private Location location;
	private String name;
	private String texture;
	private String signature;
	private GameProfile gameProfile;
	private ServerPlayer entityPlayer;
	
	private Dialog dialog;
	
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
		ServerGamePacketListenerImpl playerConnection = ((CraftPlayer) player).getHandle().connection;
		if(entityPlayer.getBukkitEntity() == null) return;
		playerConnection.send(new ClientboundRemoveEntitiesPacket(entityPlayer.getBukkitEntity().getEntityId()));
	}
	
	public void spawn(Location location) {
		KnightmaresReign plugin = KnightmaresReign.getInstance();
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		ServerLevel worldServer = ((CraftWorld) location.getWorld()).getHandle();
		
		gameProfile = new GameProfile(UUID.randomUUID(), plugin.toColour(name));
		gameProfile.getProperties().removeAll("textures");
		gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
		
		entityPlayer = new ServerPlayer(server, worldServer, gameProfile);
		entityPlayer.setPos(location.getX(), location.getY(), location.getZ());
//		entityPlayer.a(location.getYaw(), location.getPitch());
	}
	
	public void show(Player player) {
		ServerGamePacketListenerImpl playerConnection = ((CraftPlayer) player).getHandle().connection;
		
		// Send packet of ADD_PLAYER type.
		playerConnection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, entityPlayer));
		playerConnection.send(new ClientboundAddPlayerPacket(entityPlayer));
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
		return entityPlayer == null ? 0 : entityPlayer.getId();
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

	public ServerPlayer getEntityPlayer() {
		return entityPlayer;
	}

	public NPC setEntityPlayer(ServerPlayer entityPlayer) {
		this.entityPlayer = entityPlayer;
		return this;
	}
	
}
