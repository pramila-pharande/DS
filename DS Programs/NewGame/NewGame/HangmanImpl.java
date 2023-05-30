import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Implement the remote interface
class HangmanImpl extends UnicastRemoteObject implements HangmanInterface {
    private List<String> words;
    private String word;
    private String guessedWord;
    private int attemptsLeft;

    public HangmanImpl() throws RemoteException {
        words = new ArrayList<>();
        words.add("hangman");
        words.add("computer");
        words.add("programming");
        // Add more words here if needed

        word = "";
        guessedWord = "";
        attemptsLeft = 6;
        selectRandomWord();
    }

    private void selectRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        word = words.get(index);
    }

    public String startGame() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append("_");
        }
        guessedWord = sb.toString();
        return guessedWord;
    }

    public String guessLetter(char letter) throws RemoteException {
        StringBuilder sb = new StringBuilder(guessedWord);
        boolean letterFound = false;
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(word.charAt(i)) == Character.toLowerCase(letter)) {
                sb.setCharAt(i, word.charAt(i));
                letterFound = true;
            }
        }
        if (!letterFound) {
            attemptsLeft--;
        }
        guessedWord = sb.toString();

        if (guessedWord.equals(word)) {
            selectRandomWord(); // Select a new random word for the next game
            return "Congratulations! You guessed the word: " + word;
        } else if (attemptsLeft == 0) {
            selectRandomWord(); // Select a new random word for the next game
            return "Game over! The word was: " + word;
        } else {
            return "Attempts left: " + attemptsLeft + "\nGuessed word: " + guessedWord;
        }
    }
}

