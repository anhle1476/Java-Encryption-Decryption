package encryptdecrypt;

import java.util.List;
import java.util.stream.Collectors;

abstract class CryptoEngine {
    private static final List<Character> lowercaseMap = "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    private static final List<Character> uppercaseMap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    private String text;
    private int key;

    CryptoEngine(String text, int key) {
        this.text = text;
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public int getKey() {
        return key;
    }

    abstract String byUnicode();

    abstract String byShifting();

    boolean convertAndAppendLowerChar(StringBuilder result,char character, int key) {
        int index = lowercaseMap.indexOf(character);
        if (index >= 0) {
            result.append(getChar(lowercaseMap, index + key));
            return true;
        }
        return false;
    }

    boolean convertAndAppendUpperChar(StringBuilder result,char character, int key) {
        int index = uppercaseMap.indexOf(character);
        if (index >= 0) {
            result.append(getChar(uppercaseMap, index + key));
            return true;
        }
        return false;
    }

    private char getChar(List<Character> map ,int index) {
        int validIndex = index >= 0 ? index % 26 : Math.abs((26 + index) % 26);
        return map.get(validIndex);
    }
}
