package fengliu.feseliud.icecream.config.message;

import fengliu.feseliud.icecream.config.MessageConfig;
import fengliu.feseliud.icecream.config.item.MessageConfigItem;

public class Message extends MessageConfig {
    public static final MessageConfigItem CONFIG_LOADER = new MessageConfigItem("加载 %config_name% 配置文件完成...", "configLoader", "%config_name%");
    public static final MessageConfigItem MESSAGE_CONFIG_LOADER = new MessageConfigItem("加载 %message_config_name% 语言配置文件完成...", "messageConfigsLoader", "%message_config_name%");
    public static final MessageConfigItem NOT_PLUGIN_XCONOMY = new MessageConfigItem("前置插件 XConomy 不存在!!! 停止加载插件!!!", "notPluginXconomy");
    public static final MessageConfigItem XCONOMY_API_LOADER = new MessageConfigItem("加载 XConomyAPI 完成...", "xconomyApiLoader");
    public static final MessageConfigItem MESSAGE_REPLACEMENTS_NOT_ENOUGH = new MessageConfigItem("%message_key% 需要的格式化字符不够... %message_key% 需要: %message_tags% ", "messageReplacementsNotEnough", "%message_config_name%", "%message_tags%");
    public static final MessageConfigItem CREATE_TABLE = new MessageConfigItem("创建 %table_name% 表...", "createTable", "%table_name%");
    public static final MessageConfigItem CREATE_ERROR_TABLE = new MessageConfigItem("创建 %table_name% 表失败!!!", "createExecutionTable", "%table_name%");
    public static final MessageConfigItem COMMAND_RELOAD = new MessageConfigItem("重载配置成功啦~ 嘿嘿嘿", "commandReload");
    public static final MessageConfigItem COMMAND_EXECUTION_ERROR = new MessageConfigItem("呜 qwq... 指令执行失败了... 查看服务器姬的错误日志吧", "commandExecutionError");
    public static final MessageConfigItem COMMAND_EXECUTION_CONSOLE_ERROR = new MessageConfigItem("呜 qwq... 指令 %command% 执行失败了... 这是我的错误日志: %error_msg%", "commandExecutionConsoleError", "%command%", "%error_msg%");
}
