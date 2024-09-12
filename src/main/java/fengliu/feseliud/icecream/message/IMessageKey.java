package fengliu.feseliud.icecream.message;

import fengliu.feseliud.icecream.config.MessageConfig;

/**
 * 消息键
 */
public interface IMessageKey {
    /**
     * 获取消息键
     * @return 消息键
     */
    String getKey();

    /**
     * 获取消息格式化字符数组
     * @return 消息格式化字符数组
     */
    String[] getTags();

    /**
     * 消息键对应的消息配置文件对像
     * @return 消息配置文件对像
     */
    MessageConfig getConfig();
}
