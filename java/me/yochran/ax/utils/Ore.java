package me.yochran.ax.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Ore {

    /**
     * Get the drop from an ore
     * @param material - the item
     * @return
     */
    public static XMaterial from(Material material) {
        // drop
        XMaterial drop = null;

        // switch item
        switch (material) {
            case COAL_ORE:
            case DEEPSLATE_COAL_ORE:
                drop = XMaterial.COAL;
                break;

            case IRON_ORE:
            case DEEPSLATE_IRON_ORE:
                drop = XMaterial.IRON_ORE;
                break;

            case COPPER_ORE:
            case DEEPSLATE_COPPER_ORE:
                drop = XMaterial.RAW_COPPER;
                break;

            case GOLD_ORE:
            case DEEPSLATE_GOLD_ORE:
                drop = XMaterial.GOLD_ORE;
                break;

            case REDSTONE_ORE:
            case DEEPSLATE_REDSTONE_ORE:
                drop = XMaterial.REDSTONE;
                break;

            case EMERALD_ORE:
            case DEEPSLATE_EMERALD_ORE:
                drop = XMaterial.EMERALD;
                break;

            case LAPIS_ORE:
            case DEEPSLATE_LAPIS_ORE:
                drop = XMaterial.LAPIS_LAZULI;
                break;

            case DIAMOND_ORE:
            case DEEPSLATE_DIAMOND_ORE:
                drop = XMaterial.DIAMOND;
                break;

            case ANCIENT_DEBRIS:
                drop = XMaterial.ANCIENT_DEBRIS;
                break;

            case NETHER_GOLD_ORE:
                drop = XMaterial.GOLD_NUGGET;
                break;

            case NETHER_QUARTZ_ORE:
                drop = XMaterial.QUARTZ;
                break;
        }

        return drop;
    }
}
