package fengliu.feseliud.icecream.config.item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigItem<T> implements IConfigItem<T> {
    protected T data;
    protected final String key;
    protected final String description;


    public ConfigItem(@NotNull T data, @NotNull String key, @Nullable String description){
        this.set(data);
        this.key = key;
        this.description = description;
    }

    public ConfigItem(@NotNull T data, @NotNull String key){
        this(data, key, null);
    }

    @Override
    public T get() {
        return this.data;
    }

    @Override
    public void set(T data) {
        this.data = data;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
