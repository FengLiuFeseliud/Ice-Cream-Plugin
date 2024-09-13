package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.command.BaseCommand;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import fengliu.feseliud.icecream.sql.ReservesSqlConnection;
import me.yic.xconomy.data.syncdata.PlayerData;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class PayCommand extends BaseCommand {
    public PayCommand(String name) { super(name); }

    @Override
    public boolean onRnu() {
        if (!(this.sender instanceof Player player)){
            return false;
        }

        if (this.args.length <= 2){
            return false;
        }

        PlayerData playerData = IceCreamPlugin.xcapi.getPlayerData(this.args[1]);
        if (playerData == null){
            ReserveMessage.COMMAND_PAY_NOT_PLAYER.send(player, this.args[1]);
            return true;
        }

        BigDecimal money = BigDecimal.valueOf(Double.parseDouble(this.args[2]));
        if (!ReservesSqlConnection.instance.payServerMoney(money.doubleValue(), player)){
            ReserveMessage.COMMAND_PAY_NOT_MONEY.send(player);
            return true;
        }

        IceCreamPlugin.xcapi.changePlayerBalance(player.getUniqueId(), player.getName(), money, true);
        ReserveMessage.COMMAND_PAY.send(player, player.getName(), IceCreamPlugin.xcapi.getdisplay(money));
        return true;
    }
}
