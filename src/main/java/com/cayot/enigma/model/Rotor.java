package com.cayot.enigma.model;

import com.cayot.enigma.utils.Array;

import static com.cayot.enigma.utils.Array.makeAlphabeticalArray;

public class Rotor extends SubstitutionPart {
    private final char[] notch;
    private int ringSetting;
    private int offset;

    private Rotor(char[] entryArray, char[] substitutionArray, char[] notch) {
        super(entryArray, substitutionArray);
        for (char c : notch) {
            if (!Array.contain(entryArray, c))
                throw new IllegalArgumentException("Notch must be contained in entry array");
        }
        this.notch = notch;
        this.ringSetting = 0;
        this.offset = 0;
    }

    public static Rotor make(StandardRotorWiring standardRotorWiring) {
        return make(standardRotorWiring.getWiring(), standardRotorWiring.getNotch());
    }

    public static Rotor make(char[] substitutionArray, char[] notch) throws IllegalArgumentException {
        if (substitutionArray.length > 26)
            throw new IllegalArgumentException("Entry arrays are only generated for size upto 26");

        char[] entryArray = makeAlphabeticalArray(substitutionArray.length, 'A');
        return make(entryArray, substitutionArray, notch);
    }

    public static Rotor make(char[] entryArray, char[] substitutionArray, char[] notch) throws IllegalArgumentException {
        return new Rotor(entryArray, substitutionArray, notch);
    }

    @Override
    public char encode(char c, boolean reverse) throws IllegalArgumentException {
        if (!Array.contain(characters, c))
            throw new IllegalArgumentException("Character " + c + " not in set");

        c = characters[(Array.firstIndexOf(characters, c) + offset - ringSetting + characters.length) % characters.length];
        c = super.encode(c, reverse);
        c = characters[(Array.firstIndexOf(characters, c) - offset + ringSetting + characters.length ) % characters.length];
        return (c);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRingSetting() {
        return ringSetting;
    }

    public void setRingSetting(int ringSetting) throws IllegalArgumentException {
        if (ringSetting < characters.length)
            this.ringSetting = ringSetting;
        else
            throw new IllegalArgumentException();
    }

    public boolean isOnNotch() {
        return (Array.contain(notch, characters[offset % 26]));
    }

    public boolean incrementOffset() {
        boolean passedNotch = isOnNotch();

        if (offset < characters.length - 1)
            offset++;
        else
            offset = 0;
        return (passedNotch);
    }
}
