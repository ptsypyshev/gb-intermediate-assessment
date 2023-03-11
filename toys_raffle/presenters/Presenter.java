package toys_raffle.presenters;

import java.util.Map;

import toys_raffle.commands.Command;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Presenter class of MVP
 */
public class Presenter {
    private View view;
    private Map<Integer, Command> commands;

    /**
     * Default constructor for Presenter instance
     * @param view      View implementation
     * @param commands  Commands map
     */
    public Presenter(View view, Map<Integer,Command> commands) {
        this.view = view;
        this.commands = commands;
    }
    
    /**
     * Runs the application presentation
     */
    public void Run() {
        this.view.ShowMsg(Msg.welcomeMsg);
        int cmd = -1;

        while (cmd != 0) {
            this.view.MainMenu();
            cmd = this.view.getInt(Msg.getIndexMsg);
            if (this.commands.containsKey(cmd)) {
                this.commands.get(cmd).execute();
            } else {
                this.view.ShowMsg(Msg.notImplementedMsg);
            }
        }
    }
}
