package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import websocket.EventClient;

import javax.websocket.Session;

import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {

    private static Session session;
    private static final EventClient eventClient = new EventClient();
    private static final QuizService quizService = new QuizService();

    @BeforeEach
     void before(){
        session = quizService.startQuiz(eventClient);
    }

    @Test
    void startQuiz() {
        assertNotNull(quizService.startQuiz(eventClient));
    }

    @Test
    void sendAnswer() {
        int x;
        try{
            x = 1;
            quizService.sendAnswer(eventClient, "h", session);
            eventClient.closeConnection(session);
        }
        catch (Exception e){
            x = 2;
        }
        assertEquals(1, x);
    }

    @Test
    void exitQuiz() {
        int x;
        try{
            x = 1;
            quizService.exitQuiz(eventClient, session);
        }
        catch (Exception e){
            x = 2;
        }
        assertEquals(1, x);
    }
}