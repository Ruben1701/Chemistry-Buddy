package model;

import javafx.collections.ObservableList;

public class AchievementOld {
    private ObservableList<String> Achievements;

    public AchievementOld(int Id, String Name, int Points) {
    }


    public ObservableList<String> getAchievements() {
        return Achievements;
    }

    public void setAchievements(ObservableList<String> achievements) {
        Achievements = achievements;
    }
}
