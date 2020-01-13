package service;
import interfaces.iQuiz;
import websocket.EventClient;

import javax.websocket.Session;

public class QuizService implements iQuiz {
    @Override
    public Session startQuiz(EventClient eventClient) {
        return eventClient.websocket("quiz");
    }

//    @Override
//    public void quizFileMonitor() {
//        LogManager lgmngr = LogManager.getLogManager();
//        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
//
//        TimerTask task = new FileWatcher(new File("src/main/java/Serializer/data.ser")) {
//            protected void onChange(File file) {
//                //runlater because UI changes need to be made on the javaFx thread
////                Platform.runLater(
////                        () -> {
////                            //try {
////                            setQuestion(loadSerialized.LoadQuestion());
////                            if (loadSerialized.LoadQuestion().equals("End of quiz")){
////                                Quitbtn.setDisable(false);
////                                quizFinished = true;
////
////                            }
////                            //} catch () {
////                            //log.log(Level.SEVERE, (Supplier<String>) e);
////                            //e.printStackTrace();
////                            //}
////                        }
////                );
//                log.log(Level.INFO, "File " + file.getName() + " has been changed");
//                //System.out.println( "File "+ file.getName() +" has been changed" );
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(task, new Date(), 2000);
//    }

    @Override
    public void sendAnswer(EventClient eventClient, String answer, Session session) {
        eventClient.sendMessage(answer, session);
    }

    @Override
    public void exitQuiz(EventClient eventClient, Session session) {
        eventClient.closeConnection(session);
    }
}
