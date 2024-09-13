package fengliu.feseliud.icecream.command;

import fengliu.feseliud.icecream.command.icecream.*;

/**
 * 根指令 icecream
 */
public class IceCreamRootCommands extends BaseRootCommands {
    public static final String COMMAND_NAME = "icecream";
    public static final SubmitCommand SUBMIT = new SubmitCommand("submit");
    public static final TakeCommand TAKE = new TakeCommand("take");
    public static final RateCommand RATE = new RateCommand("rate");
    public static final ItemCommand ITEM = new ItemCommand("item");
    public static final PayCommand PAY = new PayCommand("pay");
    public static final ReloadCommand RELOAD = new ReloadCommand("reload");

    public IceCreamRootCommands() {
        super(COMMAND_NAME);
    }

    @Override
    public boolean onRnu() {
        return false;
    }
}
