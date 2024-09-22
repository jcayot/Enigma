package com.cayot.enigma.model;

import com.cayot.enigma.utils.Array;

public abstract class SubstitutionPart {
    protected char[] characters;
    protected Link[] links;

    protected SubstitutionPart(char[] entryArray, char[] endArray) {
        if (entryArray.length != endArray.length)
            throw new IllegalArgumentException("Entry and substitution arrays must be the same size");
        if (entryArray.length > Character.MAX_VALUE)
            throw new IllegalArgumentException("Array must be smaller than " + Character.MAX_VALUE);
        if (Array.containDuplicate(entryArray))
            throw new IllegalArgumentException("Entry array contain two identical characters");
        if (Array.containDuplicate(endArray))
            throw new IllegalArgumentException("Substitution array contain two identical characters");
        if (!Array.containSameValues(entryArray, endArray))
            throw new IllegalArgumentException("Entry and substitution arrays must contain the same characters");
        this.characters = entryArray;
        this.links = new Link[entryArray.length];
        for (int i = 0; i < entryArray.length; i++) {
    		this.links[i] = new Link(entryArray[i], endArray[i]);
    	}
    }

    public char encode(char c, boolean reverse) throws IllegalArgumentException {
        for (Link link : links)
        {
            if (c == (reverse ? link.c2 : link.c1))
                return (reverse ? link.c1 : link.c2);
        }
        throw new IllegalArgumentException("Character " + c + " not in set");
    }

    public char[] getCharacters() {
        return (characters);
    }
}
