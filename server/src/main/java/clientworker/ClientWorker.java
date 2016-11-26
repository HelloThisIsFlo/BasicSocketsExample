package clientworker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public abstract class ClientWorker implements Runnable {

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    protected ClientWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            initInputOutput();
            handleConnection(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tryToCloseSocket();
        }
    }

    private void tryToCloseSocket() {
        try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void initInputOutput() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    abstract void handleConnection(BufferedReader socketInput, PrintWriter socketOutput);
}
