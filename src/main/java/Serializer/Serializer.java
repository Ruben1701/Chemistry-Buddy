//package Serializer;
//
//import Animals.Animal;
//import Classes.Reservation;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.util.LinkedList;
//import java.util.List;
//
//public class Serializer {
//    public Reservation reservation = new Reservation();
//
//    public void Serialize(List<Animal> toSerialize){
//        try {
//            FileOutputStream out = new FileOutputStream("/Users/ruben/IdeaProjects/Animal-Shelter-GUI/src/main/java/Serializer/data.ser");
//            ObjectOutputStream oos = new ObjectOutputStream(out);
//            for (Animal x : toSerialize){
//                oos.writeObject(toSerialize);
//            }
//            oos.close();
//            out.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//}
