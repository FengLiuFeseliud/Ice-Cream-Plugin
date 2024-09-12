package fengliu.feseliud.icecream.message;

import fengliu.feseliud.icecream.config.MessageConfig;
import fengliu.feseliud.icecream.config.PluginConfigs;

public enum ReserveInfoMessageKey implements IMessageKey{
    COMMAND_SET_RESERVE_ITEMS("command.set.reserve.items", "%item_name%", "%item_id%"),
    COMMAND_SET_RESERVE_RATE("command.set.reserve.rate", "%rate%"),
    COMMAND_RESERVE_RATE("command.reserve.rate", "%rate%");

    private final String key;
    private final String[] tags;

    ReserveInfoMessageKey(String key, String... tags) {
        this.key = key;
        this.tags = tags;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String[] getTags() {
        return this.tags;
    }

    @Override
    public MessageConfig getConfig() {
        return PluginConfigs.RESERVES_INFO;
    }
}
