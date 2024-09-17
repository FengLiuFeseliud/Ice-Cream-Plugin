package fengliu.feseliud.icecream.config.message;

import fengliu.feseliud.icecream.config.MessageConfig;
import fengliu.feseliud.icecream.config.item.MessageConfigItem;

public class IconMessage extends MessageConfig {
    public static final MessageConfigItem BACK_MAIN_MENU_ICON = new MessageConfigItem("返回到主菜单", "backMainMenuIcon");
    public static final MessageConfigItem QUANTITY_ICON = new MessageConfigItem("%operator%%count%", "quantityIcon", "%operator%", "%count%");
    public static final MessageConfigItem QUANTITY_MAX_ADD_ICON = new MessageConfigItem("增加到最大值 %count%", "quantityMaxAddIcon", "%count%");
    public static final MessageConfigItem QUANTITY_MAX_DEL_ICON = new MessageConfigItem("减少到最小值 %count%", "quantityMaxDelIcon", "%count%");

    public IconMessage(String name){
        super(name);
    }
}
