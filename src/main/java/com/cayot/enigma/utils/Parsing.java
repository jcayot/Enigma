package com.cayot.enigma.utils;

public class Parsing {

	public static int parseNumericalInput(String input, int minValue, int maxValue)
			throws IllegalArgumentException {
		int n = Integer.parseInt(input);
		if (n >= minValue && n <= maxValue)
			return (n);
		throw new IllegalArgumentException("Input must be between 1 and " + maxValue + " included.");
	}
}
