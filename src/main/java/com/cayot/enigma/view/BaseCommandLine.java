package com.cayot.enigma.view;

import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class BaseCommandLine {

	private final Scanner scanner;

	BaseCommandLine() {
		scanner = new Scanner(System.in);
	}

	protected String consoleNextLine() throws NoSuchElementException {
		return (scanner.nextLine());
	}

	protected int consoleNextInt() throws NoSuchElementException {
		return (scanner.nextInt());
	}

	protected void consoleSkipNextLine() throws NoSuchElementException {
		if (scanner.hasNextLine())
			scanner.nextLine();
	}

	public void displayError(String errorMessage) {
		System.err.println("Error : " + errorMessage);
	}
}