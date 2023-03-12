package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of removing a toy from the store
 */
public class RemoveToyCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public RemoveToyCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        }
        
        int id = this.view.getInt(Msg.getToyIDMsg);
        if (!this.toyStore.hasToyID(id)) {
            this.view.ShowMsg(Msg.notExistsIDMsg, 1);
            return false;
        }

        this.toyStore.RemoveByID(id);
        this.view.ShowMsg(Msg.toyRemovedMsg, 1);
        return true;
    }
    
    @Override
    public String toString() {
        return "Remove a toy from store";
    }
}
