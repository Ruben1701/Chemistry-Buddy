package model.dictionary;

import java.util.*;

public class ElementDictionary {

    private Dictionary elements;

    //row1
    private static final Element Hydrogen = new Element("Hydrogen", "H", 1.00794);
    private static final Element Lithium = new Element("Lithium", "Li", 6.941);
    private static final Element Sodium = new Element("Sodium", "Na", 22.98976);
    private static final Element Potassium = new Element("Potassium", "K", 39.0983);
    private static final Element Rubidium = new Element("Rubidium", "Rb", 85.4678);
    private static final Element Caesium = new Element("Caesium", "Cs", 132.9054);
    private static final Element Francium = new Element("Francium", "Fr", 223);
    //row2
    private static final Element Beryllium = new Element("Beryllium", "Br", 9.012182);
    private static final Element Magnesium = new Element("Magnesium", "Mg", 24.3050);
    private static final Element Calcium = new Element("Calcium", "Ca", 40.078);
    private static final Element Strontium = new Element("Strontium", "Sr", 87.62);
    private static final Element Barium = new Element("Barium", "Ba", 137.327);
    private static final Element Radium = new Element("Radium", "Ra", 226);


    private void elementDictionary(){
        // Initializing a Dictionary
        elements = new Hashtable();

        // info over alle elementen

        //row1
        elements.put("H", Hydrogen);
        elements.put("Li", Lithium);
        elements.put("Na", Sodium);
        elements.put("K", Potassium);
        elements.put("Rb", Rubidium);
        elements.put("Cs", Caesium);
        elements.put("Fr", Francium);
        //row2
        elements.put("Br", Beryllium);
        elements.put("Mg", Magnesium);
        elements.put("Ca", Calcium);
        elements.put("Sr", Strontium);
        elements.put("Ba", Barium);
        elements.put("Ra", Radium);


    }

    public Dictionary getElements(){
        elementDictionary();
        return elements;
    }
}
