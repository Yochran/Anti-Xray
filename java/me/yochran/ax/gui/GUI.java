package me.yochran.ax.gui;

import me.yochran.ax.utils.ItemBuilder;
import me.yochran.ax.utils.Chat;
import me.yochran.ax.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUI {

    private final Player player;
    private final Inventory inventory;

    private static final Map<UUID, GUI> open_guis;
    protected Map<Integer, Button> buttons;

    static {
        open_guis = new HashMap<>();
    }

    // constructor
    public GUI(Player player, int size, String title) {
        this.player = player;

        this.inventory = Bukkit.createInventory(null, size, Chat.translate(title));
        this.buttons = new HashMap<>();
    }

    /**
     * Open the gui
     * @param gui - the gui to open
     */
    public static void open(GUI gui) {
        open_guis.put(gui.getPlayer().getUniqueId(), gui);
        gui.getPlayer().openInventory(gui.getInventory());
    }

    /**
     * Close a gui
     * @param gui - the gui to close
     */
    public static void close(GUI gui) {
        open_guis.remove(gui.getPlayer().getUniqueId());
        gui.getPlayer().closeInventory();
    }

    // getters
    public Player getPlayer() { return player; }
    public Inventory getInventory() { return inventory; }

    /**
     * Set a button in the GUI
     * @param slot - the slot
     * @param button - the button
     */
    public void setButton(int slot, Button button) {
        buttons.put(slot, button);
        ItemBuilder itemBuilder = new ItemBuilder(button.getItem(), button.getAmount(), button.getName(), button.getLore());

        inventory.setItem(slot, itemBuilder.getItemStack());
        if (button.getAmount() > 1) {
            for (int i = 1; i < button.getAmount(); i++)
                inventory.addItem(itemBuilder.getItemStack());
        }
    }

    /**
     * Get a button by its slot
     * @param slot - the slot
     * @return
     */
    public Button getButton(int slot) { return buttons.get(slot); }

    /**
     * Set the fillter slots
     * @param slots - the slots
     */
    public void setFiller(int slots) {
        ItemBuilder itemBuilder = new ItemBuilder(XMaterial.GRAY_STAINED_GLASS_PANE.parseItem(), 1, "&7 ", new ArrayList<>());
        for (int i = 0; i < slots; i++) this.inventory.setItem(i, itemBuilder.getItemStack());
    }

    /**
     * Check if slot is empty
     * @param slot - the slot
     * @return
     */
    public boolean isSlotEmpty(int slot) {
        return inventory.getItem(slot) == null;
    }

    /**
     * Set the fillter slots
     * @param slots - the slots
     */
    public void setFiller(int[] slots) {
        ItemBuilder itemBuilder = new ItemBuilder(XMaterial.GRAY_STAINED_GLASS_PANE.parseItem(), 1, "&7 ", new ArrayList<>());
        for (int i = 0; i < slots.length; i++) this.inventory.setItem(slots[i], itemBuilder.getItemStack());
    }

    /**
     * Set the fillter slots
     * @param slots - the slots
     * @param fillerItem - the item to set
     */
    public void setFiller(int[] slots, ItemStack fillerItem) {
        ItemBuilder itemBuilder = new ItemBuilder(fillerItem, 1, "&7 ", new ArrayList<>());
        for (int i = 0; i < slots.length; i++) this.inventory.setItem(slots[i], itemBuilder.getItemStack());
    }

    // get all open GUIS
    public static Map<UUID, GUI> getOpenGUIs() { return open_guis; }
}