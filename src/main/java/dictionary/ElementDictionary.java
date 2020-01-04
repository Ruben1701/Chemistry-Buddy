package dictionary;

import java.util.*;

public class ElementDictionary {

    private Dictionary elements;

    static final Element Hydrogen = new Element("Hydrogen", "H", 1.00794);
    static final Element Lithium = new Element("Lithium", "Li", 6.941);
    static final Element Sodium = new Element("Sodium", "Na", 22.98976);
    static final Element Potassium = new Element("Potassium", "K", 39.0983);

    public void elementDictionary(){
        // Initializing a Dictionary
        elements = new Hashtable();

        // info over alle elementen

        elements.put("H", Hydrogen);
        elements.put("Li", Lithium);
        elements.put("Na", Sodium);
        elements.put("K", Potassium);
    }

    public Dictionary getElements(){
        elementDictionary();
        return elements;
    }
}
