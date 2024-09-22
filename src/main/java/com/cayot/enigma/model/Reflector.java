package com.cayot.enigma.model;

import static com.cayot.enigma.utils.Array.makeAlphabeticalArray;

public class Reflector extends SubstitutionPart {

    private Reflector(char[] entryArray, char[] substitutionArray) {
        super(entryArray, substitutionArray);
    }

    public static Reflector make(StandardReflectorWiring standardReflectorWiring) {
        return make(standardReflectorWiring.getWiring());
    }

    public static Reflector make(char[] substitutionArray) throws IllegalArgumentException {
        if (substitutionArray.length > 26)
            throw new IllegalArgumentException("Entry arrays are only generated size upto 26");

        char[] entryArray = makeAlphabeticalArray(substitutionArray.length, 'A');
        return make(entryArray, substitutionArray);
    }

    public static Reflector make(char[] entryArray , char[] substitutionArray) throws IllegalArgumentException {
        return new Reflector(entryArray, substitutionArray);
    }
}
