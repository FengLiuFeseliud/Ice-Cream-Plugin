package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.IceCreamPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * 插件配置文件
 */
public class PluginConfig implements IConfig{
    protected FileConfiguration fileConfig = IceCreamPlugin.instance.getConfig();
    public static IConfig instance = new PluginConfig();

    @Override
    public String getFolderPath() {
        return "";
    }

    @Override
    public String getName() {
        return "config.yml";
    }

    @Override
    public Object get(@NotNull String path) {
        return this.fileConfig.get(path);
    }

    @Override
    public void set(@NotNull String path, @Nullable Object value) {
        this.fileConfig.set(path, value);
    }

    @Override
    public void save() {
        IceCreamPlugin.instance.saveConfig();
    }

    @Override
    public void reload() {
        IceCreamPlugin.instance.saveDefaultConfig();
        IceCreamPlugin.instance.reloadConfig();
        fileConfig = IceCreamPlugin.instance.getConfig();
    }

    @Override
    public void create(File configFile) {

    }
}
