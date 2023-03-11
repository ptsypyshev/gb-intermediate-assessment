package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of removing toys from the store
 */
public class RemoveToysCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public RemoveToysCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        };

        if (this.view.getBoolean(Msg.askRemoveDataMsg)) {
            this.toyStore.RemoveAll();
            this.view.ShowMsg(Msg.allToysRemovedMsg, 1);
            return true;
        }
        this.view.ShowMsg(Msg.cancelledMsg, 1);
        return false;
    }

    @Override
    public String toString() {
        return "Remove all toys from the store";
    }
}
