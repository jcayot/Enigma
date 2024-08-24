package com.cayot.enigma.view;

import com.cayot.enigma.controller.SetupController;
import com.cayot.enigma.model.Link;
import com.cayot.enigma.utils.Parsing;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class SetupCommandLine extends BaseCommandLine implements SetupViewable {

	private SetupController controller;

	@Override
	public void setController(SetupController controller) {
		this.controller = controller;
	}

	@Override
	public void firstTimeSetupPrompt() {
		System.out.println("Welcome to Enigma! Let's set up your machine.");
	}

	@Override
	public void standardSetup() {
		System.out.println("Enigma - Setup :");
		System.out.println("What would you like to change :");
		System.out.println("1 - Rotor configuration");
		System.out.println("2 - Reset all rotors offset");
		System.out.println("3 - Reflector configuration");
		System.out.println("4 - Plugboard configuration");
		System.out.println("5 - Exit configuration");
		try {
			String input = consoleNextLine();
			controller.setupMenuAction(Parsing.parseNumericalInput(input, 1, 5));
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		} catch (IllegalArgumentException e) {
			this.displayError(e.getMessage());
		}
	}

	@Override
	public void rotorSetup(int numberOfRotors) {
		System.out.println("In which slot would you like to edit rotor ?");
		try {
			int slot = Parsing.parseNumericalInput(consoleNextLine(), 0, numberOfRotors - 1);
			System.out.println("What action would you like to perform ?");
			System.out.println("1 - Change rotor");
			System.out.println("2 - Edit ring setting");
			String input = consoleNextLine();
			controller.parseRotorSetupMenuAction(slot, Parsing.parseNumericalInput(input, 1, 2));
		} catch (InputMismatchException | IllegalArgumentException e) {
			this.displayError(e.getMessage());
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		}
	}

	@Override
	public void plugboardSetup() {
		System.out.println("Plugboard setup : ");
		System.out.println("1 - Add a link");
		System.out.println("2 - Remove a link");
		System.out.println("3 - Show current links");
		try {
			String input = consoleNextLine();
			controller.parsePlugboardSetupMenuAction(Parsing.parseNumericalInput(input, 1, 3));
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		} catch (IllegalArgumentException e) {
			this.displayError(e.getMessage());
		}
	}

	@Override
	public void promptForRotor(int i) {
		System.out.println("Enter the type of rotor for slot " + i + " : ");
		try {
			String input = consoleNextLine();
			controller.putStandardRotor(i, Parsing.parseNumericalInput(input, 1, 7));
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		} catch (IllegalArgumentException e) {
			this.displayError(e.getMessage());
		}
	}

	@Override
	public void editRotorRingSetting(int slot, int ringSetting, int maxValue) {
		System.out.println("Current ring setting for rotor " + slot + " is index " + ringSetting);
		System.out.println("Enter the new ring setting for rotor " + slot + " (0-" + maxValue + "): ");
		System.out.println("All rotors offset will be reset");
		try {
			String input = consoleNextLine();
			controller.editRotorRingSetting(slot, Parsing.parseNumericalInput(input, 0, maxValue));
			controller.resetRotorsOffset();
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		} catch (IllegalArgumentException e) {
			this.displayError(e.getMessage());
		}
	}

	@Override
	public void promptForReflector() {
		System.out.println("Enter the type of reflector : ");
		try {
			String input = consoleNextLine();
			controller.putStandardReflector(Parsing.parseNumericalInput(input, 1, 3));
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		} catch (IllegalArgumentException e) {
			this.displayError(e.getMessage());
		}
	}

	@Override
	public void promptForNewLink() {
		System.out.println("Enter the first character of the link: ");
		try {
			char c1 = consoleNextLine().charAt(0);
			System.out.println("Enter the second character of the link: ");
			char c2 = consoleNextLine().charAt(0);
			controller.addPlugboardLink(c1, c2);
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		}
	}

	@Override
	public void promptForLinkToRemove() {
		System.out.println("Enter the character to remove from the link: ");
		try {
			char c = consoleNextLine().charAt(0);
			controller.removePlugboardLink(c);
		} catch (NoSuchElementException e) {
			controller.viewFatalError(e);
		}
	}

	@Override
	public void displayPlugboardLinks(Link[] links)  {
		System.out.println("Current plugboard links :");
		for (Link link : links) {
			System.out.println(link.getC1() + " to " + link.getC2());
		}
	}
}
