package fengliu.feseliud.icecream;

import fengliu.feseliud.icecream.command.IceCreamRootCommands;
import fengliu.feseliud.icecream.config.PluginConfigs;
import fengliu.feseliud.icecream.event.PluginEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Logger;

public final class IceCreamPlugin extends JavaPlugin {
    public static JavaPlugin instance;
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
    }

    @Override
    public void onEnable() {
        new IceCreamRootCommands().register();
        PluginEvents.addAllEvents(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
