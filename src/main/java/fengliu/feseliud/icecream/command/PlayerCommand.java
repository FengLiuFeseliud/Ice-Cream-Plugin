package fengliu.feseliud.icecream.command;

import org.bukkit.entity.Player;

/**
 *  仅玩家可用的指令
 */
public abstract class PlayerCommand extends BaseCommand{
    public PlayerCommand(String name) {
        super(name);
    }

    @Override
    public boolean canRun(IRootCommands rootCommands) {
        return super.canRun(rootCommands) && this.sender instanceof Player;
    }
}
