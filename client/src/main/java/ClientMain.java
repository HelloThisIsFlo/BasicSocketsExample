import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class ClientMain {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) {
        new ClientMain().run();
    }


    public void run() {
        initSocketAndWriterReader("localhost", 1234);
        initPrintSocketInputWorker();

        readUntilEmptyLine();

        closeSocket();
    }

    private void initPrintSocketInputWorker() {
        Thread copyInputThread = new Thread(new PrintSocketInputStreamWorker(socket));
        copyInputThread.start();
    }

    private void readUntilEmptyLine() {
        String line;
        do {
            line = readLineFromStdIn();
            out.println(line);
        } while (!line.isEmpty());
    }

    private String readLineFromStdIn() {
        System.out.println("Type a line: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
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
