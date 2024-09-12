package fengliu.feseliud.icecream.message;

import fengliu.feseliud.icecream.IceCreamPlugin;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * 消息工具
 */
public class Message {
    /**
     * 分割为多行消息列表
     * @param message 消息字符串
     * @return 消息字符串列表
     */
    private static List<String>  splitMessage(String message){
        return Arrays.stream(message.split("\n")).toList();
    }

    /**
     * 获取消息列表 (分割为多行)
     * @param messageKey 消息键
     * @return 消息字符串列表
     */
    public static List<String> getMessages(@NotNull IMessageKey messageKey){
        return Message.splitMessage((String) messageKey.getConfig().get(messageKey.getKey()));
    }

    /**
     * 获取消息列表 (分割为多行)
     * @param messageKey 消息键
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     * @return 消息字符串列表
     */
    public static List<String> getMessages(@NotNull IMessageKey messageKey, String... replacements){
        String message = (String) messageKey.getConfig().get(messageKey.getKey());

        String[] tags = messageKey.getTags();
        if(tags.length != replacements.length){
            Message.warning(MessageKey.MESSAGE_REPLACEMENTS_NOT_ENOUGH, messageKey.getKey(), Arrays.toString(messageKey.getTags()));
            return Message.splitMessage(message);
        }

        for (int index = 0; index < messageKey.getTags().length; ++index) {
            message = message.replace(tags[index], replacements[index]);
        }
        return Message.splitMessage(message);
    }

    /**
     * 向指令使用者发送消息
     * @param sender 指令使用者
     * @param messageKey 消息键
     */
    public static void send(@NotNull CommandSender sender, @NotNull IMessageKey messageKey){
        Message.getMessages(messageKey).forEach(sender::sendMessage);
    }

    /**
     * 向指令使用者发送消息
     * @param sender 指令使用者
     * @param messageKey 消息键
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public static void send(@NotNull CommandSender sender, @NotNull IMessageKey messageKey, String... replacements){
        Message.getMessages(messageKey, replacements).forEach(sender::sendMessage);
    }

    /**
     * 发送 info 级消息日志
     * @param messageKey 消息键
     */
    public static void info(@NotNull IMessageKey messageKey){
        Message.getMessages(messageKey).forEach(IceCreamPlugin.logger::info);
    }

    /**
     * 发送 info 级消息日志
     * @param messageKey 消息键
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public static void info(@NotNull IMessageKey messageKey, String... replacements){
        Message.getMessages(messageKey, replacements).forEach(IceCreamPlugin.logger::info);
    }

    /**
     * 发送 warning 级消息日志
     * @param messageKey 消息键
     */
    public static void warning(@NotNull IMessageKey messageKey){
        Message.getMessages(messageKey).forEach(IceCreamPlugin.logger::warning);
    }

    /**
     * 发送 warning 级消息日志
     * @param messageKey 消息键
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public static void warning(@NotNull IMessageKey messageKey, String... replacements){
        Message.getMessages(messageKey, replacements).forEach(IceCreamPlugin.logger::warning);
    }
}
