package clutch.dungeonrealms.attributes;

import net.minecraft.item.ItemStack;

/**
 * @author Clutch
 * @since 6/24/2017
 */
public abstract class Attribute {

    private String displayName = "";

    public Attribute(String displayName) {
        this.displayName = displayName;
    }

    public abstract void updateInfo(ItemStack stack);

    public String getDisplayName() {
        return displayName;
    }
}
