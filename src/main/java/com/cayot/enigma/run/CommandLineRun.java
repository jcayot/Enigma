package com.cayot.enigma.run;

import com.cayot.enigma.controller.EnigmaController;
import com.cayot.enigma.model.Enigma;
import com.cayot.enigma.utils.Array;
import com.cayot.enigma.view.EnigmaCommandLine;

public class CommandLineRun {
	public static void main(String[] args) {
		Enigma enigma = new Enigma(Array.makeAlphabeticalArray(26, 'A'), 3);
		EnigmaController controller = new EnigmaController(enigma, new EnigmaCommandLine());
    	controller.run();
	}
}
