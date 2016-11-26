import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class PrintSocketInputStreamWorker implements Runnable {

    private Socket socket;
    private BufferedReader reader;

    public PrintSocketInputStreamWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            initReader();
            copyInputToStdout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyInputToStdout() throws IOException {
        String line;
        while (true) {
            line = reader.readLine();
            if (line != null) {
                System.out.println("RECEIVED MESSAGE: " + line);
            }
        }
    }

    private void initReader() throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
