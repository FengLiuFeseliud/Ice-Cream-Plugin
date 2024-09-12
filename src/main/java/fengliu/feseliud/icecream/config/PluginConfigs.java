package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.message.Message;
import fengliu.feseliud.icecream.message.MessageKey;
import fengliu.feseliud.icecream.util.ReflectionUtil;

/**
 * 配置文件注册
 */
public class PluginConfigs {
    public static final MessageConfig INFO = new MessageConfig();
    public static final MessageConfig RESERVES_INFO = new MessageConfig("reserves");

    public static final Config RESERVES = new Config("reserves");

    /**
     * 重载所有配置文件
     */
    public static void reloads(){
        IceCreamPlugin.instance.saveDefaultConfig();
        IceCreamPlugin.instance.reloadConfig();

        new PluginConfig();

        String pluginLanguage = (String) PluginConfig.instance.get("pluginLanguage");
        ReflectionUtil.getObjects(Config.class, PluginConfigs.class).forEach(config -> {
            if (config instanceof MessageConfig messageConfig){
                messageConfig.setLanguage(pluginLanguage).reload();
                Message.info(MessageKey.MESSAGE_CONFIG_LOADER, messageConfig.getConfigPath());
                return;
            }

            config.reload();
            Message.info(MessageKey.CONFIG_LOADER, config.getConfigPath());
        });
    }
}

