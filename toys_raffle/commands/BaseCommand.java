package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Abstract Class as base for other commands
 */
public abstract class BaseCommand implements Command {
    protected ToyStore toyStore;
    protected View view;

    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    protected BaseCommand(ToyStore toyStore, View view) {
        this.toyStore = toyStore;
        this.view = view;
    }

    @Override
    public boolean execute() {
        if (this.toyStore.getToys().isEmpty()) {
            this.view.ShowMsg(Msg.emptyStoreMsg, 1);
            return false;
        }
        return true;
    }
}
