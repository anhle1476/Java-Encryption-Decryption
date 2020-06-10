package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static CryptoEngine engine;
    private static String action = "";
    private static String data = "";
    private static int key = 0;
    private static String algorithm = "";
    private static String fileOutput = "";


    public static void main(String[] args) {
        if (args.length > 0) {
            cliInputHandler(args);
        } else {
            scannerInputHandler();
        }

        engine = CryptoFactory.setEngine(action, data, key);

        if (engine == null) return;

        String result = encodeDecode();
        if (result == null) return;

        if (fileOutput.isEmpty()) {
            System.out.println(result);
        } else {
            try (FileWriter fileWriter = new FileWriter(new File(fileOutput))) {
                fileWriter.write(result);
            } catch (IOException exception) {
                System.out.println("Error: File writing failed! " + exception.getMessage());
            }
        }
        System.out.println("Executed. Bye Bye!");
    }

    private static String encodeDecode() {
        switch (algorithm) {
            case "unicode":
                return engine.byUnicode();
            case "shift":
                return engine.byShifting();
            default:
                System.out.println("Algorithm invalid.");
                return null;
        }
    }

    private static String fileReader(String path) throws IOException {
        return new String(Files.readAllBytes(Path.of(path)));
    }

    private static void cliInputHandler(String[] args) {
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-mode":
                        action = args[i + 1];
                        break;
                    case "-key":
                        key = Integer.parseInt(args[i + 1]);
                        break;
                    case "-data":
                        data = args[i + 1];
                        break;
                    case "-in":
                        if (data.isEmpty()) {
                            data = fileReader(args[i + 1]);
                        }
                        break;
                    case "-out":
                        fileOutput = args[i + 1];
                        break;
                    case "-alg":
                        algorithm = args[i + 1];
                        break;

                }
            }
        } catch (NullPointerException exception) {
            System.out.println("Error: Keywords invalid. " + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("Error: File path invalid. " + exception.getMessage());
            exception.printStackTrace();
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
    private static void scannerInputHandler() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isOk = false;
            while (!isOk) {
                System.out.println("Set details (action, algorithm , text, key, text from file, write to file, ok):");
                String command = scanner.nextLine();
                switch (command.toUpperCase()) {
                    case "ACTION":
                        System.out.println("Enter action (enc, dec):");
                        action = scanner.nextLine();
                        break;
                    case "TEXT":
                        System.out.println("Enter new text:");
                        data = scanner.nextLine();
                        break;
                    case "TEXT FROM FILE":
                        System.out.println("Enter file path (the previous text input will be clear):");
                        data = fileReader(scanner.nextLine());
                        break;
                    case "KEY":
                        System.out.println("Enter key:");
                        key = Integer.parseInt(scanner.nextLine());
                        break;
                    case "WRITE TO FILE":
                        System.out.println("Enter output file path:");
                        fileOutput = scanner.nextLine();
                        break;
                    case "ALGORITHM":
                        System.out.println("Enter algorithm (unicode, shift):");
                        algorithm = scanner.nextLine();
                        break;
                    case "OK":
                        isOk = true;
                        System.out.println("Executing...");
                        break;
                    default:
                        System.out.println("Action invalid. Please try again.");
                }
            }
        } catch (IOException exception) {
            System.out.println("Error: File path invalid. " + exception.getMessage());
            exception.printStackTrace();
        } catch (NumberFormatException exception) {
            System.out.println("Error: Key invalid. " + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}
