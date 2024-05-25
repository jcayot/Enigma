package model;

public abstract class SubstitutionPart {
    protected char[] characters;
    protected Link[] links;

    protected SubstitutionPart(char[] entryArray, char[] endArray) {
        this.characters = entryArray;
        this.links = new Link[entryArray.length];
        for (int i = 0; i < entryArray.length; i++) {
    		this.links[i] = new Link(entryArray[i], endArray[i]);
    	}
    }

    public char encode(char c, boolean reverse) throws IllegalArgumentException {
        for (Link link : links)
        {
            if (c == (reverse ? link.c2 : link.c1))
                return reverse ? link.c1 : link.c2;
        }
        throw new IllegalArgumentException("Character " + c + " not in set");
    }

    public char[] getCharacters() {
        return characters;
    }
}
