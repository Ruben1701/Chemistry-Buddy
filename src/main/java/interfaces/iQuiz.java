package interfaces;

import websocket.EventClient;

import javax.websocket.Session;

public interface iQuiz {
    Session startQuiz(EventClient eventClient);
    //public void quizFileMonitor();
    void sendAnswer(EventClient eventClient, String answer, Session session);
    void exitQuiz(EventClient eventClient, Session session);
}
