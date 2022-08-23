package me.yochran.ax.gui.guis;

import me.yochran.ax.AntiXray;
import me.yochran.ax.gui.Button;
import me.yochran.ax.gui.CustomGUI;
import me.yochran.ax.gui.GUI;
import me.yochran.ax.utils.Chat;
import me.yochran.ax.utils.ItemBuilder;
import me.yochran.ax.utils.XMaterial;
import org.bukkit.entity.Player;

import java.util.*;

public class AXGui extends CustomGUI {

    // instance
    private final AntiXray instance = AntiXray.getInstance();

    // lores
    private final String on_lore = "&7Click to turn &aon &7alerts for %name%&7.";
    private final String off_lore = "&7Click to turn &coff &7alerts for %name%&7.";

    // constructor
    public AXGui(Player player, int size, String title) {
        super(player, size, title);
    }

    // all ores
    private final Map<Integer, Object[]> ores = new HashMap<Integer, Object[]>() {{
        // first row
        put(10, new Object[] { XMaterial.COAL_ORE, "&7Coal Ore" });
        put(11, new Object[] { XMaterial.IRON_ORE, "&6Iron Ore" });
        put(12, new Object[] { XMaterial.COPPER_ORE, "&6Copper Ore" });
        put(13, new Object[] { XMaterial.GOLD_ORE, "&eGold Ore" });
        put(14, new Object[] { XMaterial.REDSTONE_ORE, "&4Redstone Ore" });
        put(15, new Object[] { XMaterial.EMERALD_ORE, "&2Emerald Ore" });
        put(16, new Object[] { XMaterial.LAPIS_ORE, "&1Lapis Ore" });

        // second row
        put(20, new Object[] { XMaterial.DIAMOND_ORE, "&bDiamond Ore" });
        put(21, new Object[] { XMaterial.ANCIENT_DEBRIS, "&8Ancient Debris" });
        put(23, new Object[] { XMaterial.NETHER_GOLD_ORE, "&eNether Gold Ore" });
        put(24, new Object[] { XMaterial.NETHER_QUARTZ_ORE, "&fNether Quartz Ore" });
    }};

    /**
     * Set up the GUI
     */
    public void setup() {
        // gui variables
        GUI gui = this.getGui();
        UUID playerUUID = gui.getPlayer().getUniqueId();

        // check if in map
        if (!instance.players.containsKey(playerUUID))
            instance.players.put(playerUUID, new ArrayList<>());

        // set the fillers
        gui.setFiller(36);

        for (Map.Entry<Integer, Object[]> ore : ores.entrySet()) {
            // get details on item
            XMaterial item = (XMaterial) ore.getValue()[0];
            String name = Chat.translate((String) ore.getValue()[1]);
            List<String> lore = new ArrayList<>(Collections.singletonList(on_lore.replace("%name%", name)));

            // check if to be toggled off
            if (instance.players.get(playerUUID).contains(item))
                lore = new ArrayList<>(Collections.singletonList(off_lore.replace("%name%", name)));

            // create item builder
            ItemBuilder itemBuilder = new ItemBuilder(
                    item.parseItem(),
                    1,
                    name,
                    lore
            );

            // create button
            Button button = new Button(
                    itemBuilder.getItem(),
                    () -> {
                        // get current button
                        Button current = gui.getButton(ore.getKey());

                        // check which to toggle
                        if (instance.players.get(playerUUID).contains(item)) {
                            // set lore
                            current.setLore(Collections.singletonList(on_lore.replace("%name%", name)));

                            // remove from list
                            instance.players.get(playerUUID).remove(item);
                        } else {
                            // set lore
                            current.setLore(Collections.singletonList(off_lore.replace("%name%", name)));

                            // add to list
                            instance.players.get(playerUUID).add(item);
                        }

                        // update button
                        gui.setButton(ore.getKey(), current);
                    },
                    itemBuilder.getName(),
                    itemBuilder.getLore()
            );

            // set into gui
            gui.setButton(ore.getKey(), button);
        }
    }
}
