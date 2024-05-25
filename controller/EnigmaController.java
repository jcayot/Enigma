package controller;

import model.*;
import view.EnigmaViewable;

import java.util.InputMismatchException;

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
                initialSetup();
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
        int n = parseNumericalInput(input, 1, 3);
        switch (n) {
            case 1 -> view.encodePrompt();
            case 2 -> view.standardSetup();
            case 3 -> this.exit();
        }
    }

    public void parseSetupMenuAction(String input) {
        int n = parseNumericalInput(input, 1, 3);
        switch (n) {
            case 1 -> view.rotorSetup(enigma.getNumberOfRotors());
            case 2 -> view.promptForReflector();
            case 3 -> view.plugboardSetup();
        }
    }

    public void parseRotorSetupMenuAction(int slot, String input) {
        try {
            if (slot < 0 || slot > enigma.getNumberOfRotors())
                throw new IndexOutOfBoundsException("Rotor number out of bond. Must be between 0 and " + (enigma.getNumberOfRotors() - 1) + " incuded.");
            int n = parseNumericalInput(input, 1, 3);
            if (n != -1) {
                switch (n) {
                    case 1 -> view.promptForRotor(slot);
                    case 2 -> view.editRotorRingSetting(slot, enigma.getRingSetting(slot), enigma.getCharactersLength());
                    case 3 -> view.editRotorOffset(slot, enigma.getRotorOffset(slot), enigma.getCharactersLength());
                }
            }
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    public void parsePlugboardSetupMenuAction(String input) {
        int n = parseNumericalInput(input, 1, 3);
        switch (n) {
            case 1 -> view.promptForNewLink();
            case 2 -> view.promptForLinkToRemove();
            case 3 -> view.displayPlugboardLinks(enigma.getPlugboardLinks());
        }
    }

    public void putStandardRotor(int slot, String input) {
        int n = parseNumericalInput(input, 1, 7);
        if (n != -1) {
            try {
                enigma.putRotor(slot, Rotor.make(StandardRotorWiring.values()[n - 1]));
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        }
    }

    public void editRotorRingSetting(int slot, String input) {
        int n = parseNumericalInput(input, 0, enigma.getCharactersLength());
        if (n != -1) {
            try {
                enigma.setRingSetting(slot, n);
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        }
    }

    public void editRotorOffset(int slot, String input) {
        int n = parseNumericalInput(input, 0, enigma.getCharactersLength());
    	if (n != -1) {
    		try {
    			enigma.setRotorOffset(slot, n);
    		} catch (Exception e) {
    			view.displayError(e.getMessage());
    		}
    	}
    }

    public void putStandardReflector(String input) {
        int n = parseNumericalInput(input, 1, 3);
        if (n != -1) {
            try {
                enigma.setReflector(Reflector.make(StandardReflectorWiring.values()[n - 1]));
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        }
    }

    public void addPlugboardLink(char c1, char c2) {
        try {
            enigma.addPlugboardLink(c1, c2);
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    public void removePlugboardLink(char c) {
        try {
            enigma.removePlugboardLink(c);
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }

    private void initialSetup() {
        view.firstTimeSetupPrompt();
        for (int i = 0; i < enigma.getNumberOfRotors(); i++) {
            while (enigma.rotorSlotEmpty(i))
                view.promptForRotor(i);
        }
        while (enigma.reflectorSlotEmpty())
            view.promptForReflector();
    }

    private int parseNumericalInput(String input, int minValue, int maxValue) {
        try {
            int n = Integer.parseInt(input);
            if (n >= minValue && n <= maxValue)
                return (n);
            throw new InputMismatchException("Input must be between 1 and " + maxValue + " included.");
        } catch (NumberFormatException e) {
            view.displayError("Invalid input. Please enter a number between 1 and " + maxValue + ".");
            return (-1);
        } catch (RuntimeException e) {
            view.displayError(e.getMessage());
            return (-1);
        }
    }

    private void exit() {
        this.running = false;
    }
}
