package model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Achievement {
    private ObservableList<String> Achievements;

    public Achievement(int Id, String Name, int Points) {
    }


    public ObservableList<String> getAchievements() {
        return Achievements;
    }

    public void setAchievements(ObservableList<String> achievements) {
        Achievements = achievements;
    }
}
