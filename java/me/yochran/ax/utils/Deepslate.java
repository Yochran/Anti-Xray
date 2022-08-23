package me.yochran.ax.utils;

public class Deepslate {

    /**
     * Get a deepslate ore from its normal counterpart
     * @param mat - the xmaterial ore
     * @return
     */
    public static XMaterial from(XMaterial mat) {
        // create deepslate variable
        XMaterial deepslate = null;

        // switch the material
        switch (mat) {
            case COAL_ORE: deepslate = XMaterial.DEEPSLATE_COAL_ORE;
            case IRON_ORE: deepslate = XMaterial.DEEPSLATE_IRON_ORE;
            case COPPER_ORE: deepslate = XMaterial.DEEPSLATE_COPPER_ORE;
            case GOLD_ORE: deepslate = XMaterial.DEEPSLATE_GOLD_ORE;
            case REDSTONE_ORE: deepslate = XMaterial.DEEPSLATE_REDSTONE_ORE;
            case EMERALD_ORE: deepslate = XMaterial.DEEPSLATE_EMERALD_ORE;
            case LAPIS_ORE: deepslate = XMaterial.DEEPSLATE_LAPIS_ORE;
            case DIAMOND_ORE: deepslate = XMaterial.DEEPSLATE_DIAMOND_ORE;
        }

        // return
        return deepslate;
    }
}
