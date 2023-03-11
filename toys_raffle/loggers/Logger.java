package toys_raffle.loggers;

/**
 * Logger interface
 */
public interface Logger {
    /**
     * Method that should be used to write some message to log file
     * @param message   message to be logged
     */
    public void Log(String message);
}
