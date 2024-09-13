package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.BaseCommand;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.config.ReservesConfig;
import fengliu.feseliud.icecream.config.message.ReserveMessage;

public class RateCommand extends BaseCommand {
    public RateCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        double rate;
        if (this.args.length == 2){
            rate = Double.parseDouble(this.args[1]);
            PluginConfigs.RESERVES.setAndSave(ReservesConfig.RATE, rate);
            ReserveMessage.COMMAND_SET_RESERVE_RATE.send(this.sender, String.valueOf(rate));
        }

        rate = ReservesConfig.RATE.get();
        ReserveMessage.COMMAND_RESERVE_RATE.send(this.sender, String.valueOf(rate));
        return true;
    }
}
