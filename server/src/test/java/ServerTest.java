import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Kempenich Florian
 */
public class ServerTest {

    Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server();
    }

    @After
    public void tearDown() throws Exception {
        server.closeConnection();
    }

    @Test
    public void portFree_ok() throws Exception {
        server.listen(getFreePort());

    }

    private int getFreePort() {
        try {
            // Port 0 will find any free port
            ServerSocket socket = new ServerSocket(0);
            int port = socket.getLocalPort();
            socket.close();
            return port;
        } catch (IOException e) {
            throw new IllegalStateException("No free port founds !!");
        }
    }

    @Test(expected = PortTakenException.class)
    public void portTaken_errorMessage() throws Exception {
        int port = getFreePort();
        server.listen(port);
        server.listen(port);
    }
}