package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of adding toys from external storage
 */
public class AddToysCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public AddToysCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (this.toyStore.LoadData()) {
            this.view.ShowMsg(Msg.toysAddedMsg, 1);
            return true;
        }
        this.view.ShowMsg(Msg.toysNotAddedMsg, 1);
        return false;
    }

    @Override
    public String toString() {
        return "Add new toys to store (from file)";
    }
    
}
