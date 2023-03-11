package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the exit command
 */
public class ExitCommand extends BaseCommand {
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public ExitCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (this.view.getBoolean(Msg.askSaveDataMsg)) {
            this.toyStore.SaveData();
        }
        this.view.ShowMsg(Msg.byeMsg);
        return true;
    }

    @Override
    public String toString() {
        return "Exit";
    }
    
}
