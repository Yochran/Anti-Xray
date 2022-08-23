package me.yochran.ax.listeners;

import me.yochran.ax.AntiXray;
import me.yochran.ax.utils.Chat;
import me.yochran.ax.utils.Ore;
import me.yochran.ax.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BlockBreakListener implements Listener {

    // instance
    private final AntiXray instance = AntiXray.getInstance();

    /**
     * Block break event
     * @param event - the event
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // get drop type
        XMaterial dropType = Ore.from(event.getBlock().getType());
        if (dropType == null)
            return;

        // get player
        Player player = event.getPlayer();

        // check if player has bypass permission
        if (player.hasPermission("ax.bypass"))
            return;

        // get world info
        World world = player.getWorld();
        String worldName = world.getName();

        // get break info
        Block block = event.getBlock();
        Material type = block.getType();
        String typeName = type.toString();

        // get inventory info
        int inInventory = 0;

        // for every item
        for (ItemStack item : player.getInventory().getContents()) {
            // if item isn't null and item matches type
            if (item != null && item.getType() == dropType.parseMaterial())
                // add to number
                inInventory = inInventory + item.getAmount();
        }

        // for every staff
        for (Map.Entry<UUID, List<XMaterial>> staff : instance.players.entrySet()) {
            // check if staff has this alert toggled
            if (!staff.getValue().contains(XMaterial.matchXMaterial(type)))
                continue;

            // get staff member
            Player staffMember = Bukkit.getPlayer(staff.getKey());

            // check if null, not online, and no permission
            if (staffMember != null && staffMember.isOnline() && staffMember.hasPermission("ax.alerts"))
                // send message
                staffMember.sendMessage(
                        Chat.translate(instance.getConfig().getString("Message")
                        .replace("%world%", worldName)
                        .replace("%player%", player.getName())
                        .replace("%block%", typeName)
                        .replace("%cii%", String.valueOf(inInventory))
                ));
        }
    }
}
