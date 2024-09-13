package fengliu.feseliud.icecream.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fengliu.feseliud.icecream.IceCreamPlugin;
import org.bukkit.plugin.Plugin;

/**
 * 数据源
 */
public class DataSource {
    public static final HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource dataSource = null;

    /**
     * 获取数据库地址
     * @return 数据库地址
     */
    public static String getJdbcUrl(){
        Plugin plugin = IceCreamPlugin.instance;
        return  "jdbc:sqlite:%s/%s".formatted(plugin.getDataFolder().getAbsolutePath(), plugin.getConfig().get("sqliteDBFile"));
    }

    /**
     * 连接数据库连接池
     */
    public static void connection(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception ignored){}

        hikariConfig.setJdbcUrl(DataSource.getJdbcUrl());
        dataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * 获取数据库连接池
     * @return 连接池
     */
    public static HikariDataSource getDataSource(){
        if (dataSource == null){
            DataSource.connection();
        }
        return dataSource;
    }
}
