package clutch.dungeonrealms.utils;

import net.minecraft.item.ItemStack;

public class ItemStackUtils {

    public static String getColor(ItemStack stack) {
        return stack.getDisplayName().substring(0, 2);
    }
}
