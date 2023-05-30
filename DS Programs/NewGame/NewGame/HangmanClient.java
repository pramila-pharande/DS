import java.rmi.*;
import java.util.Scanner;

public class HangmanClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the registry
            HangmanInterface hangman = (HangmanInterface) Naming.lookup("rmi://localhost/HangmanServer");

            // Start the game
            String guessedWord = hangman.startGame();
            System.out.println("Hangman game started!");
            System.out.println("Guessed word: " + guessedWord);

            // Play the game
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter a letter: ");
                char letter = scanner.nextLine().charAt(0);

                String result = hangman.guessLetter(letter);
                System.out.println(result);

                if (result.startsWith("Congratulations") || result.startsWith("Game over")) {
                    break;
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

