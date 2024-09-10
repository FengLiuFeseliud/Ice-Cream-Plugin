package fengliu.feseliud.icecream.command;

import fengliu.feseliud.icecream.command.icecream.ReloadCommand;
import fengliu.feseliud.icecream.command.icecream.ReservesCommand;
import fengliu.feseliud.icecream.command.icecream.reserves.RateCommand;
import fengliu.feseliud.icecream.command.icecream.reserves.SubmitCommand;
import fengliu.feseliud.icecream.command.icecream.reserves.TakeCommand;

/**
 * 根指令 icecream
 */
public class IceCreamRootCommands implements IRootCommands {
    public static final String COMMAND_NAME = "icecream";

    public static final ReloadCommand RELOAD = new ReloadCommand("reload");
    public static final ReservesCommand RESERVES = new ReservesCommand("reserves");
    public static final SubmitCommand RESERVES_SUBMIT = new SubmitCommand("reserves.submit");
    public static final TakeCommand RESERVES_TAKE = new TakeCommand("reserves.take");
    public static final RateCommand RESERVES_RATE = new RateCommand("reserves.rate");

    @Override
    public String getRootCommandsName() {
        return IceCreamRootCommands.COMMAND_NAME;
    }

    @Override
    public boolean runRootCommand() {
        return false;
    }
}
