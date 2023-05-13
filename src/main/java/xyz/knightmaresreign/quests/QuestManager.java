package xyz.knightmaresreign.quests;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class QuestManager {

    public static Quest TESTQUEST = new TestQuest();

    public static HashMap<UUID, Quest> OpenQuests = new HashMap<>();

    public static Quest getOpenQuest(Player player) {
        return OpenQuests.get(player.getUniqueId());
    }

    public static void setOpenQuest(Player player, Quest quest) {
        OpenQuests.put(player.getUniqueId(), quest);
        OpenQuests.get(player.getUniqueId()).setState(player, OpenQuests.get(player.getUniqueId()).getStartingState());
    }
}
