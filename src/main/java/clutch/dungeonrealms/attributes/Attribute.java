package clutch.dungeonrealms.attributes;

import net.minecraft.item.ItemStack;

/**
 * @author Clutch
 * @since 6/24/2017
 */
public abstract class Attribute {

    public abstract void updateInfo(ItemStack stack);
    public abstract int getCompareValue();

}
