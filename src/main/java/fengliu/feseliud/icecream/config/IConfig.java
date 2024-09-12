package fengliu.feseliud.icecream.config;

import fengliu.feseliud.icecream.IceCreamPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

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

    /**
     * 设置配置值并保存
     * @param path 配置键
     * @param value 配置值
     */
    default void setAndSave(@NotNull String path, @Nullable Object value){
        this.set(path, value);
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
    default void create(File configFile){
        // 从资源文件夹导出默认配置文件
        InputStream stream = IceCreamPlugin.instance.getResource(this.getConfigPath());
        try {
            FileOutputStream outputStream = new FileOutputStream(configFile);

            assert stream != null;
            outputStream.write(stream.readAllBytes());
            outputStream.close();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
