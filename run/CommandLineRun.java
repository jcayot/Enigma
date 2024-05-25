package run;

import controller.EnigmaController;
import model.Enigma;
import utils.Array;
import view.CommandLineView;

public class CommandLineRun {
	public static void main(String[] args) {
		Enigma enigma = new Enigma(Array.makeAlphabeticalArray(26, 'A'), 3);
		EnigmaController controller = new EnigmaController(enigma, new CommandLineView());
    	controller.run();
	}
}
