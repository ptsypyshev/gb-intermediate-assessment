package toys_raffle.commands;

import toys_raffle.models.ToyStore;
import toys_raffle.views.Msg;
import toys_raffle.views.View;

/**
 * Class that implements the command of changing toy's chance
 */
public class ChangeToyChanceCommand extends BaseCommand{
    /**
     * Default constructor for Command instance
     * 
     * @param toyStore  ToyStore instance
     * @param view      View interface implementation
     */
    public ChangeToyChanceCommand(ToyStore toyStore, View view) {
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

        int chance = this.view.getPercent(Msg.getToyChanceMsg);
        this.toyStore.ChangeChance(id, chance);
        this.view.ShowMsg(Msg.changeToyChanceMsg, 1);
        return true;
    }

    @Override
    public String toString() {
        return "Change a toy's chance value";
    }
    
}
