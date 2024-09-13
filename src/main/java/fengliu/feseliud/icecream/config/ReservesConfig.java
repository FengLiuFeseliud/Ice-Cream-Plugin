package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.config.item.ConfigItem;
import fengliu.feseliud.icecream.config.item.ItemConfigItem;

public class ReservesConfig extends Config{
    public static final ItemConfigItem ITEM = new ItemConfigItem("AIR", "item", "准备金物品");
    public static final ConfigItem<Double> RATE = new ConfigItem<>(0.01, "rate", "准备金率");
    public static final ConfigItem<Double> ITEM_RATE = new ConfigItem<>(0.01, "itemRate", "回购准备金物品的准备金率");
    public static final ConfigItem<Integer> INITIAL_ITEM_COUNT = new ConfigItem<>(1000, "initialItemsCount", "准备金物品初始值");
    public static final ConfigItem<Double> INITIAL_SERVER_MONEY = new ConfigItem<>(100000.00, "initialServerMoney", "服务器货币初始值");

    public ReservesConfig(String name) {
        super(name);
    }

    public ReservesConfig(String folderPath, String name) {
        super(folderPath, name);
    }
}
