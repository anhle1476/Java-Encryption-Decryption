package encryptdecrypt;

public class Encoder extends CryptoEngine {

    Encoder(String text, int key) {
        super(text, key);
    }

    @Override
    public String byUnicode() {
        StringBuilder unicodeEncoder = new StringBuilder();
        for (int character : super.getText().toCharArray()) {
            unicodeEncoder.append((char) (character + super.getKey()));
        }
        return unicodeEncoder.toString();
    }

    @Override
    public String byShifting() {
        int encodeKey = super.getKey();
        StringBuilder shiftingEncoder = new StringBuilder();
        for (char character : super.getText().toCharArray()) {
            if (convertAndAppendLowerChar(shiftingEncoder, character, encodeKey)) continue;
            if (convertAndAppendUpperChar(shiftingEncoder, character, encodeKey)) continue;
            shiftingEncoder.append(character);
        }
        return shiftingEncoder.toString();
    }
}
