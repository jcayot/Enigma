package run;

import controller.EnigmaController;
import model.Enigma;
import utils.Array;
import view.EnigmaCommandLine;

public class CommandLineRun {
	public static void main(String[] args) {
		Enigma enigma = new Enigma(Array.makeAlphabeticalArray(26, 'A'), 3);
		EnigmaController controller = new EnigmaController(enigma, new EnigmaCommandLine());
    	controller.run();
	}
}
