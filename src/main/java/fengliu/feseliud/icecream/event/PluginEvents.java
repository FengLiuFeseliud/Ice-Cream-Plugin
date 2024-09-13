package fengliu.feseliud.icecream.event;

import fengliu.feseliud.icecream.util.ReflectionUtil;
import me.yic.xconomy.api.event.NonPlayerAccountEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class PluginEvents {
    public static Listener eccount = new Listener() {
        @EventHandler
        private void nonPlayerAccount(NonPlayerAccountEvent event) {
            Bukkit.getLogger().info(event.getaccountname());
            Bukkit.getLogger().info(event.getamount().toString());
        }
    };

    public static void addAllEvents(Plugin plugin){
        ReflectionUtil.getObjects(Listener.class, PluginEvents.class).forEach(event -> {
            Bukkit.getServer().getPluginManager().registerEvents(event, plugin);
        });
    }
}
