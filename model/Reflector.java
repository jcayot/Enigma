package model;

import utils.Array;

import static utils.Array.makeAlphabeticalArray;

public class Reflector extends SubstitutionPart {

    private Reflector(char[] entryArray, char[] substitutionArray) {
        super(entryArray, substitutionArray);
    }

    public static Reflector make(StandardReflectorWiring standardReflectorWiring) {
        return make(standardReflectorWiring.getWiring());
    }

    public static Reflector make(char[] substitutionArray) throws IllegalArgumentException {
        if (substitutionArray.length > 26)
            throw new IllegalArgumentException("Entry arrays are only generated for rotor size upto 26");

        char[] entryArray = makeAlphabeticalArray(substitutionArray.length, 'A');
        return make(entryArray, substitutionArray);
    }

    public static Reflector make(char[] entryArray , char[] substitutionArray) throws IllegalArgumentException {
        if (entryArray.length != substitutionArray.length)
            throw new IllegalArgumentException("Entry and substitution arrays must be the same size");
        if (entryArray.length > Character.MAX_VALUE)
            throw new IllegalArgumentException("Array must be smaller than " + Character.MAX_VALUE);
        if (Array.containDuplicate(entryArray))
            throw new IllegalArgumentException("Entry array contain two identical characters");
        if (Array.containDuplicate(substitutionArray))
            throw new IllegalArgumentException("Substitution array contain two identical characters");
        if (!Array.containSameValues(entryArray, substitutionArray))
            throw new IllegalArgumentException("Entry and substitution arrays must contain the same characters");
        return new Reflector(entryArray, substitutionArray);
    }
}
