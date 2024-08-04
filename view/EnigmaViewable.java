package view;

import controller.EnigmaController;

public interface EnigmaViewable {

    void setController(EnigmaController controller);

    void displayError(String errorMessage);

    void promptForMainMenuAction();

    void encodePrompt();

    void displayEncodedMessage(String string);
}
