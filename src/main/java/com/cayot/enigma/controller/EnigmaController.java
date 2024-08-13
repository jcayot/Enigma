package com.cayot.enigma.controller;

import com.cayot.enigma.model.*;
import com.cayot.enigma.utils.Parsing;
import com.cayot.enigma.view.EnigmaCommandLine;
import com.cayot.enigma.view.EnigmaViewable;
import com.cayot.enigma.view.SetupCommandLine;
import com.cayot.enigma.view.SetupViewable;

import java.util.NoSuchElementException;

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
        try {
            int n = Parsing.parseNumericalInput(input, 1, 3);
            switch (n) {
                case 1 -> view.encodePrompt();
                case 2 -> this.launchSetup();
                case 3 -> this.exit();
            }
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    public void viewFatalError(Exception e) {
        view.displayError(e.getMessage());
        System.exit(1);
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
