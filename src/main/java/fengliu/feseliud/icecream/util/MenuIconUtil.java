package fengliu.feseliud.icecream.util;

import me.filoghost.chestcommands.api.Menu;
import me.filoghost.chestcommands.api.StaticIcon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MenuIconUtil {

    public static void fill(ItemStack stack, Menu menu){
        for (int rowIndex = 0; rowIndex < menu.getRows(); rowIndex++){
            for (int index = 0; index < 9; index++){
                menu.setIcon(rowIndex, index, StaticIcon.create(stack));
            }
        }
    }

    public static void fillRing(int blankLine, int blankSize, ItemStack stack, Menu menu){
        MenuIconUtil.fill(stack, menu);
        int fillHalfLine = (int) (double) ((menu.getRows() - blankLine) / 2);
        int fillHalfSize = (int) (double) ((9 - blankSize) / 2);

        for (int rowIndex = fillHalfLine; rowIndex < menu.getRows() - fillHalfLine; rowIndex++){
            for (int index = fillHalfSize; index < 9 - fillHalfSize; index++){
                menu.setIcon(rowIndex, index, StaticIcon.create(new ItemStack(Material.AIR)));
            }
        }
    }
}
