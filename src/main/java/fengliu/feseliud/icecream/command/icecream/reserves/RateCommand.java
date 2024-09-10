package fengliu.feseliud.icecream.command.icecream.reserves;

import fengliu.feseliud.icecream.command.BaseCommand;

public class RateCommand extends BaseCommand {
    public RateCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        return false;
    }
}
