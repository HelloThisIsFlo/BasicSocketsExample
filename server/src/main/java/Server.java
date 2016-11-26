import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Kempenich Florian
 */
public class Server {

    ServerSocket socket;

    public void listen(int port) throws PortTakenException {

        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            throw new PortTakenException(port);
        }

    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: 11/26/2016 investigae why IOException could happen
        }
    }
}
