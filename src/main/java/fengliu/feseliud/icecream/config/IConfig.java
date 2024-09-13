package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.config.item.ConfigItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * 通用配置文件接口
 */
public interface IConfig {
    /**
     * 获取配置文件夹路径
     * @return 文件夹路径
     */
    String getFolderPath();

    /**
     * 获取配置文件名字
     * @return 配置文件名字
     */
    String getName();

    /**
     * 获取配置值
     * @param path 配置键
     * @return 配置值
     */
    Object get(@NotNull String path);

    /**
     * 设置配置值
     * @param path 配置键
     * @param value 配置值
     */
    void set(@NotNull String path, @Nullable Object value);

    /**
     * 保存配置文件
     */
    void save();

    /**
     * 重载配置文件
     */
    void reload();

    default <T> T get(@NotNull String path, T defaultValue, Class<T> type) {
        Object value = this.get(path);
        if (value == null || defaultValue.getClass().isAssignableFrom(type)){
            return defaultValue;
        }
        return type.cast(value);
    }

    /**
     * 设置配置值并保存
     * @param configItem 配置键
     * @param value 配置值
     */
    default <T> void setAndSave(@NotNull ConfigItem<T> configItem, @Nullable T value){
        configItem.set(value);
        this.save();
    }

    /**
     * 重载配置并获取配置值
     * @param path 配置键
     * @return 配置值
     */
    default Object reloadAndGet(@NotNull String path){
        this.reload();
        return this.get(path);
    }

    /**
     * 获取配置文件路径
     * @return 配置文件路径
     */
    default String getConfigPath(){
        if (this.getFolderPath().isEmpty()){
            return this.getName();
        }
        return this.getFolderPath() + "/" + this.getName();
    }

    /**
     * 创建配置文件
     * @param configFile 配置文件对像
     */
    void create(File configFile);
}
