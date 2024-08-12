package com.cayot.enigma.view;

import com.cayot.enigma.controller.SetupController;
import com.cayot.enigma.model.Link;

public interface SetupViewable {

	void setController(SetupController controller);

	void rotorSetup(int numberOfRotors);

	void plugboardSetup();

	void promptForLinkToRemove();

	void promptForNewLink();

	void displayPlugboardLinks(Link[] links);

	void editRotorRingSetting(int slot, int ringSetting, int maxValue);

	void editRotorOffset(int slot, int ringSetting, int maxValue);

	void standardSetup();

	void firstTimeSetupPrompt();

	void promptForRotor(int i);

	void promptForReflector();

	void displayError(String errorMessage);
}
