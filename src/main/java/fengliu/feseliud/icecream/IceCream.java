package fengliu.feseliud.icecream;

import fengliu.feseliud.icecream.command.IceCreamRootCommands;
import fengliu.feseliud.icecream.event.PluginEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class IceCream extends JavaPlugin {
    public static Logger logger = null;

    @Override
    public void onEnable() {
        logger = this.getLogger();
        new IceCreamRootCommands().register();
        PluginEvents.addAllEvents(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
