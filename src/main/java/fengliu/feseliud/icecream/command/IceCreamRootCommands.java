package fengliu.feseliud.icecream.command;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.command.icecream.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

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
    public static final MenuCommand MENU = new MenuCommand("menu");
    public static final NpcCommand NPC = new NpcCommand("npc");

    public IceCreamRootCommands() {
        super(COMMAND_NAME);
    }

    public static String getCommandName(ICommand command){
        return COMMAND_NAME + " " + command.getCommandNane();
    }

    public static void runTemporaryPrivileges(Player player, ICommand command, String args){
        String permission = COMMAND_NAME + "." + command.getCommandNane();
        if (player.hasPermission(permission)){
            Bukkit.dispatchCommand(player, IceCreamRootCommands.getCommandName(command) + " " + args);
            return;
        }

        PermissionAttachment attachment = player.addAttachment(IceCreamPlugin.instance);
        attachment.setPermission(permission, true);
        Bukkit.dispatchCommand(player, IceCreamRootCommands.getCommandName(command) + " " + args);
        attachment.setPermission(permission, false);
    }

    @Override
    public boolean onRnu() {
        return false;
    }

    @Override
    public boolean canTab(IRootCommands rootCommands) {
        return true;
    }
}
