package controller;

import model.*;
import utils.Parsing;
import view.SetupViewable;

public class SetupController {
	private final Enigma enigma;
	private final SetupViewable view;
	private boolean running = true;

	public SetupController(Enigma enigma, SetupViewable view) {
		this.enigma = enigma;
		this.view = view;
		this.view.setController(this);
	}

	public void run() {
		if (enigma.requireSetup())
			this.initialSetup();
		else {
			while (running) {
				view.standardSetup();
			}
		}
	}

	public void parseSetupMenuAction(String input) {
		int n = Parsing.parseNumericalInput(input, 1, 4);
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
			int n = Parsing.parseNumericalInput(input, 1, 3);
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
		int n = Parsing.parseNumericalInput(input, 1, 3);
		switch (n) {
			case 1 -> view.promptForNewLink();
			case 2 -> view.promptForLinkToRemove();
			case 3 -> view.displayPlugboardLinks(enigma.getPlugboardLinks());
			case 4 -> this.exit();
		}
	}

	public void putStandardRotor(int slot, String input) {
		int n = Parsing.parseNumericalInput(input, 1, 7);
		if (n != -1) {
			try {
				enigma.putRotor(slot, Rotor.make(StandardRotorWiring.values()[n - 1]));
			} catch (Exception e) {
				view.displayError(e.getMessage());
			}
		}
	}

	public void editRotorRingSetting(int slot, String input) {
		int n = Parsing.parseNumericalInput(input, 0, enigma.getCharactersLength());
		if (n != -1) {
			try {
				enigma.setRingSetting(slot, n);
			} catch (Exception e) {
				view.displayError(e.getMessage());
			}
		}
	}

	public void editRotorOffset(int slot, String input) {
		int n = Parsing.parseNumericalInput(input, 0, enigma.getCharactersLength());
		if (n != -1) {
			try {
				enigma.setRotorOffset(slot, n);
			} catch (Exception e) {
				view.displayError(e.getMessage());
			}
		}
	}

	public void putStandardReflector(String input) {
		int n = Parsing.parseNumericalInput(input, 1, 3);
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

	private void exit() {
		this.running = false;
	}
}
