package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.config.item.IConfigItem;
import fengliu.feseliud.icecream.util.ReflectionUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

/**
 * 基本配置文件
 */
public class Config implements IConfig {
    protected final String folderPath;
    protected final String name;

    protected FileConfiguration configuration = null;

    public Config(String folderPath, String name){
        this.folderPath = folderPath;
        this.name = name;
    }

    public Config(String name){
        this("", name);
    }

    @Override
    public String getFolderPath() {
        return this.folderPath;
    }

    @Override
    public String getName() {
        return this.name + ".yml";
    }

    @Override
    public Object get(@NotNull String path) {
        return this.configuration.get(path);
    }

    @Override
    public void set(@NotNull String path, @Nullable Object value) {
        this.configuration.set(path, value);
    }

    @Override
    public void save() {
        File configFile = new File(this.getFolderFile(), this.getName());
        try {
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            ReflectionUtil.getObjects(IConfigItem.class, this.getClass()).forEach(configItem -> {
                yamlConfiguration.set(configItem.getKey(), configItem.get());
            });
            yamlConfiguration.save(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(File configFile) {
        this.save();
    }

    @Override
    public void reload() {
        File configFile = new File(this.getFolderFile(), this.getName());
        if (!configFile.exists()){
            this.create(configFile);
        }
        this.configuration = YamlConfiguration.loadConfiguration(configFile);
        ReflectionUtil.getObjects(IConfigItem.class, this.getClass()).forEach(configItem -> {
            Object data = configuration.get(configItem.getKey());
            if (data == null){
                return;
            }
            configItem.set(data);
        });
    }

    private File getFolderFile(){
        File folderFile = new File(IceCreamPlugin.instance.getDataFolder(), this.getFolderPath());
        if (!folderFile.exists()){
            folderFile.mkdirs();
        }
        return folderFile;
    }
}
