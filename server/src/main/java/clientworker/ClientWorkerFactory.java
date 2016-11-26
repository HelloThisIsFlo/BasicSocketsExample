package clientworker;

import java.net.Socket;

/**
 * @author Kempenich Florian
 */
public interface ClientWorkerFactory {

    ClientWorker getNewClientWorker(Socket socket);

}
