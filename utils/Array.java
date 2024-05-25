package utils;

public class Array {

    public static int firstIndexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c)
                return i;
        }
        return -1;
    }

    public static boolean contain(char[] array, char c) {
        for (char value : array) {
            if (value == c)
                return true;
        }
        return false;
    }

    public static boolean containDuplicate(char[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i != j) {
                    if (array[i] == array[j])
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean containSameValues(char[] array1, char[] array2)
    {
        for (char value : array1) {
    		if (!contain(array2, value))
    			return false;
    	}
        for (char value : array2) {
            if (!contain(array1, value))
        		return false;
        }
    	return true;
    }

    public static char[] makeAlphabeticalArray(int length, char firstChar) {
        char[] entryArray = new char[length];

        for (int i = 0; i < length; i++)
            entryArray[i] = (char) (firstChar + i);
        return entryArray;
    }
}
