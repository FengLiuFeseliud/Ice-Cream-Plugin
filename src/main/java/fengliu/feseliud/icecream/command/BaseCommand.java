package fengliu.feseliud.icecream.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 基础指令
 */
public abstract class BaseCommand implements ICommand{
    public final String name;
    protected CommandSender sender;
    protected Command command;
    protected String[] args;

    /**
     * 仅注册用，创建将使用 {@link ICommand#initCommand(CommandSender, Command, String, String[])}
     * @param name 子节点指令名 更多子节点使用 "." 分割
     */
    public BaseCommand(String name){
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
        if (!(this.sender instanceof Player player)){
            return this.nameEquals();
        }

        return this.nameEquals() && player.hasPermission(rootCommands.getCommandNane() + "." + this.getCommandNane());
    }

    @Override
    public boolean canTab(IRootCommands rootCommands) {
        if (sender instanceof Player player){
            return player.hasPermission(rootCommands.getCommandNane() + "." + this.getCommandNane());
        }
        return true;
    }

    @Override
    public void initCommand(CommandSender sender, Command command, String label, String[] args) {
        this.sender = sender;
        this.command = command;
        this.args = args;
    }
}
