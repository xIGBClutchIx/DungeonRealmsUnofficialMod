package clutch.dungeonrealms.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemStackUtils {

    public static String getLore(ItemStack stack, String starting, String contains, boolean removeStarting) {
        // Checks
        if (starting == null || starting.isEmpty()) return "";
        // Check if nbt exist on stack
        if (stack.getTagCompound() == null) return "";
        // Check if item has displays tag lists
        if (!stack.getTagCompound().hasKey("display", 10)) return "";
        // Get display tag compound
        NBTTagCompound display = stack.getTagCompound().getCompoundTag("display");
        // Check if it has lore
        if (display.getTagId("Lore") != 9) return "";
        // Get lore list
        NBTTagList lore = display.getTagList("Lore", 8);
        // Make sure lore has tags
        if (lore.hasNoTags()) return "";
        // Loop through lore and check line
        for (int i = 0; i < lore.tagCount(); ++i) {
            String loreLine = lore.getStringTagAt(i);
            if (!loreLine.startsWith(starting)) continue;
            if (contains != null && !contains.isEmpty()) {
                if (!loreLine.contains(contains)) continue;
            }
            if (removeStarting) {
                loreLine = loreLine.replaceFirst(starting, "");
            }
            return loreLine;
        }
        return "";
    }

    public static String getColor(ItemStack stack) {
        return stack.getDisplayName().substring(0, 2);
    }
}
