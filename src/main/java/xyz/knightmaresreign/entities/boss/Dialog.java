package xyz.knightmaresreign.entities.boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class Dialog<T extends Enum<T>> {

	private Boss<T> boss;
	
	private Sound dialogSound;
	
	private Map<String, List<String>> messages;
	
	private List<MessageQueue> queued;
	private int queuedIndex;
	
	public Dialog(Boss<T> boss) {
		this.boss = boss;
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
	
	public void sendRandomMessageById(String id) {
		List<String> messages = this.messages.get(id);
		if(messages == null) return;
		int index = KnightmaresReign.getInstance().getRandom(0, messages.size() - 1);
		String message = messages.get(index);
		
		sendMessage(message);
	}
	
	public void queue(String message, int seconds) {
		queued.add(new MessageQueue(message, seconds));
	}
	
	public void reset() {
		queued = new ArrayList<MessageQueue>();
		queuedIndex = -1;
	}
	
	public void start() {
		new BukkitRunnable() {
			MessageQueue mq = nextQueuedMessage();
			int secondsPassed = 0;
			@Override
			public void run() {
				if(secondsPassed == 0) {
					sendMessage(mq.message);
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
	
	public void sendMessage(String message) {
		List<Player> playersInRoom = boss.getPlayersInBossRoom();
		
		for(Player player : playersInRoom) {
			player.sendMessage(boss.getDisplayName().append(KnightmaresReign.getInstance().toComponent(": " + message)));
			player.playSound(boss.getLocation(), dialogSound, 0.5f, 1f);
		}
	}
	
	public void setDialogSound(Sound dialogSound) {
		this.dialogSound = dialogSound;
	}
	
}
