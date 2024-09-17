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
    public static final MessageConfigItem COMMAND_PAY = new MessageConfigItem("使用服务器货币向 %player_name% 支付 %money% ~", "commandPay", "%player_name%", "%money%");
    public static final MessageConfigItem COMMAND_PAY_NOT_MONEY = new MessageConfigItem("服务器货币不足, 没钱啦!!! 支付不了了 qwq", "commandPayNotMoney");
    public static final MessageConfigItem COMMAND_PAY_NOT_PLAYER = new MessageConfigItem("玩家 %player_name% 不存在啊!!! qwq", "commandPayNotPlayer", "%player_name%");
    public static final MessageConfigItem RESERVE_MENU_RESERVE_INFO = new MessageConfigItem("当前准备金物品, 当前准备金率为 %rate%", "reserveMenuReserveInfo", "%rate%");
    public static final MessageConfigItem RESERVE_MENU_TITLE = new MessageConfigItem("%bank_name% 业务菜单", "reserveMenuTitle", "%bank_name%");
    public static final MessageConfigItem RESERVE_MENU_SUBMIT_TITLE = new MessageConfigItem("%bank_name% 存入业务菜单", "reserveMenuSubmitTitle", "%bank_name%");
    public static final MessageConfigItem RESERVE_MENU_SUBMIT = new MessageConfigItem("存入准备金物品", "reserveMenuSubmit");
    public static final MessageConfigItem RESERVE_MENU_SUBMIT_COUNT_INFO = new MessageConfigItem("存入 %count% 准备金物品", "reserveMenuSubmitCountInfo", "%count%");
    public static final MessageConfigItem RESERVE_MENU_PAY_TITLE = new MessageConfigItem("%bank_name% 货币业务菜单", "reserveMenuPayTitle", "%bank_name%");
    public static final MessageConfigItem RESERVE_MENU_PAY = new MessageConfigItem("支出货币", "reserveMenuPay");
    public static final MessageConfigItem RESERVE_MENU_PAY_COUNT_INFO = new MessageConfigItem("支出 %count% 货币", "reserveMenuPayCountInfo", "%count%");
    public static final MessageConfigItem RESERVE_MENU_TAKE_TITLE = new MessageConfigItem("%bank_name% 取出业务菜单", "reserveMenuTakeTitle", "%bank_name%");
    public static final MessageConfigItem RESERVE_MENU_TAKE = new MessageConfigItem("取出准备金物品", "reserveMenuTake");
    public static final MessageConfigItem RESERVE_MENU_TAKE_COUNT_INFO = new MessageConfigItem("取出 %count% 准备金物品", "reserveMenuTakeCountInfo", "%count%");

    public ReserveMessage(String name) {
        super(name);
    }
}
