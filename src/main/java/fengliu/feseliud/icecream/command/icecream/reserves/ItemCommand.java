package fengliu.feseliud.icecream.command.icecream.reserves;

import fengliu.feseliud.icecream.command.PlayerCommand;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.message.Message;
import fengliu.feseliud.icecream.message.ReserveInfoMessageKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * /icecream reserves item<br\>
 * 将手上的物品设置为准备金物品
 */
public class ItemCommand extends PlayerCommand {
    public ItemCommand(String CommandLabel) {
        super(CommandLabel);
    }

    @Override
    public boolean canRun() {
        return super.canRun() || !((Player) this.sender).getInventory().getItemInMainHand().getType().isAir();
    }

    @Override
    public boolean onRnu() {
        ItemStack stack = ((Player) this.sender).getInventory().getItemInMainHand();
        String itemId = stack.getType().toString();
        PluginConfigs.RESERVES.setAndSave("reserveItems", itemId);
        Message.send(
                this.sender,
                ReserveInfoMessageKey.COMMAND_SET_RESERVE_ITEMS,
                stack.getTranslationKey(),
                itemId
        );
        return true;
    }
}
