import clientworker.EchoInputClientWorkerFactory;

/**
 * @author Kempenich Florian
 */
public class ServerMain {


    public static void main(String[] args) {

        Server server = new Server(new EchoInputClientWorkerFactory());

        try {
            server.start(1234);
        } catch (PortTakenException e) {
            e.printStackTrace();
        }
    }
}
