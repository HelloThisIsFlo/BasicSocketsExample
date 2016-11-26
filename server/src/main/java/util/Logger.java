package util;

import java.util.logging.Level;

/**
 * @author Kempenich Florian
 */
public class Logger {

    private final java.util.logging.Logger logger;

    public Logger(String name) {
        logger = java.util.logging.Logger.getLogger(name);
    }

    public static Logger getLogger(Class clazz) {
        return new Logger(clazz.getSimpleName());
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }
}
