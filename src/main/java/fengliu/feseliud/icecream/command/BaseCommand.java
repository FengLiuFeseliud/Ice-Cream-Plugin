package fengliu.feseliud.icecream.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 基础指令
 */
public abstract class BaseCommand implements ICommand{
    public final String name;
    protected CommandSender sender;
    protected Command command;
    protected String[] args;

    /**
     * 仅注册用，创建将使用 {@link ICommand#initCommand(CommandSender, Command, String[])}
     * @param name 子节点指令名 更多子节点使用 "." 分割
     */
    public BaseCommand(String name){
        this(name, null, null, null);
    }

    public BaseCommand(String name, CommandSender sender, Command command, String[] args){
        this.name = name;
        this.sender = sender;
        this.command = command;
        this.args = args;
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
    public boolean canRun() {
        return this.nameEquals();
    }

    @Override
    public void initCommand(CommandSender sender, Command command, String[] args) {
        this.sender = sender;
        this.command = command;
        this.args = args;
    }
}
