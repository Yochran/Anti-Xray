package me.yochran.ax.listeners;


import me.yochran.ax.gui.Button;
import me.yochran.ax.gui.GUI;
import me.yochran.ax.utils.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickListener implements Listener {

    /**
     * Function for when a player clicks a button in a GUI.
     * @param event - the event
     */
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        // check if current item is null or air
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == XMaterial.AIR.parseMaterial())
            return;

        // check if player doesn't have a GUI open
        if (!GUI.getOpenGUIs().containsKey(event.getWhoClicked().getUniqueId()))
            return;

        // get variables
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        int slot = event.getSlot();
        GUI gui = GUI.getOpenGUIs().get(player.getUniqueId());

        // check if inventory is null
        if (gui == null || !gui.getInventory().equals(inventory))
            return;

        // set cancelled
        event.setCancelled(true);

        // get the button that was clicked
        Button button = gui.getButton(slot);

        // check if button is null or if button doesn't have action
        if (button == null || button.getAction() == null)
            return;

        // get the action
        Runnable action = button.getAction();

        // run the action
        action.run();
    }
}
