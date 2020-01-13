package service;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import serializer.Serializer;

import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceTest {

    private final ProfileService profileService = new ProfileService();

    @Test
    void getAllAchievements() {
        assertNotNull(profileService.getAllAchievements());
    }

    @Test
    void getUserAchievements() throws ParseException {
        Serializer serializer = new Serializer();
        serializer.Serialize("8", "/Users/ruben/Desktop/Big Idea/Chemistry-Buddy/src/main/java/serializer/user.ser");
        assertNotNull(profileService.getUserAchievements());
    }

    @Test
    void getAchievmentInfo() throws ParseException {
        Object in = "1";
        String ExpectedJsonString = "{\"Achievement_Points\":100,\"Achievement_Name\":\"Play a Game!\",\"Achievement_Id\":1}";
        assertEquals(profileService.getAchievementInfo(in).toString(), ExpectedJsonString);
    }

}