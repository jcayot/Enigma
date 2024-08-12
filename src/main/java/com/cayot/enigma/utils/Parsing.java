package com.cayot.enigma.utils;

import java.util.InputMismatchException;

public class Parsing {

	public static int parseNumericalInput(String input, int minValue, int maxValue)
			throws InputMismatchException, NumberFormatException {
		int n = Integer.parseInt(input);
		if (n >= minValue && n <= maxValue)
			return (n);
		throw new InputMismatchException("Input must be between 1 and " + maxValue + " included.");
	}
}
