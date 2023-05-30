import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Define the remote interface
interface HangmanInterface extends Remote {
    String startGame() throws RemoteException;
    String guessLetter(char letter) throws RemoteException;
}
