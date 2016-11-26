/**
 * @author Kempenich Florian
 */
public class ServerMain {


    public static void main(String[] args) {

        Server server = new Server();

        server.start(52960);

        server.printMessageStdout();

        server.closeConnection();
    }
}
