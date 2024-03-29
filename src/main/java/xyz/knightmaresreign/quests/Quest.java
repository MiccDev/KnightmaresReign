package xyz.knightmaresreign.quests;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import xyz.knightmaresreign.entities.npc.NPC;

import java.util.HashMap;
import java.util.UUID;

public class Quest {

    HashMap<UUID, QuestState> PlayerQuestStates = new HashMap<>();

    public void setState(Player player, QuestState state) {
        PlayerQuestStates.put(player.getUniqueId(), state);
    }

    public QuestState getState(Player player) {
        if (PlayerQuestStates.containsKey(player.getUniqueId())) {
            return PlayerQuestStates.get(player.getUniqueId());
        }
        return null;
    }

    public void setDefaultState(Player player) {
        if(!PlayerQuestStates.containsKey(player.getUniqueId())) {
            PlayerQuestStates.put(player.getUniqueId(), getStartingState());
        }
    }


    public TestQuest.TestQuestState getStartingState() {return null;}
    public void npc(NPC npc, Player player) {}
    public void event(Event event, Player player) {}
}
