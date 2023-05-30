import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Create a server socket on port 4444
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        // Accept client connections and start a new thread for each one
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostName());

                // Create input and output streams for the client socket
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Create a new thread to handle the client connection
                Thread t = new ServerThread(clientSocket, out, in);
                t.start();
            } catch (IOException e) {
                System.err.println("Error handling client connection.");
            }
        }
    }
}

class ServerThread extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ServerThread(Socket socket, PrintWriter out, BufferedReader in) {
        this.clientSocket = socket;
        this.out = out;
        this.in = in;
    }

    public void run() {
        try {
            // Read the client's clock time
            long clientTime = Long.parseLong(in.readLine());
            System.out.println("Received time from " + clientSocket.getInetAddress().getHostName() + ": " + clientTime);

            // Calculate the server's clock time
            long serverTime = System.currentTimeMillis();
            System.out.println("Server time: " + serverTime);

            // Calculate the time offset
            long offset = serverTime - clientTime;
            System.out.println("Offset: " + offset);

            // Send the time offset to the client
            out.println(offset);

            // Close the socket and streams
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error handling client connection.");
        }
    }
}
