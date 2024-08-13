package com.cayot.enigma.view;

import com.cayot.enigma.controller.EnigmaController;

import java.util.NoSuchElementException;

public class EnigmaCommandLine extends BaseCommandLine implements EnigmaViewable {

    private EnigmaController controller;

    @Override
    public void setController(EnigmaController controller) {
        this.controller = controller;
    }

    @Override
    public void promptForMainMenuAction() {
        System.out.println("Enigma");
        System.out.println("Choose an action :");
        System.out.println("1 - Encode/decode a message");
        System.out.println("2 - Modify Enigma configuration");
        System.out.println("3 - Exit");
        try {
            controller.parseMainMenuAction(consoleNextLine());
        } catch (NoSuchElementException e) {
            controller.viewFatalError(e);
        }
    }

    @Override
    public void encodePrompt() {
        System.out.println("Enter the message to encode: ");
        try {
            controller.encodeInput(consoleNextLine());
        } catch (NoSuchElementException e) {
            controller.viewFatalError(e);
        }
    }

    @Override
    public void displayEncodedMessage(String string) {
        System.out.println("Encoded message: " + string);
    }
}
