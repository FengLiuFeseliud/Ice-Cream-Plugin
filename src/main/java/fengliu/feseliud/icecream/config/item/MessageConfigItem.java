package fengliu.feseliud.icecream.config.item;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.config.message.Message;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MessageConfigItem implements IConfigItem<String> {
    protected String data;
    private final String key;
    private final String[] tags;

    public MessageConfigItem(String data, String key, String... tags){
        this.data = data;
        this.key = key;
        this.tags = tags;
    }

    @Override
    public String get() {
        return this.data;
    }

    @Override
    public void set(String data) {
        this.data = data;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getDescription() {
        return null;
    }

    public String[] getTags(){
        return this.tags;
    }


    /**
     * 获取消息
     * @return 消息字符串
     */
    public String getMessage(String... replacements){
        String message = this.data;

        String[] tags = this.getTags();
        if(tags.length != replacements.length){
            Message.MESSAGE_REPLACEMENTS_NOT_ENOUGH.warning(this.getKey(), Arrays.toString(tags));
            return message;
        }

        for (int index = 0; index < tags.length; ++index) {
            message = message.replace(tags[index], replacements[index]);
        }
        return message;
    }

    /**
     * 获取消息列表 (分割为多行)
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     * @return 消息字符串列表
     */
    public List<String> getMessages(String... replacements){
        return List.of(this.getMessage(replacements).split("\n"));
    }

    /**
     * 向指令使用者发送消息
     * @param sender 指令使用者
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public void send(@NotNull CommandSender sender, String... replacements){
        this.getMessages(replacements).forEach(sender::sendMessage);
    }

    /**
     * 发送 info 级消息日志
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public void info(String... replacements){
        this.getMessages(replacements).forEach(IceCreamPlugin.logger::info);
    }

    /**
     * 发送 warning 级消息日志
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public void warning(String... replacements){
        this.getMessages(replacements).forEach(IceCreamPlugin.logger::warning);
    }


    /**
     * 发送 severe 级消息日志
     * @param replacements 格式化字符值列表 (顺序跟格式化字符列表对应)
     */
    public void severe(String... replacements){
        this.getMessages(replacements).forEach(IceCreamPlugin.logger::severe);
    }
}
