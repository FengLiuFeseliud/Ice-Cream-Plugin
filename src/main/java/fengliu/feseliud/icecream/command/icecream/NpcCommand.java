package fengliu.feseliud.icecream.command.icecream;

import fengliu.feseliud.icecream.command.PlayerCommand;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class NpcCommand extends PlayerCommand {
    public NpcCommand(String name) {
        super(name);
    }

    @Override
    public boolean onRnu() {
        if (!(this.sender instanceof Player player)){
            return true;
        }
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PAINTING, "test");
        npc.spawn(player.getLocation());
        return false;
    }
}
