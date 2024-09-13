package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.PlayerCommand;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.config.ReservesConfig;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * /icecream reserves item<br\>
 * 将手上的物品设置为准备金物品
 */
public class ItemCommand extends PlayerCommand {
    public ItemCommand(String name) {
        super(name);
    }

    @Override
    public boolean canRun() {
        return super.canRun() && !((Player) this.sender).getInventory().getItemInMainHand().getType().isAir();
    }

    @Override
    public boolean onRnu() {
        ItemStack stack = ((Player) this.sender).getInventory().getItemInMainHand();
        String itemId = stack.getType().toString();
        PluginConfigs.RESERVES.setAndSave(ReservesConfig.ITEM, itemId);
        ReserveMessage.COMMAND_SET_RESERVE_ITEMS.send(this.sender, stack.getTranslationKey(), itemId);
        return true;
    }
}
