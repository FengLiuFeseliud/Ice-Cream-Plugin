package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.IceCreamPlugin;
import fengliu.feseliud.icecream.command.BaseCommand;
import fengliu.feseliud.icecream.command.IRootCommands;
import fengliu.feseliud.icecream.command.IceCreamRootCommands;
import fengliu.feseliud.icecream.config.ReservesConfig;
import fengliu.feseliud.icecream.config.item.MessageConfigItem;
import fengliu.feseliud.icecream.config.message.IconMessage;
import fengliu.feseliud.icecream.config.message.ReserveMessage;
import fengliu.feseliud.icecream.menu.icon.AddIcon;
import fengliu.feseliud.icecream.menu.icon.CountIcon;
import fengliu.feseliud.icecream.menu.icon.DelIcon;
import fengliu.feseliud.icecream.sql.ReservesSqlConnection;
import fengliu.feseliud.icecream.util.MenuIconUtil;
import me.filoghost.chestcommands.api.Menu;
import me.filoghost.chestcommands.api.StaticIcon;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class MenuCommand extends BaseCommand {
    public static ItemStack getRenderIcon(String message){
        ItemStack stack = ReservesConfig.ITEM.getItemStack(1);
        ItemMeta itemMeta = stack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(message);
        }
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public MenuCommand(String name) {
        super(name);
    }

    @Override
    public boolean canRun(IRootCommands rootCommands) {
        return super.canRun(rootCommands) && !(this.sender instanceof ConsoleCommandSender);
    }

    private void openAmountMenu(ItemStack fillStack, Player player, MessageConfigItem title, MessageConfigItem countInfo, Menu mainMenu){
        Menu amountMenu = Menu.create(IceCreamPlugin.instance, title.get(), 4);
        MenuIconUtil.fillRing(2, 7, fillStack, amountMenu);
        
        int maxCount = 500;
        if (title == ReserveMessage.RESERVE_MENU_SUBMIT_TITLE){
            maxCount = 0;
            ItemStack reservesStack = ReservesConfig.ITEM.getItemStack(1);
            for (ItemStack stack : player.getInventory().getContents()) {
                if (stack == null){
                    continue;
                }

                if (stack.getType().equals(reservesStack.getType())){
                    maxCount += stack.getAmount();
                }
            }
        } else if (title == ReserveMessage.RESERVE_MENU_TAKE_TITLE) {
            maxCount = (int) ReservesSqlConnection.instance.getReservesItemCount();
        }

        AtomicReference<Integer> itemCount = new AtomicReference<>(0);
        int[] addCounts = new int[]{1, 32, 64, 100, -1};
        for (int index = 0; index < addCounts.length; index ++){
            amountMenu.setIcon(1, index + 1, new AddIcon<>(itemCount, addCounts[index], maxCount, Material.LIME_WOOL, Material.RED_WOOL));
        }

        for (int index = 0; index < addCounts.length; index ++){
            amountMenu.setIcon(2, index + 1, new DelIcon<>(itemCount, addCounts[index], 0, Material.LIME_WOOL, Material.RED_WOOL));
        }

        amountMenu.setIcon(2, 7, new CountIcon<>(itemCount, Material.YELLOW_WOOL, countInfo, (menuView1, countPlayer) -> {
            if (title == ReserveMessage.RESERVE_MENU_SUBMIT_TITLE){
                IceCreamRootCommands.runTemporaryPrivileges(player, IceCreamRootCommands.SUBMIT, String.valueOf(itemCount.get()));
            }else if (title == ReserveMessage.RESERVE_MENU_PAY_TITLE){
                // server.dispatchCommand(player, IceCreamRootCommands.getCommandName(IceCreamRootCommands.PAY) + " " + countPlayer.getName() + " " + itemCount.get());
            }else if (title == ReserveMessage.RESERVE_MENU_TAKE_TITLE){
                IceCreamRootCommands.runTemporaryPrivileges(player, IceCreamRootCommands.TAKE, String.valueOf(itemCount.get()));
            }
        }));

        amountMenu.setIcon(0, 8, StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_RESERVE_ITEM_INFO.getMessage(
                String.valueOf(ReservesSqlConnection.instance.getReservesItemCount())
        ))));

        amountMenu.setIcon(3, 8, StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_RESERVE_INFO.getMessage(
                IceCreamPlugin.xcapi.getdisplay(BigDecimal.valueOf(ReservesSqlConnection.instance.getServerMoney())), String.valueOf(ReservesConfig.RATE.get())
        ))));

        ItemStack backMainMenuStack = new ItemStack(Material.OAK_DOOR);
        ItemMeta itemMeta = backMainMenuStack.getItemMeta();
        if (itemMeta != null){
            itemMeta.setDisplayName(IconMessage.BACK_MAIN_MENU_ICON.get());
        }

        backMainMenuStack.setItemMeta(itemMeta);
        StaticIcon backMainMenuIcon = StaticIcon.create(backMainMenuStack);
        backMainMenuIcon.setClickHandler((menuView, backPlayer) -> mainMenu.open(backPlayer));
        amountMenu.setIcon(3, 0, backMainMenuIcon);
        amountMenu.open(player);
    }

    @Override
    public boolean onRnu() {
        if (ReservesConfig.ITEM.isAir()){
            ReserveMessage.COMMAND_NOT_RESERVE_ITEMS.send(this.sender);
            return false;
        }

        ItemStack fillStack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        Menu menu = Menu.create(IceCreamPlugin.instance, ReserveMessage.RESERVE_MENU_TITLE.get(), 3);
        for (int index = 0; index < 9; index++){
            menu.setIcon(0, index, StaticIcon.create(fillStack));
        }

        for (int index = 0; index < 9; index++){
            menu.setIcon(2, index, StaticIcon.create(fillStack));
        }

        menu.setIcon(1, 0, StaticIcon.create(fillStack));
        menu.setIcon(1, 8, StaticIcon.create(fillStack));
        menu.setIcon(0, 8, StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_RESERVE_ITEM_INFO.getMessage(
                String.valueOf(ReservesSqlConnection.instance.getReservesItemCount())
        ))));

        menu.setIcon(2, 8, StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_RESERVE_INFO.getMessage(
                IceCreamPlugin.xcapi.getdisplay(BigDecimal.valueOf(ReservesSqlConnection.instance.getServerMoney())), String.valueOf(ReservesConfig.RATE.get())
        ))));

        StaticIcon submitIcon = StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_SUBMIT.getMessage()));
        submitIcon.setClickHandler((menuView, player) ->
                this.openAmountMenu(fillStack, player, ReserveMessage.RESERVE_MENU_SUBMIT_TITLE, ReserveMessage.RESERVE_MENU_SUBMIT_COUNT_INFO, menu));
        menu.setIcon(1, 2, submitIcon);

        StaticIcon payIcon = StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_PAY.getMessage()));
        payIcon.setClickHandler((menuView, player) ->
                this.openAmountMenu(fillStack, player, ReserveMessage.RESERVE_MENU_PAY_TITLE, ReserveMessage.RESERVE_MENU_PAY_COUNT_INFO, menu));
        menu.setIcon(1, 4, payIcon);

        StaticIcon takeIcon = StaticIcon.create(MenuCommand.getRenderIcon(ReserveMessage.RESERVE_MENU_TAKE.getMessage()));
        takeIcon.setClickHandler((menuView, player) ->
                this.openAmountMenu(fillStack, player, ReserveMessage.RESERVE_MENU_TAKE_TITLE, ReserveMessage.RESERVE_MENU_TAKE_COUNT_INFO, menu));
        menu.setIcon(1, 6, takeIcon);
        menu.open((Player) this.sender);
        return true;
    }
}
