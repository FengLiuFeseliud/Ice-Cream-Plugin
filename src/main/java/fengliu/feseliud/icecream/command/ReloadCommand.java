package fengliu.feseliud.icecream.command;

import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.config.message.Message;

public class ReloadCommand extends BaseCommand {
    public ReloadCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        PluginConfigs.reloads();
        Message.COMMAND_RELOAD.send(this.sender);
        return true;
    }
}
