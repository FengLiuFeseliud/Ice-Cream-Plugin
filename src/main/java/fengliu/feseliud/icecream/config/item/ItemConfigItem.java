package fengliu.feseliud.icecream.config.item;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemConfigItem extends ConfigItem<String>{
    public ItemConfigItem(@NotNull String data, @NotNull String key, @Nullable String description) {
        super(data, key, description);
    }

    public ItemConfigItem(@NotNull String data, @NotNull String key){
        this(data, key, null);
    }

    public Material getItem(){
        return Material.getMaterial(this.get());
    }

    public void setItem(Material item){
        this.set(item.toString());
    }
}
