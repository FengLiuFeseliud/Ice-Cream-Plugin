package fengliu.feseliud.icecream.command;

import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.message.Message;
import fengliu.feseliud.icecream.message.MessageKey;

public class ReloadCommand extends BaseCommand {
    public ReloadCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        PluginConfigs.reloads();
        Message.send(this.sender, MessageKey.COMMAND_RELOAD);
        return true;
    }
}
