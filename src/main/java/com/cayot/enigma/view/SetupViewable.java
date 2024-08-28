package com.cayot.enigma.view;

import com.cayot.enigma.controller.SetupController;
import com.cayot.enigma.model.Link;

public interface SetupViewable {

	void setController(SetupController controller);

	void firstTimeSetupPrompt();

	void standardSetup();

	void rotorSetup(int numberOfRotors);

	void plugboardSetup();

	void promptForRotor(int i);

	void editRotorRingSetting(int slot, int ringSetting, int maxValue);

	void promptForReflector();

	void promptForNewLink();

	void promptForLinkToRemove();

	void displayPlugboardLinks(Link[] links);

	void displayError(String errorMessage);
}
