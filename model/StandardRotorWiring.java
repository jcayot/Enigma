package model;

public enum StandardRotorWiring {
    I("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "R"),
    II("AJDKSIRUXBLHWTMCQGZNPYFVOE", "F"),
    III("BDFHJLCPRTXVZNYEIWGAKMUSQO", "W"),
    IV("ESOVPZJAYQUIRHXLNFTGKDCMWB", "K"),
    V("VZBRGITYUPSDNHLXAWMJQOFECK", "A"),
    VI("JPGVOUMFYQBENHZRDKASXLICTW", "AN"),
    VII("NZJHGRCXMYSWBOUFAIVLPEKQDT", "AN"),
    VIII("FKQHTLXOCBJSPDZRAMEWNIUYGV", "AN");

    private final char[] wiring;
    private final char[] notch;

    StandardRotorWiring(String wiring, String notch) {
        this.wiring = wiring.toCharArray();
        this.notch = notch.toCharArray();
    }

    public char[] getWiring() {
        return wiring;
    }

    public char[] getNotch() {
        return notch;
    }
}
