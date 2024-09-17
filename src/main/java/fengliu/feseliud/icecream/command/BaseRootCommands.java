package fengliu.feseliud.icecream.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class BaseRootCommands implements IRootCommands{
    public final String name;
    protected CommandSender sender;
    protected Command command;
    protected String[] args;

    /**
     * 仅注册用，创建将使用 {@link ICommand#initCommand(CommandSender, Command, String, String[])}
     */
    public BaseRootCommands(String name){
        this.name = name;
    }

    @Override
    public String getCommandNane() {
        return this.name;
    }

    @Override
    public String[] getArgs() {
        return this.args;
    }

    @Override
    public boolean canRun(IRootCommands rootCommands) {
        return this.nameEquals();
    }

    @Override
    public void initCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.command = command;
        this.args = args;
    }
}
