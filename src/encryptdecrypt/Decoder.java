package encryptdecrypt;

public class Decoder extends CryptoEngine {

    Decoder(String text, int key) {
        super(text, key);
    }

    @Override
    public String byUnicode() {
        StringBuilder unicodeDecoder = new StringBuilder();
        for (int character : super.getText().toCharArray()) {
            unicodeDecoder.append((char) (character - super.getKey()));
        }
        return unicodeDecoder.toString();
    }

    @Override
    public String byShifting() {
        int decodeKey = -super.getKey();
        StringBuilder shiftingEncoder = new StringBuilder();
        for (char character : super.getText().toCharArray()) {
            if (convertAndAppendLowerChar(shiftingEncoder, character, decodeKey)) continue;
            if (convertAndAppendUpperChar(shiftingEncoder, character, decodeKey)) continue;
            shiftingEncoder.append(character);
        }
        return shiftingEncoder.toString();
    }
}
