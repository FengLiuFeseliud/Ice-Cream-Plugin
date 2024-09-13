package fengliu.feseliud.icecream;

import fengliu.feseliud.icecream.command.IceCreamRootCommands;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.config.message.Message;
import fengliu.feseliud.icecream.event.PluginEvents;
import fengliu.feseliud.icecream.sql.DataSource;
import fengliu.feseliud.icecream.sql.ReservesSqlConnection;
import me.yic.xconomy.api.XConomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Logger;

public final class IceCreamPlugin extends JavaPlugin {
    public static JavaPlugin instance;
    public static XConomyAPI xcapi;
    public static Logger logger;

    @Override
    public void onLoad() {
        instance = this;
        logger = this.getLogger();

        saveDefaultConfig();
        reloadConfig();

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getResource("icon"))));
        while(true) {
            try {
                if (!reader.ready()) break;
                logger.info(reader.readLine().replaceAll("%ver%", this.toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        PluginConfigs.reloads();
        if (Bukkit.getPluginManager().getPlugin("XConomy") == null){
            Message.NOT_PLUGIN_XCONOMY.severe();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        xcapi = new XConomyAPI();
        Message.XCONOMY_API_LOADER.info();

        DataSource.connection();
        ReservesSqlConnection db = ReservesSqlConnection.instance;
    }

    @Override
    public void onEnable() {
        new IceCreamRootCommands().register();
        PluginEvents.addAllEvents(this);
    }

    @Override
    public void onDisable() {
        DataSource.getDataSource().close();
    }
}
