package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.BaseCommand;

public class ReloadCommand extends BaseCommand {
    public ReloadCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        return false;
    }
}
