package fengliu.feseliud.icecream.command.icecream.reserves;

import fengliu.feseliud.icecream.command.PlayerCommand;

public class TakeCommand extends PlayerCommand {
    public TakeCommand(String CommandLabel) {
        super(CommandLabel);
    }

    @Override
    public boolean onRnu() {
        return false;
    }
}
