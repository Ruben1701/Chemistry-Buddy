package interfaces;

import websocket.EventClient;

import javax.websocket.Session;

public interface iQuiz {
    public Session startQuiz(EventClient eventClient);
    //public void quizFileMonitor();
    public void sendAnswer(EventClient eventClient, String answer, Session session);
    public void exitQuiz(EventClient eventClient, Session session);
}
