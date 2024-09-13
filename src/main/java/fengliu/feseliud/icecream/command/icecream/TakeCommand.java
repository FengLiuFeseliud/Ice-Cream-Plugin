package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.PlayerCommand;
import fengliu.feseliud.icecream.config.ReservesConfig;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import fengliu.feseliud.icecream.sql.ReservesSqlConnection;
import fengliu.feseliud.icecream.util.ItemUtil;
import me.yic.xconomy.api.XConomyAPI;
import me.yic.xconomy.data.syncdata.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class TakeCommand extends PlayerCommand {
    public TakeCommand(String CommandLabel) {
        super(CommandLabel);
    }

    private void takeAll(Material item, Double reserveRate, PlayerData playerData, Player player, XConomyAPI xapi){
        int itemCount = (int) Math.floor(playerData.getBalance().doubleValue() * reserveRate);

        BigDecimal money = BigDecimal.valueOf(itemCount/ reserveRate);
        xapi.changePlayerBalance(playerData.getUniqueId(), player.getName(), money, false);

        ItemUtil.dropItem(item, itemCount, player);
        ReservesSqlConnection.instance.takeReservesItem(itemCount, money.doubleValue(), player);
        ReserveMessage.COMMAND_TAKE.send(player, String.valueOf(itemCount), xapi.getdisplay(money));
    }

    @Override
    public boolean onRnu() {
        if (!(this.sender instanceof Player player)){
            return false;
        }

        if (this.args.length == 1){
            return false;
        }

        XConomyAPI xapi = new XConomyAPI();
        PlayerData playerData = xapi.getPlayerData(player.getUniqueId());

        double reserveRate = ReservesConfig.RATE.get();
        Material item = ReservesConfig.ITEM.getItem();
        if (item == null || item.isAir()) {
            ReserveMessage.COMMAND_NOT_RESERVE_ITEMS.send(player);
            return true;
        }

        if (this.args[1].equals("all")) {
            this.takeAll(item, reserveRate, playerData, player, xapi);
            return true;
        }

        int itemCount = Integer.parseInt(this.args[1]);
        BigDecimal money = BigDecimal.valueOf(itemCount/ reserveRate);

        if (money.doubleValue() > playerData.getBalance().doubleValue()){
            this.takeAll(item, reserveRate, playerData, player, xapi);
            return true;
        }

        xapi.changePlayerBalance(playerData.getUniqueId(), player.getName(), money, false);

        ItemUtil.dropItem(item, itemCount, player);
        ReservesSqlConnection.instance.takeReservesItem(itemCount, money.doubleValue(), player);
        ReserveMessage.COMMAND_TAKE.send(player, String.valueOf(itemCount), xapi.getdisplay(money));
        return true;
    }
}
