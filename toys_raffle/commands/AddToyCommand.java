package toys_raffle.commands;

import toys_raffle.models.Toy;
import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of adding a toy from user's input
 */
public class AddToyCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public AddToyCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        int id = this.view.getInt(Msg.getToyIDMsg);
        if (this.toyStore.hasToyID(id)) {
            this.view.ShowMsg(Msg.existsIDMsg);
            return false;
        }

        String name = this.view.getString(Msg.getToyNameMsg);
        int qty = this.view.getInt(Msg.getToyQtyMsg);
        int chance = this.view.getPercent(Msg.getToyChanceMsg);
        Toy toy = new Toy(id, name, qty, chance);
        this.toyStore.Put(toy);
        this.view.ShowMsg(Msg.toyAddedMsg, 1);
        return false;
    }

    @Override
    public String toString() {
        return "Add new toy to store (manual)";
    }
    
}
