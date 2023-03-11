package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of preparation of raffle queue
 */
public class PrepareRaffleCommand extends BaseCommand {
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore ToyStore instance
     * @param view     View interface implementation
     */
    public PrepareRaffleCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        }

        this.toyStore.AddToysToQueue();
        this.view.ShowMsg(Msg.allToysAddedToQueueMsg, 1);
        return true;
    }

    @Override
    public String toString() {
        return "Prepare a raffle";
    }
}
