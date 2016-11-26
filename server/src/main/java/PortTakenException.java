import java.io.IOException;

/**
 * @author Kempenich Florian
 */
public class PortTakenException extends IOException {

    public PortTakenException(int port) {
        super("This port is already taken: " + port);
    }
}
