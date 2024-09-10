package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.BaseCommand;

public class ReservesCommand extends BaseCommand {
    public ReservesCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        return false;
    }
}
