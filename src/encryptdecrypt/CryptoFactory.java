package encryptdecrypt;

public class CryptoFactory {

    public static CryptoEngine setEngine(String type, String text, int key) {
        switch (type) {
            case "enc":
                return new Encoder(text, key);
            case "dec":
                return new Decoder(text, key);
            default:
                return null;
        }
    }
}
