package model.dictionary;

public class Element {
    public String Name;
    public String Shortname;
    public double Molecular_Mass;

    public Element(String name, String shortname, double molecular_mass){
        Name = name;
        Shortname = shortname;
        Molecular_Mass = molecular_mass;
    }
}