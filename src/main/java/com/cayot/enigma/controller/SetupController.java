package com.cayot.enigma.controller;

import com.cayot.enigma.model.*;
import com.cayot.enigma.view.SetupViewable;

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

	public void setupMenuAction(int action) {
		switch (action) {
			case 1 -> view.rotorSetup(enigma.getNumberOfRotors());
			case 2 -> this.resetRotorsOffset();
			case 3 -> view.promptForReflector();
			case 4 -> view.plugboardSetup();
			case 5 -> this.exit();
		}
	}

	public void parseRotorSetupMenuAction(int slot, int action) {
		switch (action) {
			case 1 -> view.promptForRotor(slot);
			case 2 -> view.editRotorRingSetting(slot, enigma.getRingSetting(slot), enigma.getCharactersLength());
		}
	}

	public void parsePlugboardSetupMenuAction(int action) {
		switch (action) {
			case 1 -> view.promptForNewLink();
			case 2 -> view.promptForLinkToRemove();
			case 3 -> view.displayPlugboardLinks(enigma.getPlugboardLinks());
			case 4 -> this.exit();
		}
	}

	public void putStandardRotor(int slot, int value) {
		try {
			enigma.putRotor(slot, Rotor.make(StandardRotorWiring.values()[value - 1]));
		} catch (Exception e) {
			view.displayError(e.getMessage());
		}
	}

	public void	resetRotorsOffset() {
		for (int i = 0; i < enigma.getNumberOfRotors(); i++) {
			enigma.setRotorOffset(i, 0);
		}
	}

	public void editRotorRingSetting(int slot, int value) {
		try {
			enigma.setRingSetting(slot, value);
		} catch (Exception e) {
			view.displayError(e.getMessage());
		}
	}

	public void putStandardReflector(int value) {
		try {
			enigma.setReflector(Reflector.make(StandardReflectorWiring.values()[value - 1]));
		} catch (Exception e) {
			view.displayError(e.getMessage());
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

	public void viewFatalError(Exception e) {
		view.displayError(e.getMessage());
		System.exit(1);
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
