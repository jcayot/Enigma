package com.cayot.enigma.view;

import com.cayot.enigma.controller.EnigmaController;

public interface EnigmaViewable {

    void setController(EnigmaController controller);

    void displayError(String errorMessage);

    void promptForMainMenuAction();

    void encodePrompt();

    void displayEncodedMessage(String string);
}
