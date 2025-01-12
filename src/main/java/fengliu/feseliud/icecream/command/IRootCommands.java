package fengliu.feseliud.icecream.command;

import fengliu.feseliud.icecream.config.message.Message;
import fengliu.feseliud.icecream.util.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 根指令<br/>
 * 在继承了该类的内部创建指令对像常量<br/>
 * 将被视为该根指令下的子指令<br/>
 * 可以被自动判断使用哪一个子指令<br/>
 * 可以被自动添加 tab 补全<br/>
 */
public interface IRootCommands extends ICommand, TabExecutor {
    /**
     * 注册根指令, 在 {@link JavaPlugin#onEnable()} 调用它注册
     */
    default void register(){
        PluginCommand cmd = Objects.requireNonNull(Bukkit.getPluginCommand(this.getCommandNane()));
        cmd.setExecutor(this);
        cmd.setTabCompleter(this);
    }

    /**
     * 判断可执行指令并执行
     */
    @Override
    default boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            this.initCommand(sender, command, label, args);
            if (!this.canRun(this)){
                return false;
            }
            return this.onRnu();
        }

        for (ICommand iCommand : ReflectionUtil.getObjects(ICommand.class, this.getClass())) {
            iCommand.initCommand(sender, command, label, args);
            if (!iCommand.canRun(this)){
                continue;
            }

            try {
                return iCommand.onRnu();
            } catch (Exception exception){
                Message.COMMAND_EXECUTION_ERROR.send(sender);
                Message.COMMAND_EXECUTION_CONSOLE_ERROR.warning(label + "." + iCommand.getCommandNane(), exception.getMessage());
                exception.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 添加指令补全
     */
    @org.jetbrains.annotations.Nullable
    @Override
    default List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> nodes = new ArrayList<>();
        ReflectionUtil.getObjects(ICommand.class, this.getClass()).forEach(iCommand -> {
            if (!iCommand.canTab(this)){
                return;
            }

            String[] names = iCommand.getCommandNane().split("\\.");
            if (names.length < args.length){
                return;
            }

            int index = args.length - 1;
            if (names[index].startsWith(args[index])){
                nodes.add(names[index]);
            }
        });
        return nodes;
    }
}
