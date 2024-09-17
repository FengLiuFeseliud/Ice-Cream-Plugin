package fengliu.feseliud.icecream.menu.icon;

import fengliu.feseliud.icecream.config.item.MessageConfigItem;
import fengliu.feseliud.icecream.config.message.IconMessage;
import org.bukkit.Material;

import java.util.concurrent.atomic.AtomicReference;

public class DelIcon<T extends Number> extends AmountIcon<T> {
    public DelIcon(AtomicReference<Integer> count, T operatorCount, T maxCount, Material canOperatorItem, Material canNotOperatorItem) {
        super(count, operatorCount, maxCount, canOperatorItem, canNotOperatorItem);
    }

    @Override
    protected int operatorCount() {
        if (this.operatorCount.intValue() == this.maxCount.intValue()){
            return this.maxCount.intValue();
        }

        return this.count.get() - this.operatorCount.intValue();
    }

    @Override
    protected boolean isMaxCount(int count) {
        return count <= 0;
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    protected MessageConfigItem getMaxOperatorMessage() {
        return IconMessage.QUANTITY_MAX_DEL_ICON;
    }
}
