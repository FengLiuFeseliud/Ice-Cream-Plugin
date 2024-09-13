package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.PlayerCommand;
import fengliu.feseliud.icecream.config.ReservesConfig;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import fengliu.feseliud.icecream.sql.ReservesSqlConnection;
import me.yic.xconomy.api.XConomyAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class SubmitCommand extends PlayerCommand {
    public SubmitCommand(String name) {
        super(name);
    }

    private String changeBalance(int count, Player player){
        XConomyAPI xapi = new XConomyAPI();
        BigDecimal balance = BigDecimal.valueOf(count / ReservesConfig.ITEM_RATE.get());
        xapi.changePlayerBalance(player.getUniqueId(), player.getName(), balance, true);
        return xapi.getdisplay(balance);
    }

    private void forInventoryReserveItemsStack(Material item, PlayerInventory inventory, Consumer<ItemStack> setStack){
        for (ItemStack stack : inventory.getStorageContents()) {
            if (stack == null){
                continue;
            }

            if (!stack.getType().equals(item)){
                continue;
            }
            setStack.accept(stack);
        }
    }

    private void subInventoryALLReserveItemsStack(Material item, PlayerInventory inventory, Player player){
        AtomicInteger count = new AtomicInteger();
        this.forInventoryReserveItemsStack(item, inventory, itemStack -> {
            count.addAndGet(itemStack.getAmount());
            inventory.remove(itemStack);
        });

        ReservesSqlConnection.instance.subReservesItem(count.get(), player);
        ReserveMessage.COMMAND_SUBMIT.send(this.sender, String.valueOf(count.get()), this.changeBalance(count.get(), player));
    }

    private void subInventoryReserveItemsStack(Material item, PlayerInventory inventory, Player player, int subCount){
        AtomicInteger count = new AtomicInteger();
        this.forInventoryReserveItemsStack(item, inventory, itemStack -> count.addAndGet(itemStack.getAmount()));
        String balanceStr = this.changeBalance(Math.min(count.get(), subCount), player);

        count.set(subCount);
        this.forInventoryReserveItemsStack(item, inventory, itemStack -> {
            if (count.get() == 0){
                return;
            }

            int amount = itemStack.getAmount();
            if (amount <= count.get()){
                itemStack.setAmount(0);
                count.set(count.get() - amount);
                return;
            }

            int newAmount = amount - count.get();
            itemStack.setAmount(newAmount);
            count.set(0);
        });

        ReservesSqlConnection.instance.subReservesItem(subCount - count.get(), player);
        ReserveMessage.COMMAND_SUBMIT.send(this.sender, String.valueOf(subCount - count.get()), balanceStr);
    }

    @Override
    public boolean onRnu() {
        if (!(this.sender instanceof Player player)){
            return false;
        }

        if (this.args.length == 1){
            return false;
        }

        Material item = ReservesConfig.ITEM.getItem();
        if (item == null || item.isAir()) {
            ReserveMessage.COMMAND_NOT_RESERVE_ITEMS.send(player);
            return true;
        }

        PlayerInventory inventory = player.getInventory();
        if (!inventory.contains(item)){
            return true;
        }

        if (this.args[1].equals("all")){
            this.subInventoryALLReserveItemsStack(item, inventory, player);
            return true;
        }

        this.subInventoryReserveItemsStack(item, inventory, player, Integer.parseInt(this.args[1]));
        return true;
    }
}
