package clutch.dungeonrealms.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class RealmRuneUtils {

    public static boolean isRealmRune(ItemStack stack) {
        return stack.getItem() == Items.NETHER_STAR;
    }

    public static String getLore(ItemStack stack, String starting) {
        // Needs to have starting text
        if (starting == null || starting.isEmpty()) return "";
        // If does not have nbt tags then return
        if (stack.getTagCompound() == null) return "";
        // If does not have nbt display then return
        if (!stack.getTagCompound().hasKey("display", 10)) return "";
        NBTTagCompound display = stack.getTagCompound().getCompoundTag("display");
        // If tag id 9 is not lore return
        if (display.getTagId("Lore") != 9) return "";
        NBTTagList lore = display.getTagList("Lore", 8);
        // If lore has not tags then return
        if (lore.hasNoTags()) return "";
        // Loop through tags for lore
        for (int tagIndex = 0; tagIndex < lore.tagCount(); ++tagIndex) {
            // Get lore at tag index
            String loreLine = lore.getStringTagAt(tagIndex);
            // If lore line start does not match starting then return
            if (!loreLine.startsWith(starting)) continue;
            // Return lore line
            return loreLine;
        }
        // Return if nothing can be found
        return "";
    }

}
