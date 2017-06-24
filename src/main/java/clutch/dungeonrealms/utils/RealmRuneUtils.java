package clutch.dungeonrealms.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class RealmRuneUtils {

    public static boolean isRealmRune(ItemStack stack) {
        return stack.getItem() == Items.NETHER_STAR;
    }
}
