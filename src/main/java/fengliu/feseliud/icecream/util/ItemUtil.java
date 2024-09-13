package fengliu.feseliud.icecream.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {

    public static void dropItem(Material item, int itemCount, Location pos, World world){
        int stackMaxCount = item.getMaxStackSize();
        while (true){
            if (itemCount < stackMaxCount){
                world.dropItem(pos, new ItemStack(item, itemCount));
                break;
            }
            world.dropItem(pos, new ItemStack(item, stackMaxCount));
            itemCount -= stackMaxCount;
        }
    }

    public static void dropItem(Material item, int itemCount, Player player){
        ItemUtil.dropItem(item, itemCount, player.getLocation(), player.getWorld());
    }
}
