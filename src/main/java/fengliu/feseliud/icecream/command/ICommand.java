package fengliu.feseliud.icecream.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * 指令对像
 */
public interface ICommand {
    /**
     * 获取指令名
     * @return 指令名
     */
    String getCommandNane();

    /**
     * 获取指令参数
     * @return 指令参数
     */
    String[] getArgs();

    /**
     * 是否可以执行指令
     * @return true 可以
     */
    boolean canRun(IRootCommands rootCommands);

    /**
     * 执行指令
     * @return true 执行成功
     */
    boolean onRnu();

    boolean canTab(IRootCommands rootCommands);

    /**
     * 初始化指定数据指令
     */
    void initCommand(CommandSender sender, Command command, String label, String[] args);

    /**
     * 判断节点是否相同
     * @return 相同 true
     */
    default boolean nameEquals(){
        String[] names = this.getCommandNane().split("\\.");
        if (names.length > this.getArgs().length){
            return false;
        }

        for (int index = 0; index < names.length; index++){
            if (names[index].equals(this.getArgs()[index])){
                continue;
            }
            return false;
        }
        return true;
    }
}
