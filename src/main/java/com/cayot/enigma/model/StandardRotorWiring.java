package com.cayot.enigma.model;

public enum StandardRotorWiring {
    I("EKMFLGDQVZNTOWYHXUSPAIBRCJ", "Q"),
    II("AJDKSIRUXBLHWTMCQGZNPYFVOE", "E"),
    III("BDFHJLCPRTXVZNYEIWGAKMUSQO", "V"),
    IV("ESOVPZJAYQUIRHXLNFTGKDCMWB", "J"),
    V("VZBRGITYUPSDNHLXAWMJQOFECK", "Z"),
    VI("JPGVOUMFYQBENHZRDKASXLICTW", "ZM"),
    VII("NZJHGRCXMYSWBOUFAIVLPEKQDT", "ZM"),
    VIII("FKQHTLXOCBJSPDZRAMEWNIUYGV", "ZM");

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
