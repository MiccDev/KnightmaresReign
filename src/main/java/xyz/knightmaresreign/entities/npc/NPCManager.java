package xyz.knightmaresreign.entities.npc;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NPCManager {

	private static HashMap<String, NPC> npcs = new HashMap<String, NPC>();

	public static void addNPC(NPC npc) {
		npcs.put(npc.getName(), npc);
	}

	public static NPC getNPC(String name) {
		return npcs.get(name);
	}
	
	public static HashMap<String, NPC> getNPCs() {
		return npcs;
	}

	public static NPC LEON = new NPC("&1Leon")
			.setLocation(new Location(Bukkit.getWorld("openworld"), 71.5, 54, 40.5))
			.setTexture("ewogICJ0aW1lc3RhbXAiIDogMTY2MDYxMjQwNjcyNiwKICAicHJvZmlsZUlkIiA6ICIzZmM3ZmRmOTM5NjM0YzQxOTExOTliYTNmN2NjM2ZlZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJZZWxlaGEiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4OGZkOWQyMmJlYjVlNzEzYWYzOTUwNjcyNmYzOWQ2MTFkNWQ3NzliZjhjMDIzOTQ4NTRlODUyMzM1ZDZjIgogICAgfQogIH0KfQ==")
			.setSignature("NBa752Q6azx7lhdtaNWIthUierSMviaRCERqCoGvmHWIQVKcPvqXxMddsJykDnJf6P2sUKprBUiAMRF+QqabknxiGQqUlU3eFfmLaP9YxBH9/Y1yi41c6WGkogZJLooul50AySl1G8YyNSOIk3a3BfK5Cik5QX9RlMn3RDd3Ji+68k26xKGLdhmchww0QGdHCsU4KeT8NlI370kpNDykvwhp12bXSioCUidG73zgjCeYbvxPyupx9lh4H1p5Wu8ZLJjCnek08mGrLToGPrbqrab2ZDS/4mfvcun+tDiJI8h4YSzJmeHifLSCaotPo12idtcs0UcgcyKCRehTmwwsQT9H6PGtWi6gnNV6RmQEfK3IfGLJno9arbFW3b+6PInLVMXIJc50W6abxSXskLE+h8AZqIyoL6x4OCJC4H7qPpORXhPdPiGaw7Fd9U2pDXJYcLpduFAmtls/AKKYIKnv7VZ5UJw9N0glAszS6Sr3PSssKAPA+TFoNeukeARPikAl0+detXBtc6uzvftUiFDC4dabSGIQmVqWSymN4cAhiuApQPFRyuZ+84BDifQrXkRAWeH5BUkb4YHv2LQAD2u8GaFxdAIMgSJQwAQa+HD9UOebIRQ/L6KLUTLFAsDp3QxjoHdZpkU5d5aU4/x9L4Z9n2cFz8WePcGh2f8rxpfhzrA=");
	public static NPC DEON = new NPC("&6Deon")
			.setLocation(new Location(Bukkit.getWorld("openworld"), 71.5, 54, 36.5))
			.setTexture("ewogICJ0aW1lc3RhbXAiIDogMTY2MDYxMTY0NDg4MSwKICAicHJvZmlsZUlkIiA6ICI5ZWEyMTQ0NGFiNjI0MWZkYjg5YjE2NDFhNDg2MGZiZiIsCiAgInByb2ZpbGVOYW1lIiA6ICI3QUJDSE9VTiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZGNjZGExNDNkYzk4NzQxOWM0MGEyMDg3NWVmYjMyNTM1NzkzYWI3YjcxZjkwMThjYWNkYjdlYWMwNjU2MTJlIgogICAgfQogIH0KfQ==")
			.setSignature("KB8xLWftTwFd3RicTrtIatD6/ZF69koTH0gsxtLUv2Ng3QkjPAbWOF0EGfVgN2JZsc7Kzd7kG+gMt/cdGxvW2a+b78SR2HOifvXHFisAz0coP8x1eYtN17rvR6qKP28K9ghXfr4C+MjdG7lnyAsbBbA99hzYKmaCOdZrYKY1XDL2ZSSytNmPRv01H33tllf2R8GRu9RBxlsKRCed0b3JYNkDUogPNn1JwIiF5xPcpDY3JQIwM0FvdMXhbChB5BtAl9RVcKAfhfLANVp8vXKaz/T7ips3Mlw62Op6IQJRBIH9g/mm30yJqfQrRC2VOXdQ7vEhIINHRfJaPiPipzhuY8uAAGHksY1IrE4TCr8IqfGxeJZ60mriA1dy1llpWuvxhlJsbsUJPoBKASih4CzE0g1E4fhJbAg1Pm7gh2CJwxgA+ljXbIK5yygPS2zdgNRaTPgq9KyV9SXwki3u8eETlSeMEgz0ptX20Us+Kg09CdRqPtM5TXCYaxELX+Y4N6mnmCYHsYgFkGnkZ1jBBwtbWlze02CyXR6dPHDQnKQ1DV+FyY7kQnfDVbPTneA+7xd1x2GxCykEz7mJ1Z77Bt4ZSiCex5am9OMGJumOEHM1Q9PgiL7DQ6/UwB881zxbAJWnyeRxsBNP9tI7u6IV2VZQWXFTtXdcBrle96O8Zm+yvws=")
			.setMessagesId("welcome-messages", Arrays.asList(
					"Hello there, you need some tools? You've come to the right place!",
					"Back for some more weapons, good idea, no need for armour when you have offense!",
					"Ready for the best prices in the neighbourhood."
			));
//	public static NPC GEO = new NPC("Geo")
//			.setLocation(new Location(Bukkit.getWorld("openworld"), 61, 54, 31))
//			.setTexture("ewogICJ0aW1lc3RhbXAiIDogMTY4MzQyMzk0NDAxNiwKICAicHJvZmlsZUlkIiA6ICI3YzI2YTAxY2U4NjU0NDkzOTA3NzA2OGQxZTA5ZjE5MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJodG93ZXI4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZkOGFlMmVjZjMwMTljOTMxZDI0OTY4ZDkwOGU3NWM1ZDI4YzA2ZWY1NmJlYzkxNWJhMmQ0NWI5NjA2MWE5ZTUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==")
//			.setSignature("FpfZLlTcWVI8EJ51YlxUOg1+l/OFSP0qfFMcRAQ+m4bqddNRb/LvWkwLH5oHjJdKIlDC9gsqsx7KmZ493067x3k28iqKQ9yWchMEsuiKE+iSrcpTQdBuYAwgN8Tpqn+E6MVELWZSIBiaR9MMWMczY55PBhYMeNnUQErRNpHhij63FpzLKR9eKWwkhEeXeQPfv3gYOW7aISgsIDdQyTBk/qlzY/dljd/4cYsBJYIExkRyXObN/vCLLJoOQK4o6Qzdg0jEPyGRrOdrCwVAwfCVkHcv+JCczYAX772HaqP/QyS3ZEF6gpiI10q1UtSkJyQyKygwLPXtG0OGiTiT46Yk9KceJSDW2M5y2VNa+d7kc3rrX/YBAckOmUGfQMOPX4RjEj4/3trAmTdFdBeuRC/lAKeGsgFCgjRMQl3QP/vAhpr/pg+cBMOrR+ENipyTAX13Xlmv8RgtK5OmBxaPRa7f1JivLBN/M/9nfWqdGGJWrdvj4Eqcy+3qmNcZU+9uTZ/J1inNIM4VVKefo+UJRFyKud/m1emugkT9dfcDCJIrNldQefv1GHBuqebQzuJn3xVJRdl2v0iCN76cfko9Bo0qJC9afJubQ8jvrWSL6xHAyZvGdV8tzE/SKUwNAiETpj3y1L1bcuF3kIkH7paIscOq/1Fm4uAMFCU/DZReiLRbGyA=");

	public NPCManager() {
	}

	public void hideAllNPCs(Player player) {
		npcs.forEach((String name, NPC npc) -> {
			hideNPC(npc, player);
		});
	}

	public void hideNPCToAll(NPC npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			hideNPC(npc, player);
		}
	}

	public void hideNPC(NPC npc, Player player) {
		npc.hide(player);
	}

	public void spawnAllNPCs() {
		npcs.forEach((String name, NPC npc) -> {
			if (npc.getLocation() == null)
				return;
			npc.spawn(npc.getLocation());
		});
	}

	public void showAllNPCs(Player player) {
		npcs.forEach((String name, NPC npc) -> {
			showNPC(npc, player);
		});
	}

	public void showNPC(NPC npc, Player player) {
		npc.show(player);
	}

	public void showNPCToAll(NPC npc) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			showNPC(npc, player);
		}
	}

}
