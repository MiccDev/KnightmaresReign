package xyz.knightmaresreign.quests;

import org.bukkit.entity.Player;
import xyz.knightmaresreign.KnightmaresReign;
import xyz.knightmaresreign.entities.npc.NPC;
import xyz.knightmaresreign.entities.npc.NPCManager;
import xyz.knightmaresreign.managers.PlayerData;

import java.util.HashMap;
import java.util.UUID;

public class TestQuest extends Quest{

    public void npc(NPC npc, Player player) {
       if (npc.equals(NPCManager.FORESTER)) forester(player);
       if (npc.equals(NPCManager.LEON)) leon(player);
       if (npc.equals(NPCManager.DEON)) deon(player);
       if (npc.equals(NPCManager.KADEN)) kaden(player);
    }

    private void forester(Player player) {
        QuestState state = getState(player);
        if (TestQuestState.NOTSTARTED.equals(state)) {
            player.sendMessage("You should talk to Leon, Deon and Kaden");
            player.sendMessage("Start with Leon");
            setState(player, TestQuestState.STAGE1);
        } else if (TestQuestState.STAGE1.equals(state)) {
            player.sendMessage("Talk to Leon");
        } else if (TestQuestState.STAGE2.equals(state)) {
            player.sendMessage("Talk to Deon");
        } else if (TestQuestState.STAGE3.equals(state)) {
            player.sendMessage("Talk to Kaden");
        } else if (TestQuestState.STAGE4.equals(state)) {
            player.sendMessage("Good you talked to them");
            player.sendMessage("Take This as a reward");
            PlayerData data = KnightmaresReign.getInstance().playerManager.get(player);
            data.addCurrency(10);
            setState(player, TestQuestState.ENDED);
        } else if (TestQuestState.ENDED.equals(state)) {
            player.sendMessage("I Introduced You to Leon, Deon and Kaden already");
        }
    }

    private void leon(Player player) {
        if(PlayerQuestStates.get(player.getUniqueId()).equals(TestQuestState.STAGE1)){
            player.sendMessage("I Do Armour");
            setState(player, TestQuestState.STAGE2);
        }
    }

    private void deon(Player player) {
        if(PlayerQuestStates.get(player.getUniqueId()).equals(TestQuestState.STAGE2)){
            player.sendMessage("I Do Weapons");
            setState(player, TestQuestState.STAGE3);
        }
    }

    private void kaden(Player player) {
        if(PlayerQuestStates.get(player.getUniqueId()).equals(TestQuestState.STAGE3)){
            player.sendMessage("I Do Stuff");
            setState(player, TestQuestState.STAGE4);
        }
    }

    public TestQuestState getStartingState() {
        return TestQuestState.NOTSTARTED;
    }

    public enum TestQuestState implements QuestState{
        NOTSTARTED, STAGE1, STAGE2, STAGE3, STAGE4, ENDED;
    }
}

