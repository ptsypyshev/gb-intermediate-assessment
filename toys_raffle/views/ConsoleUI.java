package toys_raffle.views;

import java.util.Map;
import java.util.Scanner;

import toys_raffle.commands.Command;

/**
 * Implementation of View interface
 */
public class ConsoleUI implements View {
    private final static int ZERO = 0;
    private Scanner scan;
    private Map<Integer, Command> commands;

    /**
     * Default constructor for ConsoleUI class
     * @param scan      Scanner object (java.util.Scanner)
     * @param commands  Map with commands
     */
    public ConsoleUI(Scanner scan, Map<Integer,Command> commands) {
        this.scan = scan;
        this.commands = commands;
    }

    @Override
    public void MainMenu() {
        System.out.println(Msg.chooseActionsMsg);
        for (Map.Entry<Integer, Command> entry : this.commands.entrySet()) {
            if (entry.getKey() == ZERO) {
                continue;
            }
            System.out.println(String.format("%d - %s", entry.getKey(), entry.getValue()));
        }
        if (this.commands.containsKey(ZERO)) {
            System.out.println(String.format("%d - %s", ZERO, this.commands.get(ZERO)));
        }
    }

    @Override
    public int getInt(String message) {
        while (true) {
            System.out.print(message);
            if (scan.hasNextInt()) {
                return scan.nextInt();
            }
            System.out.println(Msg.incorrectInputMsg);
            scan.next();
        }
    }

    @Override
    public int getPercent(String message) {
        while (true) {
            int percent = this.getInt(message);

            if (percent >= 0 && percent <= 100) {
                return percent;
            }            
            System.out.println(Msg.incorrectInputMsg);
        }
    }

    @Override
    public String getString(String message) {
        System.out.print(message);
        return scan.next();
    }

    @Override
    public boolean getBoolean(String message) {
        System.out.print(message);
        if (scan.next().toLowerCase().equals("y")) {
            return true;
        }
        return false;
    }    
    
    @Override
    public void ShowMsg(String message) {
        System.out.println(message);
    }

    @Override
    public void ShowMsg(String message, int newLines) {
        System.out.println(message);
        System.out.print("\n".repeat(newLines));
    }
}
