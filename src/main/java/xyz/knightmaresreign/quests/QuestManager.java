package xyz.knightmaresreign.quests;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import xyz.knightmaresreign.entities.npc.NPC;

import java.util.*;

public class QuestManager {

    public static Quest TESTQUEST = new TestQuest();

    public static HashMap<UUID, Set<Quest>> OpenQuests = new HashMap<>();

    public static Set<Quest> getOpenQuests(Player player) {
        return OpenQuests.get(player.getUniqueId());
    }

    public static void setOpenQuest(Player player, Quest quest) {
        if(!OpenQuests.containsKey(player.getUniqueId())) {
            OpenQuests.put(player.getUniqueId(), new HashSet<>());
        }
        OpenQuests.get(player.getUniqueId()).add(quest);
        quest.setDefaultState(player);
    }

    public static boolean isOpenQuest(Player player, Quest quest) {
        return OpenQuests.get(player.getUniqueId()).contains(quest);
    }

    public static void openIfNotOpen(Player player, Quest quest) {
        if(!isOpenQuest(player, quest)) {
            setOpenQuest(player, quest);
        }
    }

    public static void npc(NPC npc, Player player) {
        for (Quest quest : getOpenQuests(player)) {
            quest.npc(npc, player);
        }
    }

    public static void event(Event event, Player player) {
        for (Quest quest : getOpenQuests(player)) {
            quest.event(event, player);
        }
    }
}
