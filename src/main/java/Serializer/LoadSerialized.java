//package Serializer;
//
//import Animals.Animal;
//
//import java.io.EOFException;
//import java.io.FileInputStream;
//import java.io.ObjectInputStream;
//import java.sql.ClientInfoStatus;
//import java.util.List;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.util.ArrayList;
//
//public class LoadSerialized {
//
//    public List<Animal> LoadAnimals(){
//
//        List<Animal> animals = null;
//
//        try
//        {
//            FileInputStream fis = new FileInputStream("/Users/ruben/IdeaProjects/Animal-Shelter-GUI/src/main/java/Serializer/data.ser");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//
//            animals = (List<Animal>) ois.readObject();
//
//            ois.close();
//            fis.close();
//        }
//        catch (IOException ioe)
//        {
//            ioe.printStackTrace();
//        }
//        catch (ClassNotFoundException c)
//        {
//            System.out.println("Class not found");
//            c.printStackTrace();
//        }
//
//        //Verify list data
////        for (Animal animal : animals) {
////            System.out.println(animal);
////        }
//
//        return animals;
//    }
//
//}
