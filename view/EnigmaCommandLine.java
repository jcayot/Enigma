package view;

import controller.EnigmaController;

import java.util.Scanner;

public class EnigmaCommandLine extends BaseCommandLine implements EnigmaViewable {

    private EnigmaController controller;
    private final Scanner scanner;

    public EnigmaCommandLine() {
        this.scanner = new Scanner(System.in);
    }

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
        controller.parseMainMenuAction(scanner.nextLine());
    }

    @Override
    public void encodePrompt() {
        System.out.println("Enter the message to encode: ");
    	controller.encodeInput(scanner.nextLine());
    }

    @Override
    public void displayEncodedMessage(String string) {
        System.out.println("Encoded message: " + string);
    }
}
