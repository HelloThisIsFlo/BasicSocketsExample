/**
 * @author Kempenich Florian
 */
public class ServerMain {


    public static void main(String[] args) {

        Server server = new Server();

        server.start();

        server.printMessageStdout();

        server.closeConnection();
    }
}
