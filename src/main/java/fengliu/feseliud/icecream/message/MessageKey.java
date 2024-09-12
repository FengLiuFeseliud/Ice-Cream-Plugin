package fengliu.feseliud.icecream.message;

import fengliu.feseliud.icecream.config.MessageConfig;
import fengliu.feseliud.icecream.config.PluginConfigs;

public enum MessageKey implements IMessageKey {
    CONFIG_LOADER("config.loader", "%config_name%"),
    MESSAGE_CONFIG_LOADER("message.configs.loader", "%message_config_name%"),
    MESSAGE_REPLACEMENTS_NOT_ENOUGH("message.replacements.not.enough", "%message_config_name%", "%message_tags%"),
    CREATE_TABLE("create.table", "%table_name%"),
    CREATE_TABLE_ERROR("create.table.error", "%table_name%"),
    COMMAND_RELOAD("command.reload"),
    COMMAND_EXECUTION_ERROR("command.execution.error"),
    COMMAND_EXECUTION_CONSOLE_ERROR("command.execution.console.error", "%command%", "%error_msg%");

    private final String key;
    private final String[] tags;

    MessageKey(String key, String... tags) {
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
        return PluginConfigs.INFO;
    }
}
