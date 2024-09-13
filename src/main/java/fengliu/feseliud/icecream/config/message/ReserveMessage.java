package fengliu.feseliud.icecream.config.message;

import fengliu.feseliud.icecream.config.MessageConfig;
import fengliu.feseliud.icecream.config.item.MessageConfigItem;

public class ReserveMessage extends MessageConfig {
    public static final MessageConfigItem COMMAND_SET_RESERVE_ITEMS = new MessageConfigItem("成功设置 %item_name% (ID:  %item_id%) 为准备金物品啦!", "commandSetReserveItems", "%item_name%", "%item_id%");
    public static final MessageConfigItem COMMAND_SET_RESERVE_RATE = new MessageConfigItem("成功将准备金率设置为 %rate% 啦!", "commandSetReserveRate", "%rate%");
    public static final MessageConfigItem COMMAND_NOT_RESERVE_ITEMS = new MessageConfigItem("还... 还没... 设置准备金物品啦! 这可怎么让我操作啊!!!", "commandNotReserveItems");
    public static final MessageConfigItem COMMAND_RESERVE_RATE = new MessageConfigItem("当前准备金率为 %rate% 哦~", "commandReserveRate", "%rate%");
    public static final MessageConfigItem COMMAND_SUBMIT = new MessageConfigItem("成功向银行存入 %sub_count% 准备金物品, 获得 %money% ~", "commandSubmit", "%sub_count%", "%money%");
    public static final MessageConfigItem COMMAND_TAKE = new MessageConfigItem("成功向银行取出 %take_count% 准备金物品, 花费 %money% ~", "commandTake", "%take_count%", "%money%");

    public ReserveMessage(String name) {
        super(name);
    }
}
