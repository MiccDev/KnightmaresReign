package xyz.knightmaresreign.events.quests;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import xyz.knightmaresreign.events.CustomEvent;
import xyz.knightmaresreign.quests.QuestManager;

public class Quests extends CustomEvent {

    public void onEvent(Event event, Player player) {
        //call this function so if needed later extra code can be added here
        QuestManager.event(event, player);
    }

    //Copy for all events

    //@EventHandler
    //public void onEvent(Event event) {
    //    onEvent(event, event.getPlayer()); //getPlayer or equivalent
    //}
}
