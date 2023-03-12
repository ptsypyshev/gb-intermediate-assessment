package toys_raffle.loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementation of Logger interface
 */
public class SimpleLogger implements Logger{
    private String filepathString;

    /**
     * Default constuctor for SimpleLogger instance
     * @param filepathString    path to log file
     */
    public SimpleLogger(String filepathString) {
        this.filepathString = filepathString;
    }

    @Override
    public void Log(String message) {
        File file = new File(this.filepathString);
        
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(String.format("%s\n", message));
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
