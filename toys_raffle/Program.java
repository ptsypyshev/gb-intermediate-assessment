package toys_raffle;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import toys_raffle.commands.*;
import toys_raffle.loggers.Logger;
import toys_raffle.loggers.SimpleLogger;
import toys_raffle.models.ToyStore;
import toys_raffle.presenters.Presenter;
import toys_raffle.storages.Storage;
import toys_raffle.storages.TxtStorage;
import toys_raffle.views.ConsoleUI;

public class Program {
    public final static String DEFAULT_DATAPATH = "toys.txt";
    public final static String DEFAULT_LOGPATH = "result.log";
    public final static String CWD = "user.dir";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String dataPath = Paths.get(System.getProperty(CWD), DEFAULT_DATAPATH).toString();
        String logPath = Paths.get(System.getProperty(CWD), DEFAULT_LOGPATH).toString();
        Storage storage = new TxtStorage(dataPath);
        Logger logger = new SimpleLogger(logPath);        
        
        ToyStore toyStore = new ToyStore(storage, logger);
        Map<Integer, Command> commands = new HashMap<>();
        ConsoleUI view = new ConsoleUI(scan, commands);
        
        commands.put(1, new AddToyCommand(toyStore, view));
        commands.put(2, new AddToysCommand(toyStore, view));
        commands.put(3, new ChangeToyChanceCommand(toyStore, view));
        commands.put(4, new RemoveToyCommand(toyStore, view));
        commands.put(5, new RemoveToysCommand(toyStore, view));
        commands.put(6, new ShowToysAtStoreCommand(toyStore, view));
        commands.put(7, new PrepareRaffleCommand(toyStore, view));
        commands.put(8, new GetToyCommand(toyStore, view));
        commands.put(9, new GetToysCommand(toyStore, view));
        commands.put(0, new ExitCommand(toyStore, view));
        
        Presenter presenter = new Presenter(view, commands);
        presenter.Run();
        scan.close();
    }
}
