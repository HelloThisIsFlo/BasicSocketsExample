package clientworker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class PrintInputClientWorkerFactory implements ClientWorkerFactory {

    @Override
    public ClientWorker getNewClientWorker(Socket socket) {
        return new PrintInputClientWorker(socket);
    }

    private static class PrintInputClientWorker extends ClientWorker {
        public PrintInputClientWorker(Socket socket) {
            super(socket);
        }

        @Override
        void handleConnection(BufferedReader socketInput, PrintWriter socketOutput) {
            try {
                while (true) {
                    String line = socketInput.readLine();
                    if (line != null) {
                        System.out.println("Received: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
