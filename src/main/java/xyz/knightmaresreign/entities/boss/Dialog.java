package xyz.knightmaresreign.entities.boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.knightmaresreign.KnightmaresReign;

class MessageQueue {
	public int seconds;
	public String message;
	
	public MessageQueue(String message, int seconds) {
		this.message = message;
		this.seconds = seconds;
	}
}

public class Dialog {
	
	private String name;
	private Sound dialogSound;
	
	private Map<String, List<String>> messages;
	
	private List<MessageQueue> queued;
	private int queuedIndex;
	
	public Dialog(String name) {
		this.name = name;
		messages = new HashMap<String, List<String>>();
		queued = new ArrayList<MessageQueue>();
		queuedIndex = -1;
	}
	
	public void setMessagesId(String id, List<String> messages) {
		this.messages.put(id, messages);
	}
	
	public void setMessageForId(String id, String message) {
		if(!messages.containsKey(id))
			setMessagesId(id, new ArrayList<String>());
		messages.get(id).add(message);
	}
	
	public void sendRandomMessageById(Player player, String id) {
		List<String> messages = this.messages.get(id);
		if(messages == null) return;
		int index = KnightmaresReign.getInstance().getRandom(0, messages.size());
		String message = messages.get(index);
		
		sendMessage(player, message);
	}
	
	public void queue(String message, int seconds) {
		queued.add(new MessageQueue(message, seconds));
	}
	
	public void reset() {
		queued = new ArrayList<MessageQueue>();
		queuedIndex = -1;
	}
	
	public void start(Player player) {
		new BukkitRunnable() {
			MessageQueue mq = nextQueuedMessage();
			int secondsPassed = 0;
			@Override
			public void run() {
				if(secondsPassed == 0) {
					sendMessage(player, mq.message);
				}
				
				secondsPassed++;
				
				if(secondsPassed >= mq.seconds) {
					if(queuedIndex >= queued.size() - 1) {
						cancel();
						return;
					}
					mq = nextQueuedMessage();
					secondsPassed = 0;
				}
			}
		}.runTaskTimer(KnightmaresReign.getInstance(), 20L, 20L);
	}
	
	private MessageQueue nextQueuedMessage() {
		queuedIndex++;
		return queued.get(queuedIndex);
	}
	
	public void sendMessage(Player player, String message) {
		Random random = new Random();
		player.sendMessage(KnightmaresReign.getInstance().toComponent(name + "&r: " + message));
		player.playSound(player, dialogSound, 0.5f, 1f + ((random.nextFloat(1 - -1) + -1)));
	}
	
	public void setDialogSound(Sound dialogSound) {
		this.dialogSound = dialogSound;
	}
	
}
