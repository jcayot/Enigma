package com.cayot.enigma.model;

public class Link {
	final char c1;
	final char c2;

	public Link(char c1, char c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public char getC1() {
		return (c1);
	}

	public char getC2() {
		return (c2);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return (true);
		if (o == null || getClass() != o.getClass())
			return (false);
		Link link = (Link) o;
		return (c1 == link.c1 && c2 == link.c2);
	}
}
