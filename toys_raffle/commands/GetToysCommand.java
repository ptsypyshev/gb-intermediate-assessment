package toys_raffle.commands;

import toys_raffle.exceptions.EmptyQueueException;
import toys_raffle.models.Toy;
import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of geting all toys from queue
 */
public class GetToysCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public GetToysCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (this.toyStore.GetQueueLen() == 0) {
            this.view.ShowMsg(Msg.emptyQueueMsg, 1);
            return false;
        }
        
        while (this.toyStore.GetQueueLen() > 0) {
            try {
                Toy toy = this.toyStore.Get();
                this.view.ShowMsg(toy.toString());
            } catch (EmptyQueueException e) {
                this.view.ShowMsg(e.getMessage());
                return false;
            }
        }
        this.view.ShowMsg("");
        return true;
    }

    @Override
    public String toString() {
        return "Get all toys from queue";
    }
    
}
