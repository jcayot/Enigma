package view;

import controller.EnigmaController;
import model.Link;

public interface EnigmaViewable {

    void setController(EnigmaController controller);

    void firstTimeSetupPrompt();

    void promptForRotor(int i);

    void promptForReflector();

    void displayError(String errorMessage);

    void promptForMainMenuAction();

    void standardSetup();

    void encodePrompt();

    void displayEncodedMessage(String string);

    void rotorSetup(int numberOfRotors);

    void plugboardSetup();

    void promptForLinkToRemove();

    void promptForNewLink();

    void displayPlugboardLinks(Link[] links);

    void editRotorRingSetting(int slot, int ringSetting, int maxValue);

    void editRotorOffset(int slot, int ringSetting, int maxValue);
}
