import clientworker.ClientWorkerFactory;
import util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class);

    ServerSocket serverSocket;
    ClientWorkerFactory clientWorkerFactory;

    public Server(ClientWorkerFactory clientWorkerFactory) {
        this.clientWorkerFactory = clientWorkerFactory;
    }

    public void start(int port) throws PortTakenException {
        initServer(port);
        acceptConnections();
    }

    private void initServer(int port) throws PortTakenException {
        try {
            serverSocket = new ServerSocket(port);
            LOGGER.info("Listen on port: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new PortTakenException(port);
        }
    }

    private void acceptConnections() {
        try {
            while (true) {
                Socket socket = serverSocket.accept(); //blocks until new connection
                handleNewConnection(socket);
            }
        } catch (IOException e) {
            LOGGER.error("Accept failed: " + serverSocket.getLocalPort());
        } finally {
            tryToCloseServerSocket();
        }
    }

    private void handleNewConnection(Socket socket) {
        Thread handleConnectionThread = new Thread(clientWorkerFactory.getNewClientWorker(socket));
        handleConnectionThread.start();
        LOGGER.info("Connection accepted");
    }

    private void tryToCloseServerSocket() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
