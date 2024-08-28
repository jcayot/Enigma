package com.cayot.enigma.model;

import java.util.Arrays;

public class Plugboard extends SubstitutionPart {

    public Plugboard(char[] entryArray) {
        super(entryArray, entryArray);
    }

    public void addLink(char c1, char c2) throws IllegalArgumentException {
        int linkIndex, reverseLinkIndex;

        linkIndex = getLinkIndex(c1);
        reverseLinkIndex = getLinkIndex(c2);
        if (linkIndex == -1)
            throw new IllegalArgumentException(c1 + " is not in set");
        if (reverseLinkIndex == -1)
            throw new IllegalArgumentException(c2 + " is not in set");
        if (links[linkIndex].c2 != c1)
            throw new IllegalArgumentException(c1 + " is already wired to " + links[linkIndex].c2);
        if (links[reverseLinkIndex].c1 != c2)
            throw new IllegalArgumentException(c2 + " is already wired to " + links[reverseLinkIndex].c1);
        links[linkIndex] = new Link(c1, c2);
        links[reverseLinkIndex] = new Link(c2, c1);
    }

    public Link[] getLinks()
    {
        Link[] wired_links;
        int i = 0;

        wired_links = new Link[characters.length];
        for (Link link : this.links)
        {
            if (link.c1 != link.c2)
            {
                wired_links[i] = link;
                i++;
            }
        }
        return (Arrays.copyOfRange(wired_links, 0, i));
    }

    public void removeLink(char c) throws IllegalArgumentException {
        int linkIndex = getLinkIndex(c);

        if (linkIndex == -1)
            throw new IllegalArgumentException(c + " is not in set");
        else if (links[linkIndex].c1 == links[linkIndex].c2)
            throw new IllegalArgumentException(c + " is not currently wired");
        int reverseLinkIndex = getLinkIndex(links[linkIndex].c2);
        links[linkIndex] = new Link(characters[linkIndex], characters[linkIndex]);
    	links[reverseLinkIndex] = new Link(characters[reverseLinkIndex], characters[reverseLinkIndex]);
    }

    private int getLinkIndex(char c) {
        for (int i = 0; i < links.length; i++) {
    		if (links[i].c1 == c) {
    			return i;
    		}
    	}
    	return -1;
    }
}
