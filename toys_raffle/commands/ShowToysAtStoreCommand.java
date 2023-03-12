package toys_raffle.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import toys_raffle.models.Toy;
import toys_raffle.models.ToyStore;
import toys_raffle.views.View;

/**
 * Class that implements the command of showing all toys at the store
 */
public class ShowToysAtStoreCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public ShowToysAtStoreCommand(ToyStore toyStore, View view) {
        super(toyStore, view);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            return false;
        };

        List<Toy> toys = new ArrayList<>();        
        for (Toy toy : this.toyStore.getToys().values()) {
            toys.add(toy);
        }
        Collections.sort(toys);
        for (Toy toy : toys) {
            this.view.ShowMsg(toy.toString());
        }
        this.view.ShowMsg("");
        return false;
    }

    @Override
    public String toString() {
        return "Show toys at store";
    }    
}
