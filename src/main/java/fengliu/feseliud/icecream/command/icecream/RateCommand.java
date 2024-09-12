package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.BaseCommand;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.message.Message;
import fengliu.feseliud.icecream.message.ReserveInfoMessageKey;

public class RateCommand extends BaseCommand {
    public RateCommand(String name) {
        super(name);
    }


    @Override
    public boolean onRnu() {
        double rate;
        if (this.args.length == 2){
            rate = Double.parseDouble(this.args[1]);
            PluginConfigs.RESERVES.setAndSave("reserveRate", rate);
            Message.send(this.sender, ReserveInfoMessageKey.COMMAND_SET_RESERVE_RATE, String.valueOf(rate));
        }

        rate = (double) PluginConfigs.RESERVES.get("reserveRate");
        Message.send(this.sender, ReserveInfoMessageKey.COMMAND_RESERVE_RATE, String.valueOf(rate));
        return true;
    }
}
