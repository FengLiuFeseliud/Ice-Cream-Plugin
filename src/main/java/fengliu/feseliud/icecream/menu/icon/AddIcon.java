package fengliu.feseliud.icecream.menu.icon;

import fengliu.feseliud.icecream.config.item.MessageConfigItem;
import fengliu.feseliud.icecream.config.message.IconMessage;
import org.bukkit.Material;

import java.util.concurrent.atomic.AtomicReference;

public class AddIcon<T extends Number> extends AmountIcon<T>{
    public AddIcon(AtomicReference<Integer> count, T operatorCount, T maxCount, Material canOperatorItem, Material canNotOperatorItem) {
        super(count, operatorCount, maxCount, canOperatorItem, canNotOperatorItem);
    }

    @Override
    protected int operatorCount() {
        return this.count.get() + this.operatorCount.intValue();
    }

    @Override
    protected boolean isMaxCount(int count) {
        return count >= this.maxCount.intValue();
    }

    @Override
    protected String getOperator() {
        return "+";
    }

    @Override
    protected MessageConfigItem getMaxOperatorMessage() {
        return IconMessage.QUANTITY_MAX_ADD_ICON;
    }
}
