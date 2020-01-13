package model.dictionary;

public class Element {
    public final String Name;
    public final String Shortname;
    public final double Molecular_Mass;

    public Element(String name, String shortname, double molecular_mass){
        Name = name;
        Shortname = shortname;
        Molecular_Mass = molecular_mass;
    }
}