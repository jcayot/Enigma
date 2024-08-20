package com.cayot.enigma.model;

import java.util.Arrays;

public class Enigma {

    private final char[] characters;
    private final Plugboard plugboard;
    private final Rotor[] rotors;
    private Reflector reflector;

    public Enigma(char[] characters, int numberOfRotors) {
        this.characters = characters;
        this.plugboard = new Plugboard(characters);
        if (numberOfRotors > 5)
            this.rotors = new Rotor[5];
        else if (numberOfRotors > 0)
            this.rotors = new Rotor[numberOfRotors];
        else
            this.rotors = new Rotor[1];
    }

    public int getCharactersLength() {
        return characters.length;
    }

    public boolean requireSetup() {
        for (Rotor rotor : rotors) {
            if (rotor == null)
                return true;
        }
        return reflectorSlotEmpty();
    }

    public boolean rotorSlotEmpty(int rotorIndex) throws IndexOutOfBoundsException {
        return rotors[rotorIndex] == null;
    }

    public int getNumberOfRotors() {
        return rotors.length;
    }

    public void putRotor(int rotorIndex, Rotor rotor) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (!Arrays.equals(this.characters, rotor.getCharacters()))
            throw new IllegalArgumentException("Rotor entry array don't match with enigma entry array.");
        this.rotors[rotorIndex] = rotor;
    }

    public int getRingSetting(int rotorIndex) throws IndexOutOfBoundsException {
        return rotors[rotorIndex].getRingSetting();
    }

    public void setRingSetting(int rotorIndex, int ringSetting) throws IndexOutOfBoundsException, IllegalArgumentException {
        rotors[rotorIndex].setRingSetting(ringSetting);
    }

    public int getRotorOffset(int rotorIndex) throws IndexOutOfBoundsException {
        return rotors[rotorIndex].getOffset();
    }

    public void setRotorOffset(int rotorIndex, int ringOffset) throws IndexOutOfBoundsException, IllegalArgumentException {
        rotors[rotorIndex].setOffset(ringOffset);
    }

    public boolean reflectorSlotEmpty() {
        return reflector == null;
    }

    public void setReflector(Reflector reflector) throws IllegalArgumentException {
        if (!Arrays.equals(this.characters, reflector.getCharacters()))
            throw new IllegalArgumentException("Reflector entry array don't match with enigma entry array.");
        this.reflector = reflector;
    }

    public void addPlugboardLink(char c1, char c2) throws IllegalArgumentException {
        plugboard.addLink(c1, c2);
    }

    public Link[] getPlugboardLinks() {
        return plugboard.getLinks();
    }

    public void removePlugboardLink(char c1) throws IllegalArgumentException {
        plugboard.removeLink(c1);
    }

    public char encode(char c) throws IllegalArgumentException, IllegalStateException {
        if (this.requireSetup())
			throw new IllegalStateException("Enigma is not yet set-up. Please set-up before encoding.");

        c = plugboard.encode(c, false);

        boolean previousMoved = true;
        for (int i = 0; i < rotors.length; i++) {
            if (previousMoved) {
                if (i == 0 || rotors[i - 1].isOnNotch())
                    rotors[i].incrementOffset();
                else
                    previousMoved = false;
            }
            c = rotors[i].encode(c, false);
        }

        c = reflector.encode(c, false);

        for (int i = rotors.length - 1; i >= 0; i--)
            c = rotors[i].encode(c, true);

        c = plugboard.encode(c, true);

        return (c);
    }
}
