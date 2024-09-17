package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.config.message.IconMessage;
import fengliu.feseliud.icecream.config.message.Message;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import fengliu.feseliud.icecream.util.ReflectionUtil;

/**
 * 配置文件注册
 */
public class PluginConfigs {
    public static final Message INFO = new Message();
    public static final ReserveMessage RESERVES_INFO = new ReserveMessage("reserves");
    public static final ReservesConfig RESERVES = new ReservesConfig("reserves");
    public static final IconMessage ICON = new IconMessage("icon");


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
                Message.MESSAGE_CONFIG_LOADER.info(messageConfig.getConfigPath());
                return;
            }

            config.reload();
            Message.CONFIG_LOADER.info(config.getConfigPath());
        });
    }
}

