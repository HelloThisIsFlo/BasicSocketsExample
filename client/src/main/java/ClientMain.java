import java.io.*;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class ClientMain {

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public static void main(String[] args) {
        new ClientMain().run();
    }


    public void run() {
//        String line = readLineFromStdIn("Please enter text: ");


        initSocketAndWriterReader("localhost", 52960);

        String line;
        do {
            line = readLineFromStdIn("Type a line: ");
            out.println(line);
        } while (!line.isEmpty());

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readLineFromStdIn(String prompt) {
        System.out.println(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "ERROR";
        }
    }

    private void initSocketAndWriterReader(String address, int port) {
        try {
            socket = new Socket(address, port);
            initWriterAndReader();
        } catch (IOException e) {
            e.printStackTrace();
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
}
