package toys_raffle.commands;

import toys_raffle.exceptions.EmptyQueueException;
import toys_raffle.models.Toy;
import toys_raffle.models.ToyStore;
import toys_raffle.views.View;

/**
 * Class that implements the command of geting a toy from queue
 */
public class GetToyCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public GetToyCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        try {
            Toy toy = this.toyStore.Get();
            this.view.ShowMsg(toy.toString(), 1);
        } catch (EmptyQueueException e) {
            this.view.ShowMsg(e.getMessage(), 1);
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Get a toy from queue";
    }
    
}
