package fengliu.feseliud.icecream.menu.icon;

import fengliu.feseliud.icecream.config.item.MessageConfigItem;
import fengliu.feseliud.icecream.config.message.IconMessage;
import me.filoghost.chestcommands.api.ClickHandler;
import me.filoghost.chestcommands.api.ClickableIcon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AmountIcon<T extends Number> implements ClickableIcon {
    protected final AtomicReference<Integer> count;
    protected final T operatorCount;
    protected final T maxCount;
    private final ItemStack canOperatorStack;
    private final ItemStack canNotOperatorStack;

    private ClickHandler clickHandler;

    public AmountIcon(AtomicReference<Integer> count, T operatorCount, T maxCount, Material canOperatorItem, Material canNotOperatorItem){
        this.count = count;
        this.operatorCount = operatorCount.intValue() != -1 ? operatorCount: maxCount;
        this.maxCount = maxCount;
        this.canOperatorStack = new ItemStack(canOperatorItem);
        this.canNotOperatorStack = new ItemStack(canNotOperatorItem);
    }

    protected abstract int operatorCount();
    protected abstract boolean isMaxCount(int count);
    protected abstract String getOperator();
    protected abstract MessageConfigItem getMaxOperatorMessage();

    @Override
    public void setClickHandler(@Nullable ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @Override
    public @Nullable ClickHandler getClickHandler() {
        if (clickHandler != null){
            return this.clickHandler;
        }

        this.clickHandler = ((menuView, player) -> {
            int newCount = this.operatorCount();
            if (this.isMaxCount(newCount)){
                newCount = this.maxCount.intValue();
            }
            this.count.set(newCount);
            menuView.getMenu().refreshOpenViews();
        });
        return clickHandler;
    }

    private ItemStack getCanOperatorStack(){
        ItemMeta itemMeta = this.canOperatorStack.getItemMeta();
        if (itemMeta == null){
            return this.canOperatorStack;
        }

        if (Objects.equals(this.operatorCount, this.maxCount)){
            itemMeta.setDisplayName(this.getMaxOperatorMessage().getMessage(this.maxCount.toString()));
        } else {
            itemMeta.setDisplayName(IconMessage.QUANTITY_ICON.getMessage(this.getOperator(), this.operatorCount.toString()));
        }
        this.canOperatorStack.setItemMeta(itemMeta);
        return this.canOperatorStack;
    }

    private ItemStack getCanNotOperatorStack(){
        ItemMeta itemMeta = this.canNotOperatorStack.getItemMeta();
        if (itemMeta == null){
            return this.canNotOperatorStack;
        }


        if (Objects.equals(this.operatorCount, this.maxCount)){
            itemMeta.setDisplayName(this.getMaxOperatorMessage().getMessage(this.maxCount.toString()));
        } else {
            itemMeta.setDisplayName(IconMessage.QUANTITY_ICON.getMessage(this.getOperator(), "0"));
        }
        this.canNotOperatorStack.setItemMeta(itemMeta);
        return this.canNotOperatorStack;
    }

    @Override
    public @Nullable ItemStack render(@NotNull Player player) {
        if (this.isMaxCount(this.count.get())){
            return this.getCanNotOperatorStack();
        }
        return this.getCanOperatorStack();
    }
}
