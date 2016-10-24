package me.kazzababe.rpg.quest;

import me.kazzababe.rpg.Forged;
import me.kazzababe.rpg.player.ForgedPlayer;

import java.util.HashMap;

public class QuestManager {
    private static HashMap<Integer, Quest> allQuests = new HashMap<Integer, Quest>();

    public static void registerQuest(Class<? extends Quest> questClass) {
        try {
            Quest quest = questClass.getConstructor(ForgedPlayer.class).newInstance(new Object[] {null});
            allQuests.put(quest.getId(), quest);

            // Create a table for the quest
            Forged.getPlugin().getSQL().createQuestTable(quest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public QuestManager() {
        registerQuest(Quest001PigKiller.class);
    }
}
