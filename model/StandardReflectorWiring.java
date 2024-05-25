package model;

public enum StandardReflectorWiring {
    A("EJMZALYXVBWFCRQUONTSPIKHGD"),
    B("YRUHQSLDPXNGOKMIEBFZCWVJAT"),
    C("FVPJIAOYEDRZXWGCTKUQSBNMHL");

    private final char[] wiring;

    StandardReflectorWiring(String wiring) {
        this.wiring = wiring.toCharArray();
    }

    public char[] getWiring() {
        return wiring;
    }
}
