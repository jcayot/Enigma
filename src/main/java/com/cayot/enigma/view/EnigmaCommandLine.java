package com.cayot.enigma.view;

import com.cayot.enigma.controller.EnigmaController;
import com.cayot.enigma.utils.Parsing;

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
            String input = consoleNextLine();
            controller.mainMenuAction(Parsing.parseNumericalInput(input, 1, 3));
        } catch (NoSuchElementException e) {
            controller.viewFatalError(e);
        } catch (IllegalArgumentException e) {
            this.displayError(e.getMessage());
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
