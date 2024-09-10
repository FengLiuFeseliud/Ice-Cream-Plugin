package fengliu.feseliud.icecream.command.icecream.reserves;

import fengliu.feseliud.icecream.command.PlayerCommand;

public class SubmitCommand extends PlayerCommand {
    public SubmitCommand(String CommandLabel) {
        super(CommandLabel);
    }

    @Override
    public boolean onRnu() {
        return false;
    }
}
