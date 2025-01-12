package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.IRootCommands;
import fengliu.feseliud.icecream.command.PlayerCommand;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.config.ReservesConfig;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * /icecream reserves item<br\>
 * 将手上的物品设置为准备金物品
 */
public class ItemCommand extends PlayerCommand {
    public ItemCommand(String name) {
        super(name);
    }

    @Override
    public boolean canRun(IRootCommands rootCommands) {
        return super.canRun(rootCommands) && !((Player) this.sender).getInventory().getItemInMainHand().getType().isAir();
    }

    @Override
    public boolean onRnu() {
        ItemStack stack = ((Player) this.sender).getInventory().getItemInMainHand();
        String itemId = stack.getType().toString();
        PluginConfigs.RESERVES.setAndSave(ReservesConfig.ITEM, itemId);
        ItemMeta itemMeta = stack.getItemMeta();
        if (itemMeta == null){
            ReserveMessage.COMMAND_SET_RESERVE_ITEMS.send(this.sender, stack.getTranslationKey(), itemId);
            return true;
        }
        ReserveMessage.COMMAND_SET_RESERVE_ITEMS.send(this.sender, stack.getItemMeta().getDisplayName(), itemId);
        return true;
    }
}
