import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class Server {

    ServerSocket serverSocket;
    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public void start(int port) {
        try {
            listen(port);
            accept();
            initWriterAndReader();
        } catch (PortTakenException e) {
            System.out.println("Port taken: " + port);
        }
    }

    private void listen(int port) throws PortTakenException {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listen on port: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new PortTakenException(port);
        }

    }

    private void initWriterAndReader() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void accept() {
        try {
            socket = serverSocket.accept();
            System.out.println("Socket opened");
        } catch (IOException e) {
            System.out.println("Accept failed: " + serverSocket.getLocalPort());
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            socket.close();
            serverSocket.close();
            System.out.println("Closed serverSocket & socket");
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 11/26/2016 investigate why IOException could happen
        }
    }

    public void start() {
        listen();
        accept();
        initWriterAndReader();
    }
    
    public void echoMessage() {
    }
    
    public void printMessageStdout() {
        while (true) {
            try {
                String line = in.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listen() {
        try {
            listen(0);
        } catch (PortTakenException e) {
            throw new IllegalStateException("No port available on system");
        }
    }
}
