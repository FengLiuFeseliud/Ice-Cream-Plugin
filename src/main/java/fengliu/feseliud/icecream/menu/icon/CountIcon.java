package fengliu.feseliud.icecream.menu.icon;

import fengliu.feseliud.icecream.config.item.MessageConfigItem;
import me.filoghost.chestcommands.api.ClickHandler;
import me.filoghost.chestcommands.api.ClickableIcon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;

public class CountIcon<T extends Number> implements ClickableIcon {
    private final AtomicReference<T> count;
    private final ItemStack countStack;
    private final MessageConfigItem message;

    private ClickHandler clickHandler;

    public CountIcon(AtomicReference<T> count, Material item, MessageConfigItem message, ClickHandler clickHandler){
        this.count = count;
        this.countStack = new ItemStack(item);
        this.message = message;
        this.clickHandler = clickHandler;
    }

    @Override
    public void setClickHandler(@Nullable ClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    @Override
    public @Nullable ClickHandler getClickHandler() {
        return this.clickHandler;
    }

    @Override
    public @Nullable ItemStack render(@NotNull Player player) {
        ItemMeta itemMeta = this.countStack.getItemMeta();
        if (itemMeta != null){
            itemMeta.setDisplayName(this.message.getMessage(String.valueOf(this.count.get().intValue())));
        }
        this.countStack.setItemMeta(itemMeta);
        return this.countStack;
    }
}
