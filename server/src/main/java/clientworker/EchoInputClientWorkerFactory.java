package clientworker;

import util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class EchoInputClientWorkerFactory implements ClientWorkerFactory {
    private static final Logger LOGGER = Logger.getLogger(EchoInputClientWorkerFactory.class);

    @Override
    public ClientWorker getNewClientWorker(Socket socket) {
        return new EchoInputClientWorker(socket);
    }

    private static class EchoInputClientWorker extends ClientWorker {
        public EchoInputClientWorker(Socket socket) {
            super(socket);
        }

        @Override
        void handleConnection(BufferedReader socketInput, PrintWriter socketOutput) {
            try {
                while (true) {
                    String line = socketInput.readLine();
                    if (line != null) {
                        LOGGER.info(" Echoed message: " + line);
                        socketOutput.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
