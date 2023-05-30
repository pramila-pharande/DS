import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the remote object
            HangmanInterface hangman = new HangmanImpl();

            // Bind the remote object's stub in the registry
            Naming.rebind("HangmanServer", hangman);

            System.out.println("Hangman Server is running...");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
