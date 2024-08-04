package controller;

import model.*;
import utils.Parsing;
import view.EnigmaCommandLine;
import view.EnigmaViewable;
import view.SetupCommandLine;
import view.SetupViewable;

public class EnigmaController {
    private final Enigma enigma;
    private final EnigmaViewable view;
    private boolean running = true;

    public EnigmaController(Enigma enigma, EnigmaViewable view) {
        this.enigma = enigma;
        this.view = view;
        this.view.setController(this);
    }

    public void run() {
        while (running)
        {
            if (enigma.requireSetup())
                this.launchSetup();
            view.promptForMainMenuAction();
        }
    }

    public void encodeInput(String input) {
        try {
            StringBuilder encodedInput = new StringBuilder();
            for (char c : input.toCharArray())
                encodedInput.append(enigma.encode(c));
            view.displayEncodedMessage(encodedInput.toString());
        } catch (RuntimeException e) {
            view.displayError(e.getMessage());
        }
    }

    public void parseMainMenuAction(String input) {
        int n = Parsing.parseNumericalInput(input, 1, 3);
        switch (n) {
            case 1 -> view.encodePrompt();
            case 2 -> this.launchSetup();
            case 3 -> this.exit();
        }
    }

    private void launchSetup() {
        SetupController setupController;
        SetupViewable setupView = null;

        if (view instanceof EnigmaCommandLine)
            setupView = new SetupCommandLine();
        setupController = new SetupController(enigma, setupView);
        setupController.run();
    }

    private void exit() {
        this.running = false;
    }
}
